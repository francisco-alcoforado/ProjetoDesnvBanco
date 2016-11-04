package br.aeso.exercicio.vendedor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepositorioVendedor {
	public void cadastrar(Vendedor vendedor) throws IOException;
	public void atualizar(Vendedor vendedor) throws IOException;
	public boolean remover(double codigo) throws IOException;
	public Vendedor procurar(double codigo) throws SQLException;
	public ArrayList<Vendedor> listar();
}
