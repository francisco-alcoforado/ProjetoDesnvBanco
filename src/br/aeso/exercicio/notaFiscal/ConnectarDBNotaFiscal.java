package br.aeso.exercicio.notaFiscal;

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
import br.aeso.exercicio.vendedor.VendedorNaoEncontradoException;

public class ConnectarDBNotaFiscal {
	private BancoDeDados banco;

	public ConnectarDBNotaFiscal() throws SQLException {
		this.banco = BancoDeDados.getBancoDeDados("jdbc:mysql://servidor/BancoProjeto", "root", "");

	}

	public ArrayList<NotaFiscal> listar() throws SQLException, ClassNotFoundException, PedidoNaoEncontradoException, IOException, ClienteNaoExncontradoException, VendedorNaoEncontradoException {
		String sql = "SELECT * FROM Nota_Fiscal";
		ResultSet rst = this.banco.listar(sql);
		ArrayList<NotaFiscal> lista = new ArrayList<NotaFiscal>();
		while (rst.next()) {
			Pedido pedido = this.getPedido(rst.getInt("Codigo_Pedido"));
			NotaFiscal notaFiscal = new NotaFiscal(rst.getInt("Codigo"), rst.getString("Emitente"), pedido,
					rst.getDate("Data_Emissao"));
			lista.add(notaFiscal);
		}
		return lista;
	}

	private Pedido getPedido(int codigo)
			throws SQLException, PedidoNaoEncontradoException, ClassNotFoundException, IOException, ClienteNaoExncontradoException, VendedorNaoEncontradoException {
		ControladorPedido controlador = new ControladorPedido();
		return controlador.procurar(codigo);
	}

	public void cadastrar(NotaFiscal notaFiscal) throws SQLException {
		String sql = "INSERT INTO Nota_Fiscal (Emitente, Codigo_Pedido, Data_Emissao) VALUES ('"
				+ notaFiscal.getEmitente() + "', " + notaFiscal.getPedido().getCodigo() + ", '"
				+ notaFiscal.getData_Emissao().toString() + "')";
		this.banco.cadastrar(sql);
	}

	public void atualizar(NotaFiscal notaFiscal) throws SQLException {
		String sql = "UPDATE Nota_Fiscal SET Emitente = '" + notaFiscal.getEmitente() + "', Codigo_Pedido = "
				+ notaFiscal.getPedido().getCodigo() + ", Data_Emissao = '" + notaFiscal.getData_Emissao().toString() + "' WHERE Codigo = " + notaFiscal.getCodigo();
		this.banco.atualizar(sql);
	}

	public void remover(NotaFiscal notaFiscal) throws SQLException {
		String sql = "DELETE FROM Nota_Fiscal WHERE Codigo = " + notaFiscal.getCodigo();
		this.banco.remove(sql);
	}

	public ArrayList<NotaFiscal> procurar(Map<String, Object> valores) throws SQLException, ClassNotFoundException, PedidoNaoEncontradoException, IOException, ClienteNaoExncontradoException, VendedorNaoEncontradoException {
		String sql = "SELECT * FROM Nota_Fiscal WHERE ";
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
		ArrayList<NotaFiscal> lista = new ArrayList<NotaFiscal>();
		while (rst.next()) {
			Pedido pedido = this.getPedido(rst.getInt("Codigo_Pedido"));
			NotaFiscal notaFiscal = new NotaFiscal(rst.getInt("Codigo"), rst.getString("Emitente"), pedido,
					rst.getDate("Data_Emissao"));
			lista.add(notaFiscal);

		}
		
		if(lista.size() == 0){
			return null;
		}
		return lista;
	}
}
