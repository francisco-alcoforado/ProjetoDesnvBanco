package br.aeso.exercicio.fornecedor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import br.aeso.exercicio.database.BancoDeDados;

public class ConnectarDBFornecedor {
private BancoDeDados banco;
	
	public ConnectarDBFornecedor() throws SQLException {
		this.banco = BancoDeDados.getBancoDeDados("jdbc:mysql://localhost/projeto_banco", "root", "cavaco");
		
	}

	public ArrayList<Fornecedor> listar() throws SQLException{
		String sql = "SELECT * FROM Fornecedor";
		ResultSet rst = this.banco.listar(sql);
		ArrayList<Fornecedor> lista = new ArrayList<Fornecedor>();
		while(rst.next()){
			ArrayList<EmailFornecedor> emails = this.getEmails(rst.getInt("Codigo"));
			ArrayList<TelefoneFornecedor> telefones = this.getTelefones(rst.getInt("Codigo"));
			Fornecedor cliente = new Fornecedor(rst.getInt("Codigo"), rst.getString("Nome"), emails, telefones);
			lista.add(cliente);
		}
		return lista;
	}
	private ArrayList<EmailFornecedor> getEmails(int codigo) throws SQLException{
		String sql = "SELECT * FROM Email_Fornecedor WHERE Codigo_Fornecedor = " + codigo;
		ResultSet rst = this.banco.listar(sql);
		ArrayList<EmailFornecedor> lista = new ArrayList<EmailFornecedor>();
		while(rst.next()){
			EmailFornecedor email = new EmailFornecedor(rst.getInt("Codigo"), rst.getString("Email"), rst.getInt("Primario"));
			lista.add(email);
		}
		return lista;
	}
	private ArrayList<TelefoneFornecedor> getTelefones(int codigo) throws SQLException{
		String sql = "SELECT * FROM Telefone_Fornecedor WHERE Codigo_Fornecedor = " + codigo;
		ResultSet rst = this.banco.listar(sql);
		ArrayList<TelefoneFornecedor> lista = new ArrayList<TelefoneFornecedor>();
		while(rst.next()){
			TelefoneFornecedor telefone = new TelefoneFornecedor(rst.getInt("Codigo"), rst.getString("Telefone"));
			lista.add(telefone);
		}
		return lista;
	}
	public void cadastrar(Fornecedor fornecedor) throws SQLException{
		String sql = "INSERT INTO Fornecedor (Nome) VALUES ('" + fornecedor.getNome() + "')";
		int codigo = this.banco.cadastrar(sql);
		for(EmailFornecedor email : fornecedor.getEmails()){
			String sqlEmail = "INSERT INTO Email_Fornecedor (Email, Primario, Codigo_Fornecedor) VALUES ('" + email.getEmail() + "', " + email.getPrimario() + ", " + codigo +")";
			this.banco.cadastrar(sqlEmail);
		}
		for(TelefoneFornecedor telefone : fornecedor.getTelefones()){
			String sqlTelefone = "INSERT INTO Telefone_Fornecedor (Telefone, Codigo_Fornecedor) VALUES ('" + telefone.getTelefone() + "', " + codigo +")";
			this.banco.cadastrar(sqlTelefone);
		}
	}
	public void atualizar(Fornecedor fornecedor) throws SQLException{
		String sql = "UPDATE Fornecedor SET Nome = '" + fornecedor.getNome() + "' WHERE Codigo = " + fornecedor.getCodigo();
		this.banco.atualizar(sql);
		for(EmailFornecedor email : fornecedor.getEmails()){
			String sqlEmail = "UPDATE Email_Fornecedor SET email = '" + email.getEmail() + "', " + email.getPrimario() + ", WHERE Codigo = " + email.getCodigo();
			this.banco.atualizar(sqlEmail);
		}
		for(TelefoneFornecedor telefone : fornecedor.getTelefones()){
			String sqlTelefone = "UPDATE Telefone_Fornecedor SET Telefone = '" + telefone.getTelefone() + "' WHERE Codigo = " + telefone.getCodigo();
			this.banco.atualizar(sqlTelefone);
		}
	}
	public void remover(Fornecedor fornecedor) throws SQLException{
		String sqlEmail = "DELETE FROM Email_Fornecedor WHERE Codigo_Fornecedor = " + fornecedor.getCodigo();
		String sqlTelefone = "DELETE FROM Telefone_Fornecedor WHERE Codigo_Fornecedor = " + fornecedor.getCodigo();
		String sql = "DELETE FROM Fornecedor WHERE Codigo = " + fornecedor.getCodigo();
		this.banco.remove(sqlEmail);
		this.banco.remove(sqlTelefone);
		this.banco.remove(sql);
	}
	public void remover(int codigo) throws SQLException{
		System.out.println(codigo);
		String sqlEmail = "DELETE FROM Email_Fornecedor WHERE Codigo_Fornecedor = " + codigo;
		String sqlTelefone = "DELETE FROM Telefone_Fornecedor WHERE Codigo_Fornecedor = " + codigo;
		String sql = "DELETE FROM Fornecedor WHERE Codigo = " + codigo;
		this.banco.remove(sqlEmail);
		this.banco.remove(sqlTelefone);
		this.banco.remove(sql);
	}
	public ArrayList<Fornecedor> procurar(Map<String, Object> valores) throws SQLException{
		String sql = "SELECT * FROM Fornecedor WHERE ";
		int i = 0;
		for(String key : valores.keySet()){
			if(i == 0){
				if(valores.get(key) instanceof Integer || valores.get(key) instanceof Double){
					sql += key + " = " + valores.get(key);
				}else if (valores.get(key) instanceof String){
					sql += key + " = '" + valores.get(key) + "'";
				}
			}else{
				if(valores.get(key) instanceof Integer || valores.get(key) instanceof Double){
					sql += ", " + key + " = " + valores.get(key);
				}else if(valores.get(key) instanceof String){
					sql += ", " + key + " = '" + valores.get(key) + "'";
				}
			}
			i++;
		}
		ResultSet rst = this.banco.listar(sql);
		ArrayList<Fornecedor> lista = new ArrayList<Fornecedor>(); 
		while(rst.next()){
			ArrayList<EmailFornecedor> emails = this.getEmails(rst.getInt("Codigo"));
			ArrayList<TelefoneFornecedor> telefones = this.getTelefones(rst.getInt("Codigo"));
			Fornecedor fornecedor = new Fornecedor(rst.getInt("Codigo"), rst.getString("Nome"), emails, telefones);
			lista.add(fornecedor);
		}
		
		if(lista.size() == 0){
			return null;
		}
		return lista;
	}
}
