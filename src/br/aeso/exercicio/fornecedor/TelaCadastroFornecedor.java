package br.aeso.exercicio.fornecedor;

import java.util.ArrayList;
import java.util.Scanner;
import br.aeso.exercicio.fachada.Fachada;
import br.aeso.exercicio.util.CNPJInvalidoException;

public class TelaCadastroFornecedor {
	private static Scanner teclado = new Scanner(System.in);
	public static void main(String args[]) throws IllegalArgumentException, FornecedorJaCadastradoException, CNPJInvalidoException, FornecedorNaoEncontradoException{
		Fachada fachada = new Fachada();
		System.out.println("Escolha a sua Opção: ");
		System.out.println("1 - Cadastrar, 2 - lista, 3 - remover:");
		int opcao = teclado.nextInt();
		if(opcao == 1){
			try{
				Fornecedor fornecedor = TelaCadastroFornecedor.cadastrarFornecedor();
				fachada.cadastrarFornecedor(fornecedor);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else if(opcao == 2){
			try{
				ArrayList<Fornecedor> lista = fachada.listarFornecedor();
				TelaCadastroFornecedor.montarDisplay(lista);
			}catch(Exception e){
				
			}
		}else if(opcao == 3){
			System.out.println("Digite o Codigo:");
			int codigo = teclado.nextInt();
			try{
				fachada.removerFornecedor(codigo);
				ArrayList<Fornecedor> lista = fachada.listarFornecedor();
				TelaCadastroFornecedor.montarDisplay(lista);
			}catch(Exception e){
				
			}
		}
	}
	public static void montarDisplay(ArrayList<Fornecedor> lista){
		System.out.println("Codigo \tNome \tEmail");
		for(Fornecedor fornecedor : lista){
			System.out.print(fornecedor.getCodigo());
			System.out.print("\t");
			System.out.print(fornecedor.getNome());
			System.out.print("\t");
			if(fornecedor.getEmails().size() > 0){
				System.out.print(fornecedor.getEmails().get(0).getEmail());
			}
			System.out.print("\n");
		}
	}
	public static Fornecedor cadastrarFornecedor(){
		System.out.println("Cadastro de Fornecedor: ");
		teclado.nextLine();
		System.out.println("Insira Nome: ");
		String nome = teclado.nextLine();
		System.out.println("Insira Email: ");
		String email = teclado.nextLine();
		System.out.println("Email é primario: ");
		int primario = teclado.nextInt();
		teclado.nextLine();
		System.out.println("Insira Telefone: ");
		String telefone = teclado.nextLine();
		
		System.out.println(nome);
		System.out.println(email);
		System.out.println(primario);
		System.out.println(telefone);
		TelaCadastroFornecedor tela = new TelaCadastroFornecedor();
		ArrayList<EmailFornecedor> emails = tela.cadastrarEmail(email, primario);
		ArrayList<TelefoneFornecedor> telefones = tela.cadastrarTelefones(telefone);
		Fornecedor fornecedor = new Fornecedor(1, nome, emails, telefones);
		return fornecedor;
	}
	private ArrayList<TelefoneFornecedor> cadastrarTelefones(String telefone){
        ArrayList<TelefoneFornecedor> lista = new ArrayList<TelefoneFornecedor>();
        TelefoneFornecedor telefoneFornecedor = new TelefoneFornecedor(1, telefone);
        lista.add(telefoneFornecedor);
        return lista;
    }
    private ArrayList<EmailFornecedor> cadastrarEmail(String email, int primario){
        ArrayList<EmailFornecedor> lista = new ArrayList<EmailFornecedor>();
        EmailFornecedor emailFornecedor = new EmailFornecedor(1, email, primario);
        lista.add(emailFornecedor);
        return lista;
    }
}
