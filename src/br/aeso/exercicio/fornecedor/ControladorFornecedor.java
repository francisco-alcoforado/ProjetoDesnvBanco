package br.aeso.exercicio.fornecedor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.aeso.exercicio.util.CNPJInvalidoException;

public class ControladorFornecedor {
	private IRepositorioFornecedor repositorio;
	
	public ControladorFornecedor() throws ClassNotFoundException, IOException, SQLException {
		this.repositorio = new RepositorioFornecedorArrayList();
	}
	
	public IRepositorioFornecedor getRepositorio() {
		return repositorio;
	}
	public void setRepositorio(IRepositorioFornecedor repositorio) {
		this.repositorio = repositorio;
	}

	public void cadastrar(Fornecedor fornecedor) throws IllegalArgumentException, FornecedorJaCadastradoException, CNPJInvalidoException, FornecedorNaoEncontradoException, IOException, SQLException{
		//Imprimir as informacoes do cliente.
		if(fornecedor == null){
			throw new IllegalArgumentException();
		}
		System.out.println(fornecedor.getNome());
		this.repositorio.cadastrar(fornecedor);
	}
	public void atualizar(Fornecedor fornecedor) throws CNPJInvalidoException, IOException{
		
		this.repositorio.atualizar(fornecedor);
	}
	public boolean remover(int codigo) throws FornecedorNaoEncontradoException, IOException{
		boolean retorno = this.repositorio.remover(codigo);
		if(retorno == false){
			throw new FornecedorNaoEncontradoException();
		}
		return retorno;
	}
	public Fornecedor procurar(int codigo) throws FornecedorNaoEncontradoException, SQLException{
		Fornecedor fornecedor = this.repositorio.procurar(codigo);
		if(fornecedor == null){
			throw new FornecedorNaoEncontradoException();
		}
		return fornecedor;
	}
	public ArrayList<Fornecedor> listar(){
		ArrayList<Fornecedor> lista = this.repositorio.listar();
		return lista;
	}
}
