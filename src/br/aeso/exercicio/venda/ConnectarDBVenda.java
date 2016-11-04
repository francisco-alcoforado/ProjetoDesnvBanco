package br.aeso.exercicio.venda;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import br.aeso.exercicio.cliente.ClienteNaoExncontradoException;
import br.aeso.exercicio.database.BancoDeDados;
import br.aeso.exercicio.pedido.ControladorPedido;
import br.aeso.exercicio.pedido.Pedido;
import br.aeso.exercicio.pedido.PedidoNaoEncontradoException;
import br.aeso.exercicio.produto.ControladorProduto;
import br.aeso.exercicio.produto.Produto;
import br.aeso.exercicio.produto.ProdutoNaoEncontradoException;
import br.aeso.exercicio.vendedor.VendedorNaoEncontradoException;

public class ConnectarDBVenda {
	private BancoDeDados banco;

	public ConnectarDBVenda() throws SQLException {
		this.banco = BancoDeDados.getBancoDeDados("jdbc:mysql://servidor/BancoProjeto", "root", "");

	}

	public ArrayList<Venda> listar() throws SQLException, ClassNotFoundException, ProdutoNaoEncontradoException,
			IOException, PedidoNaoEncontradoException, ClienteNaoExncontradoException, VendedorNaoEncontradoException {
		String sql = "SELECT * FROM Venda";
		ResultSet rst = this.banco.listar(sql);
		ArrayList<Venda> lista = new ArrayList<Venda>();
		while (rst.next()) {
			Produto produto = this.getProduto(rst.getInt("Codigo_Produto"));
			Pedido pedido = this.getPedido(rst.getInt("Codigo_Pedido"));
			Venda venda = new Venda(rst.getInt("Codigo"), pedido, produto, rst.getInt("Quantidade"),
					rst.getDouble("Valor"), rst.getDate("Data_Venda"));
			lista.add(venda);
		}
		return lista;
	}

	private Produto getProduto(int codigo)
			throws SQLException, ProdutoNaoEncontradoException, ClassNotFoundException, IOException {
		ControladorProduto controlador = new ControladorProduto();
		return controlador.procurar(codigo);
	}

	private Pedido getPedido(int codigo)
			throws SQLException, ClassNotFoundException, IOException, PedidoNaoEncontradoException, ClienteNaoExncontradoException, VendedorNaoEncontradoException {
		ControladorPedido controlador = new ControladorPedido();
		return controlador.procurar(codigo);
	}

	public void cadastrar(Venda venda) throws SQLException {
		String sql = "INSERT INTO Venda (Codigo_Pedido, Codigo_Produto, Quantidade, Valor, Data_Venda) VALUES ("
				+ venda.getPedido().getCodigo() + ", " + venda.getProduto().getCodigo() + ", " + venda.getCodigo()
				+ ", " + venda.getData_Venda() + ", '" + venda.getData_Venda() + "')";
		this.banco.cadastrar(sql);
	}

	public void atualizar(Venda venda) throws SQLException {
		String sql = "UPDATE Venda SET Codigo_Pedido = " + venda.getPedido().getCodigo() + ", Codigo_Produto = "
				+ venda.getProduto().getCodigo() + ", Quantidade = " + venda.getQuantidade() + ", Valor = "
				+ venda.getValor() + ", Data_Venda = '" + venda.getData_Venda() +"' WHERE Codigo = " + venda.getCodigo();
		this.banco.atualizar(sql);
	}

	public void remover(Venda venda) throws SQLException {
		String sql = "DELETE FROM Venda WHERE Codigo = " + venda.getCodigo();
		this.banco.remove(sql);
	}

	public ArrayList<Venda> procurar(Map<String, Object> valores) throws SQLException, ClassNotFoundException, ProdutoNaoEncontradoException, IOException, PedidoNaoEncontradoException, ClienteNaoExncontradoException, VendedorNaoEncontradoException {
		String sql = "SELECT * FROM Venda WHERE ";
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
		ArrayList<Venda> lista = new ArrayList<Venda>();
		while (rst.next()) {
			Produto produto = this.getProduto(rst.getInt("Codigo_Produto"));
			Pedido pedido = this.getPedido(rst.getInt("Codigo_Pedido"));
			Venda venda = new Venda(rst.getInt("Codigo"), pedido, produto, rst.getInt("Quantidade"),
					rst.getDouble("Valor"), rst.getDate("Data_Venda"));
			lista.add(venda);
		}
		
		if(lista.size() == 0){
			return null;
		}
		return lista;
	}
}
