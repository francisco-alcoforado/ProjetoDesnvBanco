package br.aeso.exercicio.pedido;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import br.aeso.exercicio.cliente.ClienteNaoExncontradoException;
import br.aeso.exercicio.vendedor.VendedorNaoEncontradoException;

public class RepositorioPedidoArrayList implements IRepositorioPedido{
	private ArrayList<Pedido> pedidos;
	private ConnectarDBPedido banco;
	public RepositorioPedidoArrayList() throws ClassNotFoundException, IOException, SQLException {
		this.banco = new ConnectarDBPedido();
	}
	
	public void cadastrar(Pedido pedido){
		try {
			this.banco.cadastrar(pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Pedido> listar(){
		try {
			this.pedidos = this.banco.listar();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClienteNaoExncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VendedorNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.pedidos;
	}
	public boolean remover(Pedido pedido)  throws PedidoNaoEncontradoException, IOException{
		int index = this.pedidos.indexOf(pedido);
		if(index == -1){
			throw new PedidoNaoEncontradoException();
		}
		try {
			this.banco.remover(pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.listar();
		return true;
	}
	public boolean remover(double codigo){
		for(Pedido pedido : this.pedidos){
			if(pedido.getCodigo() == codigo){
				try {
					this.banco.remover(pedido);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.listar();
				return true;
			}
		}
		return false;
	}
	public Pedido procurar(double codigo) throws ClassNotFoundException, SQLException, ClienteNaoExncontradoException, IOException, VendedorNaoEncontradoException{
		Map<String, Object> valores = new HashMap<String, Object>();
		valores.put("Codigo", codigo);
		ArrayList<Pedido> lista = null;
		lista = this.banco.procurar(valores);
		
		if(lista == null){
			return null;
		}
		
		Pedido pedido = lista.get(0);
		return pedido;
		
	}
	public Pedido procurar(Pedido pedido) throws ClassNotFoundException, SQLException, ClienteNaoExncontradoException, IOException, VendedorNaoEncontradoException{
		Map<String, Object> valores = new HashMap<String, Object>();
		valores.put("Codigo", pedido.getCodigo());
		ArrayList<Pedido> lista = null;		
		lista = this.banco.procurar(valores);
		
		if(lista == null){
			return null;
		}
		
		Pedido pedidoResp = lista.get(0);
		return pedidoResp;
	}
	public void atualizar(Pedido pedido){
		if(!this.pedidos.contains(pedidos)){
			this.cadastrar(pedido);
		}
		try {
			this.banco.atualizar(pedido);
			this.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
