package br.aeso.exercicio.notaFiscal;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.aeso.exercicio.cliente.ClienteNaoExncontradoException;
import br.aeso.exercicio.pedido.PedidoNaoEncontradoException;
import br.aeso.exercicio.vendedor.VendedorNaoEncontradoException;

public interface IRepositorioNotaFiscal {
	public void cadastrar(NotaFiscal notaFiscal);
	public void atualizar(NotaFiscal notaFiscal);
	public boolean remover(int codigo);
	public NotaFiscal procurar(int codigo) throws ClassNotFoundException, SQLException, PedidoNaoEncontradoException, IOException;
	public ArrayList<NotaFiscal> listar() throws ClassNotFoundException, SQLException, PedidoNaoEncontradoException, IOException, ClienteNaoExncontradoException, VendedorNaoEncontradoException;
}
