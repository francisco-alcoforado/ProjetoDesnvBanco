package br.aeso.exercicio.pedido;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import br.aeso.exercicio.cliente.Cliente;
import br.aeso.exercicio.cliente.ClienteNaoExncontradoException;
import br.aeso.exercicio.cliente.ControladorCliente;
import br.aeso.exercicio.database.BancoDeDados;
import br.aeso.exercicio.vendedor.ControladorVendedor;
import br.aeso.exercicio.vendedor.Vendedor;
import br.aeso.exercicio.vendedor.VendedorNaoEncontradoException;

public class ConnectarDBPedido {
	private BancoDeDados banco;

	public ConnectarDBPedido() throws SQLException {
		this.banco = BancoDeDados.getBancoDeDados("jdbc:mysql://servidor/BancoProjeto", "root", "");

	}

	public ArrayList<Pedido> listar() throws SQLException, ClassNotFoundException, ClienteNaoExncontradoException, IOException, VendedorNaoEncontradoException{
		String sql = "SELECT * FROM Pedido";
		ResultSet rst = this.banco.listar(sql);
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		while(rst.next()){
			Cliente cliente = getCliente(rst.getInt("Codigo_Cliente"));
			Vendedor vendedor = getVendedor(rst.getInt("Codigo_Vendedor"));
			Pedido pedido = new Pedido(rst.getInt("Codigo"), cliente, vendedor, rst.getDouble("Valor"), rst.getDate("Data_Pedido"));
			lista.add(pedido);
		}
		return lista;
	}

	private Cliente getCliente(int codigo)
			throws SQLException, ClienteNaoExncontradoException, ClassNotFoundException, IOException {
		ControladorCliente controle = new ControladorCliente();
		return controle.procurar(codigo);
	}

	private Vendedor getVendedor(int codigo)
			throws SQLException, ClassNotFoundException, IOException, VendedorNaoEncontradoException {
		ControladorVendedor controle = new ControladorVendedor();
		return controle.procurar(codigo);
	}

	public void cadastrar(Pedido pedido) throws SQLException {
		String sql = "INSERT INTO Pedido (Codigo_Cliente, Codigo_Vendedor, Valor, Data_Pedido) VALUE ("
				+ pedido.getCliente().getCodigo() + ", " + pedido.getVendedor().getCodigo() + ", " + pedido.getValor()
				+ ", '" + pedido.getData_pedido().toString() + "')";
		this.banco.cadastrar(sql);
	}

	public void atualizar(Pedido pedido) throws SQLException {
		String sql = "UPDATE Pedido SET Codigo_Cliente = " + pedido.getCliente().getCodigo() + ", Codigo_Vendedor = "
				+ pedido.getVendedor().getCodigo() + ",  Valor = " + pedido.getValor() + ", Data_Pedido = '" + pedido.getData_pedido().toString() + "' WHERE Codigo = "
				+ pedido.getCodigo();
		this.banco.atualizar(sql);
	}

	public void remover(Pedido pedido) throws SQLException {
		String sql = "DELETE FROM Pedido WHERE Codigo = " + pedido.getCodigo();
		this.banco.remove(sql);
	}

	public ArrayList<Pedido> procurar(Map<String, Object> valores) throws SQLException, ClassNotFoundException, ClienteNaoExncontradoException, IOException, VendedorNaoEncontradoException {
		String sql = "SELECT * FROM Pedido WHERE ";
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
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		while (rst.next()) {
			Cliente cliente = getCliente(rst.getInt("Codigo_Cliente"));
			Vendedor vendedor = getVendedor(rst.getInt("Codigo_Vendedor"));
			Pedido pedido = new Pedido(rst.getInt("Codigo"), cliente, vendedor, rst.getDouble("Valor"), rst.getDate("Data_Pedido"));
			lista.add(pedido);
		}
		
		if(lista.size() == 0){
			return null;
		}
		
		return lista;
	}
}
