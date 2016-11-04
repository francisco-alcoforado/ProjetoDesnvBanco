package br.aeso.exercicio.pedido;

import java.util.Date;

import br.aeso.exercicio.cliente.Cliente;
import br.aeso.exercicio.vendedor.Vendedor;

public class Pedido{
	private int Codigo;
	private Cliente cliente;
	private Vendedor vendedor;
	private double valor;
	private Date data_pedido;
	public Pedido(int codigo, Cliente cliente, Vendedor vendedor, double valor, Date data_pedido) {
		super();
		Codigo = codigo;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.valor = valor;
		this.data_pedido = data_pedido;
	}
	public int getCodigo() {
		return Codigo;
	}
	public void setCodigo(int codigo) {
		Codigo = codigo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getData_pedido() {
		return data_pedido;
	}
	public void setData_pedido(Date data_pedido) {
		this.data_pedido = data_pedido;
	}
	@Override
	public String toString() {
		return "Pedido [Codigo=" + Codigo + ", cliente=" + cliente + ", vendedor=" + vendedor + ", valor=" + valor
				+ ", data_pedido=" + data_pedido + "]";
	}
}
