package br.aeso.exercicio.vendedor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RepositorioVendedorArrayList implements IRepositorioVendedor{
	private ArrayList<Vendedor> Vendedors;
	private ConnectarDBVendedor banco;
	public RepositorioVendedorArrayList() throws ClassNotFoundException, IOException, SQLException {
		this.banco = new ConnectarDBVendedor();
	}
	
	public void cadastrar(Vendedor Vendedor) throws IOException{
		try {
			this.banco.cadastrar(Vendedor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Vendedor> listar(){
		try {
			this.Vendedors = this.banco.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.Vendedors;
	}
	public boolean remover(Vendedor Vendedor)  throws VendedorNaoEncontradoException, IOException{
		int index = this.Vendedors.indexOf(Vendedor);
		if(index == -1){
			throw new VendedorNaoEncontradoException();
		}
		try {
			this.banco.remover(Vendedor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.listar();
		return true;
	}
	public boolean remover(double codigo) throws IOException{
		for(Vendedor Vendedor : this.Vendedors){
			if(Vendedor.getCodigo() == codigo){
				try {
					this.banco.remover(Vendedor);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.listar();
				return true;
			}
		}
		return false;
	}
	public Vendedor procurar(double codigo) throws SQLException{
		Map<String, Object> valores = new HashMap<String, Object>();
		valores.put("Codigo", codigo);
		ArrayList<Vendedor> lista = null;
		lista = this.banco.procurar(valores);
		
		if(lista == null){
			return null;
		}
		Vendedor Vendedor = lista.get(0);
		return Vendedor;
		
	}
	public Vendedor procurar(Vendedor Vendedor) throws SQLException{
		Map<String, Object> valores = new HashMap<String, Object>();
		valores.put("Codigo", Vendedor.getCodigo());
		ArrayList<Vendedor> lista = null;
		lista = this.banco.procurar(valores);
		
		if(lista == null){
			return null;
		}
		Vendedor VendedorResp = lista.get(0);
		return VendedorResp;
	}
	public void atualizar(Vendedor Vendedor) throws IOException{
		if(!this.Vendedors.contains(Vendedor)){
			this.cadastrar(Vendedor);
		}
		try {
			this.banco.atualizar(Vendedor);
			this.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
