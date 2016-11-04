package br.aeso.exercicio.vendedor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import br.aeso.exercicio.database.BancoDeDados;

public class ConnectarDBVendedor {
	private BancoDeDados banco;
	public ConnectarDBVendedor() throws SQLException {
		this.banco = BancoDeDados.getBancoDeDados("jdbc:mysql://servidor/BancoProjeto", "root", "");
		
	}

	public ArrayList<Vendedor> listar() throws SQLException{
		String sql = "SELECT * FROM Vendedor";
		ResultSet rst = this.banco.listar(sql);
		ArrayList<Vendedor> lista = new ArrayList<Vendedor>();
		while(rst.next()){
			ArrayList<EmailVendedor> emails = this.getEmails(rst.getInt("Codigo"));
			ArrayList<TelefoneVendedor> telefones = this.getTelefones(rst.getInt("Codigo"));
			Vendedor Vendedor = new Vendedor(rst.getInt("Codigo"), rst.getString("Nome"), rst.getString("senha"), rst.getString("CPF"), rst.getString("Rua"), rst.getString("Bairro"), rst.getString("Cidade"), rst.getString("CEP"), rst.getInt("Numero"), rst.getString("Complemeto"), telefones, emails);
			lista.add(Vendedor);
		}
		return lista;
	}
	private ArrayList<EmailVendedor> getEmails(int codigo) throws SQLException{
		String sql = "SELECT * FROM Email_Vendedor WHERE Codigo_Vendedor = " + codigo;
		ResultSet rst = this.banco.listar(sql);
		ArrayList<EmailVendedor> lista = new ArrayList<EmailVendedor>();
		while(rst.next()){
			EmailVendedor email = new EmailVendedor(rst.getInt("Codigo"), rst.getString("Email"), rst.getInt("Priamrio"));
			lista.add(email);
		}
		return lista;
	}
	private ArrayList<TelefoneVendedor> getTelefones(int codigo) throws SQLException{
		String sql = "SELECT * FROM Telefone_Vendedor WHERE Codigo_Vendedor = " + codigo;
		ResultSet rst = this.banco.listar(sql);
		ArrayList<TelefoneVendedor> lista = new ArrayList<TelefoneVendedor>();
		while(rst.next()){
			TelefoneVendedor telefone = new TelefoneVendedor(rst.getInt("Codigo"), rst.getString("Telefone"));
			lista.add(telefone);
		}
		return lista;
	}
	public void cadastrar(Vendedor Vendedor) throws SQLException{
		String sql = "INSERT INTO Vendedor (Nome, Senha, CPF, Rua, Bairro, Cidade, CEP, Numero, Complemento) VALUE ('" + Vendedor.getNome() + "', '" + Vendedor.getSenha() + "', '" + Vendedor.getCpf() + "', '" + Vendedor.getRua() + "', '" + Vendedor.getBairro() + "', '" + Vendedor.getCidade() + "', '" + Vendedor.getCEP()  + "', " + Vendedor.getNumero() + ", '" + Vendedor.getComplemento() + "')";
		this.banco.cadastrar(sql);
		for(EmailVendedor email : Vendedor.getEmails()){
			String sqlEmail = "INSERT INTO Email_Vendedor (Email, Primario, Codigo_Vendedor) VALUES ('" + email.getEmail() + "', " + email.getPrimario() + ", " + Vendedor.getCodigo() +")";;
			this.banco.cadastrar(sqlEmail);
		}
		for(TelefoneVendedor telefone : Vendedor.getTelefones()){
			String sqlTelefone = "INSERT INTO Telefone_Vendedor (Email, Codigo_Vendedor) VALUES ('" + telefone.getTelefone() + "', " + Vendedor.getCodigo() +")";
			this.banco.cadastrar(sqlTelefone);
		}
	}
	public void atualizar(Vendedor Vendedor) throws SQLException{
		String sql = "UPDATE Vendedor SET Nome = '" + Vendedor.getNome() + "', Senha =  " + Vendedor.getSenha() + ",CPF = '" + Vendedor.getCpf() + "', Rua = '" + 
	Vendedor.getRua() + "', Bairro = '" + Vendedor.getBairro() + "', Cidade = '" + Vendedor.getCidade() + "', CEP = '"+ Vendedor.getCEP()
	+"', Numero = " + Vendedor.getNumero() + ", Complemento = '" + Vendedor.getComplemento() + "' WHERE Codigo = " + Vendedor.getCodigo();
		this.banco.atualizar(sql);
		for(EmailVendedor email : Vendedor.getEmails()){
			String sqlEmail = "UPDATE Email_Vendedor SET email = '" + email.getEmail() + "', " + email.getPrimario() + ", WHERE Codigo = " + email.getCodigo();
			this.banco.atualizar(sqlEmail);
		}
		for(TelefoneVendedor telefone : Vendedor.getTelefones()){
			String sqlTelefone = "UPDATE Telefone_Vendedor SET Telefone = '" + telefone.getTelefone() + "' WHERE Codigo = " + telefone.getCodigo();
			this.banco.atualizar(sqlTelefone);
		}
	}
	public void remover(Vendedor Vendedor) throws SQLException{
		String sql = "DELETE FROM Vendedor WHERE Codigo = " + Vendedor.getCodigo();
		String sqlEmail = "DELETE FROM Email_Vendedor WHERE Codigo_Vendedor = " + Vendedor.getCodigo();
		String sqlTelefone = "DELETE FROM Telefone_Vendedor WHERE Codigo_Vendedor = " + Vendedor.getCodigo();
		this.banco.remove(sqlEmail);
		this.banco.remove(sqlTelefone);
		this.banco.remove(sql);
	}
	public ArrayList<Vendedor> procurar(Map<String, Object> valores) throws SQLException{
		String sql = "SELECT * FROM Vendedor WHERE ";
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
		ArrayList<Vendedor> lista = new ArrayList<Vendedor>(); 
		while(rst.next()){
			ArrayList<EmailVendedor> emails = this.getEmails(rst.getInt("Codigo"));
			ArrayList<TelefoneVendedor> telefones = this.getTelefones(rst.getInt("Codigo"));
			Vendedor Vendedor = new Vendedor(rst.getInt("Codigo"), rst.getString("Nome"), rst.getString("senha"), rst.getString("CPF"), rst.getString("Rua"), rst.getString("Bairro"), rst.getString("Cidade"), rst.getString("CEP"), rst.getInt("Numero"), rst.getString("Complemeto"), telefones, emails);
			lista.add(Vendedor);
		}
		
		if(lista.size() == 0){
			return null;
		}
		return lista;
	}
}
