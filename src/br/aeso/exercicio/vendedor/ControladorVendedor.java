package br.aeso.exercicio.vendedor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorVendedor {
	private IRepositorioVendedor repositorio;
	public ControladorVendedor() throws ClassNotFoundException, IOException, SQLException {
		this.repositorio = new RepositorioVendedorArrayList();
	}
	public IRepositorioVendedor getRepositorio() {
		return repositorio;
	}
	public void setRepositorio(IRepositorioVendedor repositorio) {
		this.repositorio = repositorio;
	}

	public void cadastrar(Vendedor Vendedor) throws IllegalArgumentException, VendedorJaCadastradoException, IOException, VendedorNaoEncontradoException, SQLException{
		if(Vendedor == null){
			throw new IllegalArgumentException();
		}
		//Imprimir as informacoes do Vendedor.
		this.repositorio.cadastrar(Vendedor);
	}
	public void atualizar(Vendedor Vendedor) throws IOException, VendedorNaoEncontradoException, SQLException{
		this.repositorio.atualizar(Vendedor);
	}
	public boolean remover(int codigo) throws VendedorNaoEncontradoException, IOException{
		System.out.println(codigo);
		boolean retorno = this.repositorio.remover(codigo);
		if(retorno == false){
			throw new VendedorNaoEncontradoException();
		}else{
			return true;
		}
	}
	public Vendedor procurar(int codigo) throws VendedorNaoEncontradoException, SQLException{
		Vendedor Vendedor = this.repositorio.procurar(codigo);
		if(Vendedor == null){
			throw new VendedorNaoEncontradoException();
		}
		
		return Vendedor;
	}
	public ArrayList<Vendedor> listar(){
		ArrayList<Vendedor> lista = this.repositorio.listar();
		return lista;
	}
}
