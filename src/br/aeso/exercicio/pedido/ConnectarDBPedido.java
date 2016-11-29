package br.aeso.exercicio.pedido;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
		this.banco = BancoDeDados.getBancoDeDados("jdbc:mysql://localhost/projeto_banco", "root", "cavaco");

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
        public ArrayList<Pedido> listar(String orderBy) throws SQLException, ClassNotFoundException, ClienteNaoExncontradoException, IOException, VendedorNaoEncontradoException{
		String sql = "SELECT * FROM Pedido ORDER BY " + orderBy;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String data = sdf.format(pedido.getData_pedido());
		String sql = "INSERT INTO Pedido (Codigo_Cliente, Codigo_Vendedor, Valor, Data_Pedido) VALUES ("
				+ pedido.getCliente().getCodigo() + ", " + pedido.getVendedor().getCodigo() + ", " + pedido.getValor()
				+ ", '" + data + "')";
		this.banco.cadastrar(sql);
	}

	public void atualizar(Pedido pedido) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String data = sdf.format(pedido.getData_pedido());
		String sql = "UPDATE Pedido SET Codigo_Cliente = " + pedido.getCliente().getCodigo() + ", Codigo_Vendedor = "
				+ pedido.getVendedor().getCodigo() + ",  Valor = " + pedido.getValor() + ", Data_Pedido = '" + data + "' WHERE Codigo = "
				+ pedido.getCodigo();
		this.banco.atualizar(sql);
	}

	public void remover(Pedido pedido) throws SQLException {
                String sqlVenda = "DELETE FROM Venda WHERE Codigo_Pedido = " + pedido.getCodigo();
                String sqlNota = "DELETE FROM Nota_Fiscal WHERE Codigo_Pedido = " + pedido.getCodigo();
		String sql = "DELETE FROM Pedido WHERE Codigo = " + pedido.getCodigo();
                this.banco.remove(sqlVenda);
                this.banco.remove(sqlNota);
		this.banco.remove(sql);
	}
	public void remover(int codigo) throws SQLException {
                String sqlVenda = "DELETE FROM Venda WHERE Codigo_Pedido = " + codigo;
                String sqlNota = "DELETE FROM Nota_Fiscal WHERE Codigo_Pedido = " + codigo;
		String sql = "DELETE FROM Pedido WHERE Codigo = " + codigo;
                this.banco.remove(sqlVenda);
                this.banco.remove(sqlNota);
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
        public ArrayList<Pedido> procurar(Map<String, Object> valores, String order) throws SQLException, ClassNotFoundException, ClienteNaoExncontradoException, IOException, VendedorNaoEncontradoException {
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
                sql += " ORDER BY " + order;
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
