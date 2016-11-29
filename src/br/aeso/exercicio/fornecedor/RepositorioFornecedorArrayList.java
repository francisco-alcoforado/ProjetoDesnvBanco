package br.aeso.exercicio.fornecedor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RepositorioFornecedorArrayList implements IRepositorioFornecedor{
	private ArrayList<Fornecedor> fornecedores;
	private ConnectarDBFornecedor banco;
	public RepositorioFornecedorArrayList() throws ClassNotFoundException, IOException, SQLException {
		this.banco = new ConnectarDBFornecedor();
	}
	
	public void cadastrar(Fornecedor fornecedor) throws IOException{
		try {
			this.banco.cadastrar(fornecedor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Fornecedor> listar(){
		try {
			this.fornecedores = this.banco.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.fornecedores;
	}
	public boolean remover(Fornecedor fornecedor) throws IOException{
		int index = this.fornecedores.indexOf(fornecedor);
		if(index == -1){
			return false;
		}
		try {
			this.banco.remover(fornecedor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.listar();
		return true;
	}
	public boolean remover(int codigo) throws IOException{
		try {
			this.banco.remover(codigo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public Fornecedor procurar(double codigo) throws SQLException{
		Map<String, Object> valores = new HashMap<String, Object>();
		valores.put("Codigo", codigo);
		ArrayList<Fornecedor> lista = null;
		lista = this.banco.procurar(valores);
		
		if(lista == null){
			return null;
		}
		
		Fornecedor fornecedor = lista.get(0);
		return fornecedor;
	}
	public Fornecedor procurar(Fornecedor fornecedor) throws SQLException{
		Map<String, Object> valores = new HashMap<String, Object>();
		valores.put("Codigo", fornecedor.getCodigo());
		ArrayList<Fornecedor> lista = null;
		lista = this.banco.procurar(valores);
		
		if(lista == null){
			return null;
		}
		
		Fornecedor fornecedorResp = lista.get(0);
		return fornecedorResp;
	}
	public void atualizar(Fornecedor fornecedor) throws IOException{
		try {
			this.banco.atualizar(fornecedor);
			this.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
