package br.aeso.exercicio.produto;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import br.aeso.exercicio.database.BancoDeDados;
import br.aeso.exercicio.fornecedor.ControladorFornecedor;
import br.aeso.exercicio.fornecedor.Fornecedor;
import br.aeso.exercicio.fornecedor.FornecedorNaoEncontradoException;

public class ConnectarDBProduto {
	private BancoDeDados banco;

	public ConnectarDBProduto() throws SQLException {
		this.banco = BancoDeDados.getBancoDeDados("jdbc:mysql://localhost/projeto_banco", "root", "cavaco");
	}

	public ArrayList<Produto> listar()
			throws SQLException, ClassNotFoundException, FornecedorNaoEncontradoException, IOException {
		String sql = "SELECT * FROM Produto";
		ResultSet rst = this.banco.listar(sql);
		ArrayList<Produto> lista = new ArrayList<Produto>();
		while (rst.next()) {
			Fornecedor fornecedor = this.getFornecedor(rst.getInt("Codigo_Fornecedor"));
			Produto produto = new Produto(rst.getInt("Codigo"), rst.getString("Nome"), rst.getDouble("Valor"),
					rst.getString("Categoria"), fornecedor);
			lista.add(produto);
		}
		return lista;
	}

	private Fornecedor getFornecedor(int codigo)
			throws SQLException, FornecedorNaoEncontradoException, ClassNotFoundException, IOException {
		ControladorFornecedor controlador = new ControladorFornecedor();
		return controlador.procurar(codigo);
	}

	public void cadastrar(Produto produto) throws SQLException {
		String sql = "INSERT INTO Produto (Nome, Valor, Categoria, Codigo_Fornecedor) VALUES ('" + produto.getNome()
				+ "', " + produto.getValor() + ", '" + produto.getCategoria() + "', " + produto.getFornecedor().getCodigo() + ")";
		this.banco.cadastrar(sql);
	}

	public void atualizar(Produto produto) throws SQLException {
		String sql = "UPDATE Produto SET Nome = '" + produto.getNome() + "', Valor = " + produto.getValor()
				+ ", Categoria = '" + produto.getCategoria() + "', Codigo_Fornecedor = " + produto.getFornecedor().getCodigo() + " WHERE Codigo = " + produto.getCodigo();
		this.banco.atualizar(sql);
	}

	public void remover(Produto produto) throws SQLException {
		String sql = "DELETE FROM Produto WHERE Codigo = " + produto.getCodigo();
		this.banco.remove(sql);
	}
	public void remover(int codigo) throws SQLException {
		String sql = "DELETE FROM Produto WHERE Codigo = " + codigo;
		this.banco.remove(sql);
	}
	public ArrayList<Produto> procurar(Map<String, Object> valores) throws SQLException, ClassNotFoundException, FornecedorNaoEncontradoException, IOException {
		String sql = "SELECT * FROM Produto WHERE ";
		int i = 0;
		for (String key : valores.keySet()) {
			if (i == 0) {
				if (valores.get(key) instanceof Integer || valores.get(key) instanceof Double) {
					sql += key + " = " + valores.get(key);
				} else if (valores.get(key) instanceof String) {
					sql += key + " = '" + valores.get(key) + "'";
				}
			} else {
				if (valores.get(key) instanceof Integer || valores.get(key) instanceof Double) {
					sql += ", " + key + " = " + valores.get(key);
				} else if (valores.get(key) instanceof String) {
					sql += ", " + key + " = '" + valores.get(key) + "'";
				}
			}
			i++;
		}
		ResultSet rst = this.banco.listar(sql);
		ArrayList<Produto> lista = new ArrayList<Produto>();
		while (rst.next()) {
			Fornecedor fornecedor = this.getFornecedor(rst.getInt("Codigo"));
			Produto produto = new Produto(rst.getInt("Codigo"), rst.getString("Nome"), rst.getDouble("Valor"),
					rst.getString("Categoria"), fornecedor);
			lista.add(produto);
		}
		
		if(lista.size() == 0){
			return null;
		}
		return lista;
	}
}
