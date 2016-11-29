package br.aeso.exercicio.notaFiscal;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.aeso.exercicio.notaFiscal.NotaFiscal;
import br.aeso.exercicio.notaFiscal.NotaFiscalNaoEncontradaException;
import br.aeso.exercicio.pedido.PedidoNaoEncontradoException;
import br.aeso.exercicio.vendedor.VendedorNaoEncontradoException;
import br.aeso.exercicio.cliente.ClienteNaoExncontradoException;
import br.aeso.exercicio.notaFiscal.ConnectarDBNotaFiscal;

public class RepositorioNotaFiscalArrayList implements IRepositorioNotaFiscal{
	private ArrayList<NotaFiscal> notaFiscals;
	
	private ConnectarDBNotaFiscal banco;
	public RepositorioNotaFiscalArrayList() throws ClassNotFoundException, IOException, SQLException {
		this.banco = new ConnectarDBNotaFiscal();
	}
	
	public void cadastrar(NotaFiscal notaFiscal){
		try {
			this.banco.cadastrar(notaFiscal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<NotaFiscal> listar() throws SQLException, ClassNotFoundException, PedidoNaoEncontradoException, IOException, ClienteNaoExncontradoException, VendedorNaoEncontradoException{
		return this.banco.listar();
	}
	public boolean remover(NotaFiscal notaFiscal)  throws NotaFiscalNaoEncontradaException, IOException{
		try {
			this.banco.remover(notaFiscal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public boolean remover(int codigo){
		try {
			this.banco.remover(codigo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public NotaFiscal procurar(int codigo) throws ClassNotFoundException, SQLException, PedidoNaoEncontradoException, IOException{
		Map<String, Object> valores = new HashMap<String, Object>();
		valores.put("Codigo", codigo);
		ArrayList<NotaFiscal> lista = null;
		try {
			lista = this.banco.procurar(valores);
		} catch (ClienteNaoExncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VendedorNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(lista == null){
			return null;
		}
		
		NotaFiscal notaFiscal = lista.get(0);
		return notaFiscal;
		
	}
	public NotaFiscal procurar(NotaFiscal notaFiscal) throws ClassNotFoundException, SQLException, PedidoNaoEncontradoException, IOException{
		Map<String, Object> valores = new HashMap<String, Object>();
		valores.put("Codigo", notaFiscal.getCodigo());
		ArrayList<NotaFiscal> lista = null;
		try {
			lista = this.banco.procurar(valores);
		} catch (ClienteNaoExncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VendedorNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(lista == null){
			return null;
		}
		
		NotaFiscal notaFiscalResp = lista.get(0);
		return notaFiscalResp;
	}
	public void atualizar(NotaFiscal notaFiscal){
		try {
			this.banco.atualizar(notaFiscal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
