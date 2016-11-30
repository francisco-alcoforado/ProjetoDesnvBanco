package br.aeso.exercicio.pedido;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.aeso.exercicio.cliente.ClienteNaoExncontradoException;
import br.aeso.exercicio.vendedor.VendedorNaoEncontradoException;
import java.util.Map;

public class ControladorPedido {
	private IRepositorioPedido repositorio;
	
	public ControladorPedido() throws ClassNotFoundException, IOException, SQLException {
		this.repositorio = new RepositorioPedidoArrayList();
	}
	
	public IRepositorioPedido getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(IRepositorioPedido repositorio) {
		this.repositorio = repositorio;
	}

	public void cadastrar(Pedido pedido) throws IllegalArgumentException, PedidoJaCadastradoException, PedidoNaoEncontradoException, IOException, ClassNotFoundException, SQLException, ClienteNaoExncontradoException, VendedorNaoEncontradoException{
		//Imprimir as informacoes do cliente.
		if(pedido == null){
			throw new IllegalArgumentException();
		}
		this.repositorio.cadastrar(pedido);
	}
	public void atualizar(Pedido pedido) throws IOException{
		this.repositorio.atualizar(pedido);
	}
	public boolean remover(int codigo) throws PedidoNaoEncontradoException, IOException{
		boolean retorno = this.repositorio.remover(codigo);
		if(retorno == false){
			throw new PedidoNaoEncontradoException();
		}
		return retorno;
	}
	public Pedido procurar(int codigo) throws PedidoNaoEncontradoException, ClassNotFoundException, SQLException, ClienteNaoExncontradoException, IOException, VendedorNaoEncontradoException{
		Pedido pedido = this.repositorio.procurar(codigo);
		if(pedido == null){
			throw new PedidoNaoEncontradoException();
		}
		return pedido;
	}
        public ArrayList<Pedido> procurar(Map<String, Object> valores, String order) throws PedidoNaoEncontradoException, ClassNotFoundException, SQLException, ClienteNaoExncontradoException, IOException, VendedorNaoEncontradoException{
                RepositorioPedidoArrayList rep = (RepositorioPedidoArrayList) this.repositorio;
		ArrayList<Pedido> lista = rep.procurar(valores, order);
		if(lista == null){
			throw new PedidoNaoEncontradoException();
		}
		return lista;
	}
	public ArrayList<Pedido> listar() throws ClassNotFoundException, SQLException, ClienteNaoExncontradoException, IOException, VendedorNaoEncontradoException{
		System.out.println("Cheguei Controlador");
		ArrayList<Pedido> lista = this.repositorio.listar();
		System.out.println(lista.size());
		return lista;
	}
        public ArrayList<Pedido> listar(String orderBy) throws ClassNotFoundException, SQLException, ClienteNaoExncontradoException, IOException, VendedorNaoEncontradoException{
		System.out.println("Cheguei Controlador");
                RepositorioPedidoArrayList rep = (RepositorioPedidoArrayList) this.repositorio;
		ArrayList<Pedido> lista = rep.listar(orderBy);
		System.out.println(lista.size());
		return lista;
	}
}
