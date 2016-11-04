package br.aeso.exercicio.fornecedor;

import java.io.Serializable;
import java.util.ArrayList;

public class Fornecedor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nome;
	private ArrayList<EmailFornecedor> emails;
	private ArrayList<TelefoneFornecedor> telefones;
	public Fornecedor(int codigo, String nome, ArrayList<EmailFornecedor> emails,
			ArrayList<TelefoneFornecedor> telefones) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.emails = emails;
		this.telefones = telefones;
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
	public ArrayList<EmailFornecedor> getEmails() {
		return emails;
	}
	public void setEmails(ArrayList<EmailFornecedor> emails) {
		this.emails = emails;
	}
	public ArrayList<TelefoneFornecedor> getTelefones() {
		return telefones;
	}
	public void setTelefones(ArrayList<TelefoneFornecedor> telefones) {
		this.telefones = telefones;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Fornecedor [codigo=" + codigo + ", nome=" + nome + ", emails=" + emails + ", telefones=" + telefones
				+ "]";
	}
	
	
	
}
