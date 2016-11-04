package br.aeso.exercicio.venda;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.aeso.exercicio.pedido.PedidoNaoEncontradoException;
import br.aeso.exercicio.produto.ProdutoNaoEncontradoException;

public interface IRepositorioVenda {
	public void cadastrar(Venda venda);
	public void atualizar(Venda venda);
	public boolean remover(double codigo);
	public Venda procurar(double codigo) throws ClassNotFoundException, SQLException, ProdutoNaoEncontradoException, IOException, PedidoNaoEncontradoException;
	public ArrayList<Venda> listar() throws ClassNotFoundException, SQLException, ProdutoNaoEncontradoException, IOException, PedidoNaoEncontradoException;
}
