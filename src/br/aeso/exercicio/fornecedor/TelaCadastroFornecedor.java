package br.aeso.exercicio.fornecedor;

import java.util.Scanner;

import br.aeso.exercicio.fachada.Fachada;
import br.aeso.exercicio.util.CNPJInvalidoException;

public class TelaCadastroFornecedor {
	private static Scanner teclado = new Scanner(System.in);
	public static void main(String args[]) throws IllegalArgumentException, FornecedorJaCadastradoException, CNPJInvalidoException, FornecedorNaoEncontradoException{
		Fachada fachada = new Fachada();
		Fornecedor fornecedor = TelaCadastroFornecedor.cadastrarFornecedor();
		try{
			fachada.cadastrarFornecedor(fornecedor);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public static Fornecedor cadastrarFornecedor(){
		/*System.out.println("Cadastro de CLiente: ");
		System.out.println("Insira o codigo: ");
		int codigo = Integer.parseInt(teclado.nextLine());
		System.out.println("Insira o Nome: ");
		String nome = teclado.nextLine();
		System.out.println("Insira o cpf: ");
		String cpf = teclado.nextLine();
		System.out.println("Insira o Banco: ");
		String banco = teclado.nextLine();
		System.out.println("Insira o Rua: ");
		String rua = teclado.nextLine();
		System.out.println("Insira o numero: ");
		String numero = teclado.nextLine();
		System.out.println("Insira o complemento: ");
		String complemento = teclado.nextLine();
		System.out.println("Insira o Bairro: ");
		String bairro = teclado.nextLine();
		System.out.println("Insira o cidade: ");
		String cidade = teclado.nextLine();
		System.out.println("Insira o cep: ");
		String cep = teclado.nextLine();
		Endereco endereco = new Endereco();
		endereco.setRua(rua);
		endereco.setNumero(numero);
		endereco.setBairro(bairro);
		endereco.setComplemento(complemento);
		endereco.setCidade(cidade);
		endereco.setCep(cep);
		Fornecedor fornecedor = new Fornecedor(codigo, nome, cpf, banco, endereco);
		endereco.setFornecedor(fornecedor);
		return fornecedor;*/
                return null;
	}
}
