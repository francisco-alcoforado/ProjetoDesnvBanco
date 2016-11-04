package br.aeso.exercicio.venda;

import java.util.Date;

import br.aeso.exercicio.pedido.Pedido;
import br.aeso.exercicio.produto.Produto;

public class Venda {
	private int codigo;
	private Pedido pedido;
	private Produto produto;
	private int quantidade;
	private double valor;
	private Date Data_Venda;
	
	public Venda(int codigo, Pedido pedido, Produto produto, int quantidade, double valor, Date data_Venda) {
		super();
		this.codigo = codigo;
		this.pedido = pedido;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valor = valor;
		Data_Venda = data_Venda;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData_Venda() {
		return Data_Venda;
	}

	public void setData_Venda(Date data_Venda) {
		Data_Venda = data_Venda;
	}

	@Override
	public String toString() {
		return "Venda [codigo=" + codigo + ", pedido=" + pedido + ", produto=" + produto + ", quantidade=" + quantidade
				+ ", valor=" + valor + ", Data_Venda=" + Data_Venda + "]";
	}
	
	
}
