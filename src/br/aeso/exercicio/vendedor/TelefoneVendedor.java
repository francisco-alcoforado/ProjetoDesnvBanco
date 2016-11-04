package br.aeso.exercicio.vendedor;

public class TelefoneVendedor {
	private int Codigo;
	private String Telefone;
	public TelefoneVendedor(int codigo, String telefone) {
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
		return "TelefoneVendedor [Codigo=" + Codigo + ", Telefone=" + Telefone + "]";
	}
	
}
