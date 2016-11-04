package br.aeso.exercicio.venda;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.aeso.exercicio.venda.Venda;
import br.aeso.exercicio.venda.VendaNaoEncontradaException;
import br.aeso.exercicio.pedido.PedidoNaoEncontradoException;
import br.aeso.exercicio.produto.ProdutoNaoEncontradoException;
import br.aeso.exercicio.venda.ConnectarDBVenda;


public class RepositorioVendaArrayList implements IRepositorioVenda{
	private ArrayList<Venda> vendas;
	private ConnectarDBVenda banco;
	public RepositorioVendaArrayList() throws ClassNotFoundException, IOException, SQLException {
		this.banco = new ConnectarDBVenda();
	}
	
	public void cadastrar(Venda venda){
		try {
			this.banco.cadastrar(venda);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Venda> listar() throws ClassNotFoundException, SQLException, ProdutoNaoEncontradoException, IOException, PedidoNaoEncontradoException{
		try {
			this.vendas = this.banco.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.vendas;
	}
	public boolean remover(Venda venda)  throws VendaNaoEncontradaException, IOException{
		int index = this.vendas.indexOf(venda);
		if(index == -1){
			throw new VendaNaoEncontradaException();
		}
		try {
			this.banco.remover(venda);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public boolean remover(double codigo){
		for(Venda venda : this.vendas){
			if(venda.getCodigo() == codigo){
				try {
					this.banco.remover(venda);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}
	public Venda procurar(double codigo) throws ClassNotFoundException, SQLException, ProdutoNaoEncontradoException, IOException, PedidoNaoEncontradoException{
		Map<String, Object> valores = new HashMap<String, Object>();
		valores.put("Codigo", codigo);
		ArrayList<Venda> lista = null;
		try {
			lista = this.banco.procurar(valores);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(lista == null){
			return null;
		}
		Venda venda = lista.get(0);
		return venda;
		
	}
	public Venda procurar(Venda venda) throws ClassNotFoundException, SQLException, ProdutoNaoEncontradoException, IOException, PedidoNaoEncontradoException{
		Map<String, Object> valores = new HashMap<String, Object>();
		valores.put("Codigo", venda.getCodigo());
		ArrayList<Venda> lista = null;
			
		try {
			lista = this.banco.procurar(valores);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(lista == null){
			return null;
		}
		Venda vendaResp = lista.get(0);
		return vendaResp;
	}
	public void atualizar(Venda venda){
		if(!this.vendas.contains(vendas)){
			this.cadastrar(venda);
		}
		try {
			this.banco.atualizar(venda);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
