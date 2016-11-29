package br.aeso.exercicio.cliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import br.aeso.exercicio.database.BancoDeDados;

public class ConnectarDBCliente {
	private BancoDeDados banco;
	
	public ConnectarDBCliente() throws SQLException {
		this.banco = BancoDeDados.getBancoDeDados("jdbc:mysql://localhost/projeto_banco", "root", "cavaco");
		
	}

	public ArrayList<Cliente> listar() throws SQLException{
		String sql = "SELECT * FROM Cliente";
		ResultSet rst = this.banco.listar(sql);
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		while(rst.next()){
			ArrayList<EmailCliente> emails = this.getEmails(rst.getInt("Codigo"));
			ArrayList<TelefoneCliente> telefones = this.getTelefones(rst.getInt("Codigo"));
			Cliente cliente = new Cliente(rst.getInt("Codigo"), rst.getString("Nome"), rst.getString("CPF"), rst.getString("Rua"), rst.getString("Bairro"), rst.getString("Cidade"), rst.getString("CEP"), rst.getInt("Numero"), rst.getString("Complemento"), telefones, emails);
			lista.add(cliente);
		}
		return lista;
	}
	private ArrayList<EmailCliente> getEmails(int codigo) throws SQLException{
		String sql = "SELECT * FROM Email_Cliente WHERE Codigo_Cliente = " + codigo;
		ResultSet rst = this.banco.listar(sql);
		ArrayList<EmailCliente> lista = new ArrayList<EmailCliente>();
		while(rst.next()){
			EmailCliente email = new EmailCliente(rst.getInt("Codigo"), rst.getString("Email"), rst.getInt("Primario"));
			lista.add(email);
		}
		return lista;
	}
	private ArrayList<TelefoneCliente> getTelefones(int codigo) throws SQLException{
		String sql = "SELECT * FROM Telefone_Cliente WHERE Codigo_Cliente = " + codigo;
		ResultSet rst = this.banco.listar(sql);
		ArrayList<TelefoneCliente> lista = new ArrayList<TelefoneCliente>();
		while(rst.next()){
			TelefoneCliente telefone = new TelefoneCliente(rst.getInt("Codigo"), rst.getString("Telefone"));
			lista.add(telefone);
		}
		return lista;
	}
	public void cadastrar(Cliente cliente) throws SQLException{
		String sql = "INSERT INTO Cliente (Nome, CPF, Rua, Bairro, Cidade, CEP, Numero, Complemento) VALUE ('" + cliente.getNome() + "', '" + cliente.getCpf() + "', '" + cliente.getRua() + "', '" + cliente.getBairro() + "', '" + cliente.getCidade() + "', '" + cliente.getCEP()  + "', " + cliente.getNumero() + ", '" + cliente.getComplemento() + "')";
		int codigo = this.banco.cadastrar(sql);
		for(EmailCliente email : cliente.getEmails()){
			String sqlEmail = "INSERT INTO Email_Cliente (Email, Primario, Codigo_Cliente) VALUES ('" + email.getEmail() + "', " + email.getPrimario() + ", " + codigo +")";;
			this.banco.cadastrar(sqlEmail);
		}
		for(TelefoneCliente telefone : cliente.getTelefones()){
			String sqlTelefone = "INSERT INTO Telefone_Cliente (Telefone, Codigo_Cliente) VALUES ('" + telefone.getTelefone() + "', " + codigo +")";
			this.banco.cadastrar(sqlTelefone);
		}
	}
	public void atualizar(Cliente cliente) throws SQLException{
		String sql = "UPDATE Cliente SET Nome = '" + cliente.getNome() + "', CPF = '" + cliente.getCpf() + "', Rua = '" + 
	cliente.getRua() + "', Bairro = '" + cliente.getBairro() + "', Cidade = '" + cliente.getCidade() + "', CEP = '"+ cliente.getCEP()
	+"', Numero = " + cliente.getNumero() + ", Complemento = '" + cliente.getComplemento() + "' WHERE Codigo = " + cliente.getCodigo();
		this.banco.atualizar(sql);
		for(EmailCliente email : cliente.getEmails()){
                        ResultSet rs = this.banco.listar("SELECT COUNT(*) as quant FROM Email_Cliente WHERE Codigo = " + email.getCodigo() );
                        rs.next();
                        if(rs.getInt("quant") == 1){
                            String sqlEmail = "UPDATE Email_Cliente SET email = '" + email.getEmail() + "', Primario = " + email.getPrimario() + ", WHERE Codigo = " + email.getCodigo();
                            this.banco.atualizar(sqlEmail);
                        }else{
                            String sqlEmail = "INSERT INTO Email_Cliente (Email, Primario, Codigo_Cliente) VALUES ('" + email.getEmail() + "', " + email.getPrimario() + ", " + cliente.getCodigo() +")";;
                            this.banco.cadastrar(sqlEmail);
                        }
		}
		for(TelefoneCliente telefone : cliente.getTelefones()){
                        ResultSet rs = this.banco.listar("SELECT COUNT(*) as quant FROM Telefone_Cliente WHERE Codigo = " + telefone.getCodigo() );
                        rs.next();
                        if(rs.getInt("quant") == 1){
                            String sqlTelefone = "UPDATE Telefone_Cliente SET Telefone = '" + telefone.getTelefone() + "' WHERE Codigo = " + telefone.getCodigo();
                            this.banco.atualizar(sqlTelefone);
                        }else{
                            String sqlTelefone = "INSERT INTO Telefone_Cliente (Telefone, Codigo_Cliente) VALUES ('" + telefone.getTelefone() + "', " + cliente.getCodigo() +")";
                            this.banco.cadastrar(sqlTelefone);
                        }
		}
	}
	public boolean remover(Cliente cliente) throws SQLException{
		String sql = "DELETE FROM Cliente WHERE Codigo = " + cliente.getCodigo();
		String sqlEmail = "DELETE FROM Email_Cliente WHERE Codigo_Cliente = " + cliente.getCodigo();
		String sqlTelefone = "DELETE FROM Telefone_Cliente WHERE Codigo_Cliente = " + cliente.getCodigo();
		this.banco.remove(sqlEmail);
		this.banco.remove(sqlTelefone);
		this.banco.remove(sql);
		return true;
	}
	public boolean remover(int codigo) throws SQLException{
		System.out.println(codigo);
		String sql = "DELETE FROM Cliente WHERE Codigo = " + codigo;
		String sqlEmail = "DELETE FROM Email_Cliente WHERE Codigo_Cliente = " + codigo;
		String sqlTelefone = "DELETE FROM Telefone_Cliente WHERE Codigo_Cliente = " +codigo;
		this.banco.remove(sqlEmail);
		this.banco.remove(sqlTelefone);
		this.banco.remove(sql);
		return true;
	}
	public ArrayList<Cliente> procurar(Map<String, Object> valores) throws SQLException{
		String sql = "SELECT * FROM Cliente WHERE ";
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
		ArrayList<Cliente> lista = new ArrayList<Cliente>(); 
		while(rst.next()){
			ArrayList<EmailCliente> emails = this.getEmails(rst.getInt("Codigo"));
			ArrayList<TelefoneCliente> telefones = this.getTelefones(rst.getInt("Codigo"));
			Cliente cliente = new Cliente(rst.getInt("Codigo"), rst.getString("Nome"), rst.getString("CPF"), rst.getString("Rua"), rst.getString("Bairro"), rst.getString("Cidade"), rst.getString("CEP"), rst.getInt("Numero"), rst.getString("Complemento"), telefones, emails);
			lista.add(cliente);
		}
		
		if(lista.size() == 0){
			return null;
		}
		
		return lista;
	}
}
