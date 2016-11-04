package br.aeso.exercicio.pedido;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.aeso.exercicio.cliente.ClienteNaoExncontradoException;
import br.aeso.exercicio.vendedor.VendedorNaoEncontradoException;

public interface IRepositorioPedido {
	public void cadastrar(Pedido pedido);
	public void atualizar(Pedido pedido);
	public boolean remover(double codigo);
	public Pedido procurar(double codigo) throws ClassNotFoundException, SQLException, ClienteNaoExncontradoException, IOException, VendedorNaoEncontradoException;
	public ArrayList<Pedido> listar();
}
