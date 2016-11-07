package br.aeso.exercicio.cliente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RepositorioClienteArrayList implements IRepositorioCliente{
	private ArrayList<Cliente> clientes;
	private ConnectarDBCliente banco;
	public RepositorioClienteArrayList() throws ClassNotFoundException, IOException, SQLException {
		this.banco = new ConnectarDBCliente();
	}
	
	public void cadastrar(Cliente cliente) throws IOException{
		try {
			this.banco.cadastrar(cliente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Cliente> listar(){
		try {
			this.clientes = this.banco.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.clientes;
	}
	/*public boolean remover(Cliente cliente)  throws ClienteNaoExncontradoException, IOException{
		try {
			this.banco.remover(cliente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.listar();
		return true;
	}*/
	public boolean remover(int codigo) throws IOException{
		try {
			this.banco.remover(codigo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public Cliente procurar(double codigo) throws SQLException{
		Map<String, Object> valores = new HashMap<String, Object>();
		valores.put("Codigo", codigo);
		ArrayList<Cliente> lista = null;
		
		lista = this.banco.procurar(valores);
		if(lista == null){
			return null;
		}
		Cliente cliente = lista.get(0);
		return cliente;
		
	}
	public Cliente procurar(Cliente cliente) throws SQLException{
		Map<String, Object> valores = new HashMap<String, Object>();
		valores.put("Codigo", cliente.getCodigo());
		ArrayList<Cliente> lista = null;
		
		lista = this.banco.procurar(valores);
		if(lista == null){
			return null;
		}
		
		Cliente clienteResp = lista.get(0);
		return clienteResp;
	}
	public void atualizar(Cliente cliente) throws IOException{
		if(!this.clientes.contains(clientes)){
			this.cadastrar(cliente);
		}
		try {
			this.banco.atualizar(cliente);
			this.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
