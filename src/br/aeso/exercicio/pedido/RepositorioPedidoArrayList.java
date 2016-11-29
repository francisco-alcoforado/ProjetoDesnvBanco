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
	public ArrayList<Pedido> listar() throws ClassNotFoundException, SQLException, ClienteNaoExncontradoException, IOException, VendedorNaoEncontradoException{
		System.out.println("Cheguei");
		return this.banco.listar();
		
	}
        public ArrayList<Pedido> listar(String orderBy) throws ClassNotFoundException, SQLException, ClienteNaoExncontradoException, IOException, VendedorNaoEncontradoException{
		System.out.println("Cheguei");
		return this.banco.listar(orderBy);
		
	}
	public boolean remover(Pedido pedido)  throws PedidoNaoEncontradoException, IOException{
		try {
			this.banco.remover(pedido);
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
        public ArrayList<Pedido> procurar(Map<String, Object> valores, String order) throws ClassNotFoundException, SQLException, ClienteNaoExncontradoException, IOException, VendedorNaoEncontradoException{
		ArrayList<Pedido> lista = null;
		lista = this.banco.procurar(valores, order);
		
		if(lista == null){
			return null;
		}
                return lista;		
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
		try {
			this.banco.atualizar(pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
