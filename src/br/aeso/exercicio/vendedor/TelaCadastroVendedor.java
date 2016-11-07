package br.aeso.exercicio.vendedor;

import java.util.ArrayList;
import java.util.Scanner;

import br.aeso.exercicio.fachada.Fachada;

public class TelaCadastroVendedor {
		private static Scanner teclado = new Scanner(System.in);
		public static void main(String args[]) throws IllegalArgumentException, VendedorJaCadastradoException, VendedorNaoEncontradoException{
			Fachada fachada = new Fachada();
			System.out.println("Escolha a sua Opção: ");
			System.out.println("1 - Cadastrar, 2 - lista, 3 - remover:");
			int opcao = teclado.nextInt();
			if(opcao == 1){
				try{
					Vendedor vendedor = TelaCadastroVendedor.cadastrarVendedor();
					fachada.cadastrarVendedor(vendedor);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}else if(opcao == 2){
				try{
					ArrayList<Vendedor> lista = fachada.listarVendedor();
					TelaCadastroVendedor.montarDisplay(lista);
				}catch(Exception e){
					
				}
			}else if(opcao == 3){
				System.out.println("Digite o Codigo:");
				int codigo = teclado.nextInt();
				try{
					fachada.removerVendedor(codigo);
					ArrayList<Vendedor> lista = fachada.listarVendedor();
					TelaCadastroVendedor.montarDisplay(lista);
				}catch(Exception e){
					
				}
			}
			
		}
		public static void montarDisplay(ArrayList<Vendedor> lista){
			System.out.println("Codigo \tNome \tEmail \tCPF \tEmail");
			for(Vendedor vendedor : lista){
				System.out.print(vendedor.getCodigo());
				System.out.print("\t");
				System.out.print(vendedor.getNome());
				System.out.print("\t");
				System.out.print(vendedor.getCpf());
				System.out.print("\t");
				System.out.print(vendedor.getCEP());
				System.out.print("\t");
				if(vendedor.getEmails().size() > 0){
					System.out.print(vendedor.getEmails().get(0).getEmail());
				}
				System.out.print("\n");
			}
		}
		public static Vendedor cadastrarVendedor(){
			System.out.println("Cadastro de CLiente: ");
			System.out.println("Insira o codigo: ");
			int codigo = teclado.nextInt();
	        teclado.nextLine();
			System.out.println("Insira o Nome: ");
			String nome = teclado.nextLine();
			System.out.println("Insira Senha:");
			String senha = teclado.nextLine();
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
	        System.out.println("Email Ã© primario:");
	        int primario = teclado.nextInt();
	        teclado.nextLine();
	        System.out.println("Insira Telefone:");
            String telefone = teclado.nextLine();
            TelaCadastroVendedor tela = new TelaCadastroVendedor();
            ArrayList<TelefoneVendedor> telefones = tela.cadastrarTelefones(telefone);
            ArrayList<EmailVendedor> emails = tela.cadastrarEmail(email, primario);
	                
			Vendedor vendedor = new Vendedor(codigo, nome, senha, cpf, rua, bairro, cidade, CEP,
				numero, complemento, telefones, emails);
			return vendedor;
		}
	        private ArrayList<TelefoneVendedor> cadastrarTelefones(String telefone){
	            ArrayList<TelefoneVendedor> lista = new ArrayList<TelefoneVendedor>();
	            TelefoneVendedor telefoneVendedor = new TelefoneVendedor(1, telefone);
	            lista.add(telefoneVendedor);
	            return lista;
	        }
	        private ArrayList<EmailVendedor> cadastrarEmail(String email, int primario){
	            ArrayList<EmailVendedor> lista = new ArrayList<EmailVendedor>();
	            EmailVendedor emailVendedor = new EmailVendedor(1, email, primario);
	            lista.add(emailVendedor);
	            return lista;
	        }
}

