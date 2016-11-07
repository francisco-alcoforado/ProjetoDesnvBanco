package br.aeso.exercicio.produto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.aeso.exercicio.produto.Produto;
import br.aeso.exercicio.produto.ProdutoNaoEncontradoException;
import br.aeso.exercicio.fornecedor.FornecedorNaoEncontradoException;
import br.aeso.exercicio.produto.ConnectarDBProduto;

public class RepositorioProdutoArrayList implements IRepositorioProduto{
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	private ConnectarDBProduto banco;
	
	public RepositorioProdutoArrayList() throws ClassNotFoundException, SQLException {
		this.banco = new ConnectarDBProduto();
	}
	
	public void cadastrar(Produto produto) throws IOException{
		try {
			this.banco.cadastrar(produto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Produto> listar(){
		try {
			this.produtos = this.banco.listar();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FornecedorNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.produtos;
	}
	public boolean remover(Produto produto)  throws ProdutoNaoEncontradoException, IOException{
		try {
			this.banco.remover(produto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public boolean remover(int codigo) throws IOException{
		try {
			this.banco.remover(codigo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.listar();
		return true;
	}
	public Produto procurar(double codigo){
		Map<String, Object> valores = new HashMap<String, Object>();
		valores.put("Codigo", codigo);
		ArrayList<Produto> lista = null;
		try {
			lista = this.banco.procurar(valores);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FornecedorNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Produto produto = lista.get(0);
		return produto;
		
	}
	public Produto procurar(Produto produto){
		Map<String, Object> valores = new HashMap<String, Object>();
		valores.put("Codigo", produto.getCodigo());
		ArrayList<Produto> lista = null;
		try {
			lista = this.banco.procurar(valores);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FornecedorNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Produto produtoResp = lista.get(0);
		return produtoResp;
	}
	public void atualizar(Produto produto) throws IOException{
		if(!this.produtos.contains(produtos)){
			this.cadastrar(produto);
		}
		try {
			this.banco.atualizar(produto);
			this.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
