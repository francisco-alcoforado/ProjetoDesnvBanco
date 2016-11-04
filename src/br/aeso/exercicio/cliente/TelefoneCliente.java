package br.aeso.exercicio.cliente;

public class TelefoneCliente {
	private int Codigo;
	private String Telefone;
	public TelefoneCliente(int codigo, String telefone) {
		super();
		Codigo = codigo;
		Telefone = telefone;
	}
	public int getCodigo() {
		return Codigo;
	}
	public void setCodigo(int codigo) {
		Codigo = codigo;
	}
	public String getTelefone() {
		return Telefone;
	}
	public void setTelefone(String telefone) {
		Telefone = telefone;
	}
	@Override
	public String toString() {
		return "TelefoneCliente [Codigo=" + Codigo + ", Telefone=" + Telefone + "]";
	}
	
	
}
