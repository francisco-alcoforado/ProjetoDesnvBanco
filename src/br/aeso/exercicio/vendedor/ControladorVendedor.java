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
		if(this.procurar(Vendedor.getCodigo()) != null){
			throw new VendedorJaCadastradoException();
		}
		
		//Imprimir as informações do Vendedor.
		this.repositorio.cadastrar(Vendedor);
	}
	public void atualizar(Vendedor Vendedor) throws IOException, VendedorNaoEncontradoException, SQLException{
		if(this.procurar(Vendedor.getCodigo()) == null){
			throw new VendedorNaoEncontradoException();
		}
		this.repositorio.atualizar(Vendedor);
	}
	public boolean remover(int codigo) throws VendedorNaoEncontradoException, IOException{
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
		ArrayList<Vendedor> lista = new ArrayList<Vendedor>();
		return lista;
	}
}
