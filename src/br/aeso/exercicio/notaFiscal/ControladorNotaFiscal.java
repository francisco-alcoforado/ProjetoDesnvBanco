package br.aeso.exercicio.notaFiscal;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.aeso.exercicio.cliente.ClienteNaoExncontradoException;
import br.aeso.exercicio.pedido.PedidoNaoEncontradoException;
import br.aeso.exercicio.vendedor.VendedorNaoEncontradoException;

public class ControladorNotaFiscal {
	private IRepositorioNotaFiscal repositorio;
	
	public ControladorNotaFiscal() throws ClassNotFoundException, IOException, SQLException {
		this.repositorio = new RepositorioNotaFiscalArrayList();
	}
	
	public IRepositorioNotaFiscal getRepositorio() {
		return repositorio;
	}


	public void setRepositorio(IRepositorioNotaFiscal repositorio) {
		this.repositorio = repositorio;
	}

	public void cadastrar(NotaFiscal notaFiscal) throws IllegalArgumentException, NotaFiscalJaCadastradaException, NotaFiscalNaoEncontradaException, IOException, ClassNotFoundException, SQLException, PedidoNaoEncontradoException{
		//Imprimir as informa��es do cliente.
		if(notaFiscal == null){
			throw new IllegalArgumentException();
		}
		this.repositorio.cadastrar(notaFiscal);
	}
	public void atualizar(NotaFiscal notaFiscal) throws IOException{
		this.repositorio.atualizar(notaFiscal);
	}
	public boolean remover(int codigo) throws NotaFiscalNaoEncontradaException, IOException{
		boolean retorno = this.repositorio.remover(codigo);
		if(retorno == false){
			throw new NotaFiscalNaoEncontradaException();
		}
		return retorno;
	}
	public NotaFiscal procurar(int codigo) throws NotaFiscalNaoEncontradaException, ClassNotFoundException, SQLException, PedidoNaoEncontradoException, IOException{
		NotaFiscal fornecedor = this.repositorio.procurar(codigo);
		if(fornecedor == null){
			throw new NotaFiscalNaoEncontradaException();
		}
		return fornecedor;
	}
	public ArrayList<NotaFiscal> listar() throws ClassNotFoundException, SQLException, PedidoNaoEncontradoException, IOException, ClienteNaoExncontradoException, VendedorNaoEncontradoException{
		ArrayList<NotaFiscal> lista = this.repositorio.listar();
		return lista;
	}
}
