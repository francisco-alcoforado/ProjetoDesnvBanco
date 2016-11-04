package br.aeso.exercicio.fornecedor;

public class EmailFornecedor {
	private int Codigo;
	private String Email;
	private int primario;
	public EmailFornecedor(int codigo, String email, int primario) {
		super();
		Codigo = codigo;
		Email = email;
		this.primario = primario;
	}
	public int getCodigo() {
		return Codigo;
	}
	public void setCodigo(int codigo) {
		Codigo = codigo;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getPrimario() {
		return primario;
	}
	public void setPrimario(int primario) {
		this.primario = primario;
	}
	@Override
	public String toString() {
		return "EmailFornecedor [Codigo=" + Codigo + ", Email=" + Email + ", primario=" + primario + "]";
	}
	
}
