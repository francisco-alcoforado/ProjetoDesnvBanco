package br.aeso.exercicio.cliente;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nome;
	private String cpf;
	private String rua;
	private String Bairro;
	private String Cidade;
	private String CEP;
	private int Numero;
    private String Complemento;
    private ArrayList<TelefoneCliente> telefones;
    private ArrayList<EmailCliente> emails;
    
    

	public Cliente(int codigo, String nome, String cpf, String rua, String bairro, String cidade, String cEP,
			int numero, String complemento, ArrayList<TelefoneCliente> telefones, ArrayList<EmailCliente> emails) {
		super();
		this.codigo = codigo;
		this.nome = nome;
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



	public ArrayList<TelefoneCliente> getTelefones() {
		return telefones;
	}



	public void setTelefones(ArrayList<TelefoneCliente> telefones) {
		this.telefones = telefones;
	}



	public ArrayList<EmailCliente> getEmails() {
		return emails;
	}



	public void setEmails(ArrayList<EmailCliente> emails) {
		this.emails = emails;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nome=" + nome + ", cpf=" + cpf + ", rua=" + rua + ", Bairro=" + Bairro
				+ ", Cidade=" + Cidade + ", CEP=" + CEP + ", Numero=" + Numero + ", Complemento=" + Complemento
				+ ", telefones=" + telefones + ", emails=" + emails + "]";
	}



	public String getCpfFormatado(){
		return String.format("xxx.xxx.xxx-xx", this.cpf);
	}
}
