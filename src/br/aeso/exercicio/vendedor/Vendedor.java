package br.aeso.exercicio.vendedor;

import java.util.ArrayList;

public class Vendedor {
	private int codigo;
	private String nome;
	private String senha;
	private String cpf;
	private String rua;
	private String Bairro;
	private String Cidade;
	private String CEP;
	private int Numero;
    private String Complemento;
    private ArrayList<TelefoneVendedor> telefones;
    private ArrayList<EmailVendedor> emails;
	public Vendedor(int codigo, String nome, String senha, String cpf, String rua, String bairro, String cidade,
			String cEP, int numero, String complemento, ArrayList<TelefoneVendedor> telefones,
			ArrayList<EmailVendedor> emails) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.senha = senha;
		this.cpf = cpf;
		this.rua = rua;
		Bairro = bairro;
		Cidade = cidade;
		CEP = cEP;
		Numero = numero;
		Complemento = complemento;
		this.telefones = telefones;
		this.emails = emails;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return Bairro;
	}
	public void setBairro(String bairro) {
		Bairro = bairro;
	}
	public String getCidade() {
		return Cidade;
	}
	public void setCidade(String cidade) {
		Cidade = cidade;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
	public int getNumero() {
		return Numero;
	}
	public void setNumero(int numero) {
		Numero = numero;
	}
	public String getComplemento() {
		return Complemento;
	}
	public void setComplemento(String complemento) {
		Complemento = complemento;
	}
	public ArrayList<TelefoneVendedor> getTelefones() {
		return telefones;
	}
	public void setTelefones(ArrayList<TelefoneVendedor> telefones) {
		this.telefones = telefones;
	}
	public ArrayList<EmailVendedor> getEmails() {
		return emails;
	}
	public void setEmails(ArrayList<EmailVendedor> emails) {
		this.emails = emails;
	}
	@Override
	public String toString() {
		return "Vendedor [codigo=" + codigo + ", nome=" + nome + ", senha=" + senha + ", cpf=" + cpf + ", rua=" + rua
				+ ", Bairro=" + Bairro + ", Cidade=" + Cidade + ", CEP=" + CEP + ", Numero=" + Numero + ", Complemento="
				+ Complemento + ", telefones=" + telefones + ", emails=" + emails + "]";
	}
	
    
    
}
