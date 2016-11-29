/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.aeso.exercicio.cliente;

import java.util.ArrayList;

/**
 *
 * @author lab01
 */
public class ClienteBuilder {
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

    public ClienteBuilder comCodigo(int codigo) {
        this.codigo = codigo;
        return this;
    }

    public ClienteBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClienteBuilder comCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ClienteBuilder comRua(String rua) {
        this.rua = rua;
        return this;
    }

    public ClienteBuilder comBairro(String Bairro) {
        this.Bairro = Bairro;
        return this;
    }

    public ClienteBuilder comCidade(String Cidade) {
        this.Cidade = Cidade;
        return this;
    }

    public ClienteBuilder comCEP(String CEP) {
        this.CEP = CEP;
        return this;
    }

    public ClienteBuilder comNumero(int Numero) {
        this.Numero = Numero;
        return this;
    }

    public ClienteBuilder comComplemento(String Complemento) {
        this.Complemento = Complemento;
        return this;
    }

    public ClienteBuilder comTelefones(ArrayList<TelefoneCliente> telefones) {
        this.telefones = telefones;
        return this;
    }

    public ClienteBuilder comEmails(ArrayList<EmailCliente> emails) {
        this.emails = emails;
        return this;
    }
    
    public Cliente construir(){
        return new Cliente(codigo, nome, cpf, rua, Bairro, Cidade, CEP, Numero, Complemento, telefones, emails);
    }
    
}
