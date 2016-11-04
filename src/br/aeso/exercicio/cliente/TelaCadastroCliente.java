package br.aeso.exercicio.cliente;

import java.util.Scanner;
import br.aeso.exercicio.fachada.Fachada;
import br.aeso.exercicio.util.CPFInvalidoException;
import java.util.ArrayList;

public class TelaCadastroCliente {
	private static Scanner teclado = new Scanner(System.in);
	public static void main(String args[]) throws IllegalArgumentException, CPFInvalidoException, ClienteJaCadastradoException, ClienteNaoExncontradoException{
		Fachada fachada = new Fachada();
		Cliente cliente = TelaCadastroCliente.cadastrarCliente();
		try{
			fachada.cadastrarCliente(cliente);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	public static Cliente cadastrarCliente(){
		System.out.println("Cadastro de CLiente: ");
		System.out.println("Insira o codigo: ");
		int codigo = teclado.nextInt();
                teclado.nextLine();
		System.out.println("Insira o Nome: ");
		String nome = teclado.nextLine();
		System.out.println("Insira o cpf: ");
		String cpf = teclado.nextLine();
                System.out.println("Insira Rua:");
		String rua = teclado.nextLine();
                System.out.println("Insira Numero:");
		int numero = teclado.nextInt();
                teclado.nextLine();
                System.out.println("Insira Complemento:");
		String complemento = teclado.nextLine();
                System.out.println("Insira Bairro:");
		String bairro= teclado.nextLine();
                System.out.println("Insira Cidade:");
		String cidade = teclado.nextLine(); 
                System.out.println("Insira CEP:");
		String CEP = teclado.nextLine();
                System.out.println("Insira Email:");
                String email = teclado.nextLine();
                System.out.println("Email é primario:");
                int primario = teclado.nextInt();
                teclado.nextLine();
                System.out.println("Insira Telefone:");
                String telefone = teclado.nextLine();
                TelaCadastroCliente tela = new TelaCadastroCliente();
                ArrayList<TelefoneCliente> telefones = tela.cadastrarTelefones(telefone);
                ArrayList<EmailCliente> emails = tela.cadastrarEmail(email, primario);
		Cliente cliente = new Cliente(codigo, nome, cpf, rua, bairro, cidade, CEP,
			numero, complemento, telefones, emails);
		return cliente;
	}
        private ArrayList<TelefoneCliente> cadastrarTelefones(String telefone){
            ArrayList<TelefoneCliente> lista = new ArrayList<TelefoneCliente>();
            TelefoneCliente telefoneCliente = new TelefoneCliente(1, telefone);
            lista.add(telefoneCliente);
            return lista;
        }
        private ArrayList<EmailCliente> cadastrarEmail(String email, int primario){
            ArrayList<EmailCliente> lista = new ArrayList<EmailCliente>();
            EmailCliente emailCliente = new EmailCliente(1, email, primario);
            lista.add(emailCliente);
            return lista;
        }
}
