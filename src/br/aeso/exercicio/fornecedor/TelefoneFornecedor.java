package br.aeso.exercicio.fornecedor;

public class TelefoneFornecedor {
	private int Codigo;
	private String Telefone;
	public TelefoneFornecedor(int codigo, String telefone) {
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
		return "TelefoneFornecedor [Codigo=" + Codigo + ", Telefone=" + Telefone + "]";
	}
	
}
