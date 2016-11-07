package br.aeso.exercicio.fornecedor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepositorioFornecedor {
	public void cadastrar(Fornecedor fornecedor) throws IOException;
	public void atualizar(Fornecedor fornecedor) throws IOException;
	public boolean remover(int codigo) throws IOException;
	public Fornecedor procurar(double codigo) throws SQLException;
	public ArrayList<Fornecedor> listar();
}
