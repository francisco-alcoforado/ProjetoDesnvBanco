package br.aeso.exercicio.produto;

import br.aeso.exercicio.fornecedor.Fornecedor;

public class Produto{
	private int codigo;
	private String nome;
	private double valor;
	private String categoria;
	private Fornecedor fornecedor;
	public Produto(int codigo, String nome, double valor, String categoria, Fornecedor fornecedor) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
		this.categoria = categoria;
		this.fornecedor = fornecedor;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", valor=" + valor + ", categoria=" + categoria
				+ ", fornecedor=" + fornecedor + "]";
	}
	
	
	
}
