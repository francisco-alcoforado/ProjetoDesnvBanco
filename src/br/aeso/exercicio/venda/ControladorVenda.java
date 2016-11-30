package br.aeso.exercicio.venda;

import br.aeso.exercicio.fachada.Fachada;
import br.aeso.exercicio.pedido.Pedido;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.aeso.exercicio.venda.Venda;
import br.aeso.exercicio.venda.VendaJaCadastradaException;
import br.aeso.exercicio.venda.VendaNaoEncontradaException;
import br.aeso.exercicio.pedido.PedidoNaoEncontradoException;
import br.aeso.exercicio.produto.ProdutoNaoEncontradoException;
import br.aeso.exercicio.venda.IRepositorioVenda;
import br.aeso.exercicio.venda.RepositorioVendaArrayList;

public class ControladorVenda {
	
	private IRepositorioVenda repositorio;
	public ControladorVenda() throws ClassNotFoundException, IOException, SQLException {
		this.repositorio = new RepositorioVendaArrayList();
	}
	public IRepositorioVenda getRepositorio() {
		return repositorio;
	}
	public void setRepositorio(IRepositorioVenda repositorio) {
		this.repositorio = repositorio;
	}

	public void cadastrar(Venda venda) throws IllegalArgumentException, VendaJaCadastradaException, VendaNaoEncontradaException, IOException, ClassNotFoundException, SQLException, ProdutoNaoEncontradoException, PedidoNaoEncontradoException{
		if(venda == null){
			throw new IllegalArgumentException();
		}
		
		//Imprimir as informacoes do venda.
		this.repositorio.cadastrar(venda);
                Fachada fachada = new Fachada();
                Pedido pedido = venda.getPedido();
                double valor = pedido.getValor() + venda.getValor();
                pedido.setValor(valor);
                fachada.atualizarPedido(pedido);
	}
	public void atualizar(Venda venda) throws IOException{
		this.repositorio.atualizar(venda);
	}
	public boolean remover(int codigo) throws VendaNaoEncontradaException, IOException{
		boolean retorno = this.repositorio.remover(codigo);
		if(retorno == false){
			throw new VendaNaoEncontradaException();
		}else{
			return true;
		}
	}
	public Venda procurar(int codigo) throws VendaNaoEncontradaException, ClassNotFoundException, SQLException, ProdutoNaoEncontradoException, IOException, PedidoNaoEncontradoException{
		Venda venda = this.repositorio.procurar(codigo);
		if(venda == null){
			throw new VendaNaoEncontradaException();
		}
		
		return venda;
	}
	public ArrayList<Venda> listar() throws ClassNotFoundException, SQLException, ProdutoNaoEncontradoException, IOException, PedidoNaoEncontradoException{
		ArrayList<Venda> lista = this.repositorio.listar();
		return lista;
	}
}
