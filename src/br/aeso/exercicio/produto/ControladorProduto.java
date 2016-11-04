package br.aeso.exercicio.produto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorProduto {
	private IRepositorioProduto repositorio;
	
	public ControladorProduto() throws ClassNotFoundException, IOException, SQLException {
		this.repositorio = new RepositorioProdutoArrayList();
	}
	
	public IRepositorioProduto getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(IRepositorioProduto repositorio) {
		this.repositorio = repositorio;
	}

	public void cadastrar(Produto produto) throws IllegalArgumentException, ProdutoJaCadastradoException, ProdutoNaoEncontradoException, IOException{
		//Imprimir as informações do cliente.
		if(produto == null){
			throw new IllegalArgumentException();
		}
		
		this.repositorio.cadastrar(produto);
	}
	public void atualizar(Produto produto) throws IOException{
		this.repositorio.atualizar(produto);
	}
	public boolean remover(int codigo) throws ProdutoNaoEncontradoException, IOException{
		boolean retorno = this.repositorio.remover(codigo);
		if(retorno == false){
			throw new ProdutoNaoEncontradoException();
		}
		return retorno;
	}
	public Produto procurar(int codigo) throws ProdutoNaoEncontradoException{
		Produto fornecedor = this.repositorio.procurar(codigo);
		if(fornecedor == null){
			throw new ProdutoNaoEncontradoException();
		}
		return fornecedor;
	}
	public ArrayList<Produto> listar(){
		ArrayList<Produto> lista = this.repositorio.listar();
		return lista;
	}
}
