package br.aeso.exercicio.produto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import br.aeso.exercicio.produto.Produto;
import br.aeso.exercicio.produto.ProdutoJaCadastradoException;
import br.aeso.exercicio.produto.ProdutoNaoEncontradoException;
import br.aeso.exercicio.produto.TelaCadastroProduto;
import br.aeso.exercicio.fachada.Fachada;
import br.aeso.exercicio.fornecedor.ControladorFornecedor;
import br.aeso.exercicio.fornecedor.Fornecedor;
import br.aeso.exercicio.fornecedor.FornecedorNaoEncontradoException;
import br.aeso.exercicio.util.CPFInvalidoException;

public class TelaCadastroProduto {
	private static Scanner teclado = new Scanner(System.in);
	public static void main(String args[]) throws IllegalArgumentException, CPFInvalidoException, ProdutoJaCadastradoException, ProdutoNaoEncontradoException{
		Fachada fachada = new Fachada();
		System.out.println("Escolha a sua Opção: ");
		System.out.println("1 - Cadastrar, 2 - lista, 3 - remover:");
		int opcao = teclado.nextInt();
		if(opcao == 1){
			try{
				Produto produto = TelaCadastroProduto.cadastrarProduto();
				fachada.cadastrarProduto(produto);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else if(opcao == 2){
			try{
				ArrayList<Produto> lista = fachada.listarProduto();
				TelaCadastroProduto.montarDisplay(lista);
			}catch(Exception e){
				
			}
		}else if(opcao == 3){
			System.out.println("Digite o Codigo:");
			int codigo = teclado.nextInt();
			try{
				fachada.removerProduto(codigo);
				ArrayList<Produto> lista = fachada.listarProduto();
				TelaCadastroProduto.montarDisplay(lista);
			}catch(Exception e){
				
			}
		}
		
	}
	public static void montarDisplay(ArrayList<Produto> lista){
		System.out.println("Codigo \tNome \tCategoria \tValor \tFornecedor");
		for(Produto produto : lista){
			System.out.print(produto.getCodigo());
			System.out.print("\t");
			System.out.print(produto.getNome());
			System.out.print("\t");
			System.out.print(produto.getCategoria());
			System.out.print("\t");
			System.out.print(produto.getValor());
			System.out.print("\t");
			System.out.println(produto.getFornecedor().getNome());
			System.out.print("\n");
		}
	}
	public static void montarDisplayFornecedores(ArrayList<Fornecedor> lista){
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
	public static Produto cadastrarProduto() throws ClassNotFoundException, FornecedorNaoEncontradoException, IOException, SQLException{
		System.out.println("Cadastro de CLiente: ");
		System.out.println("Insira o codigo: ");
		int codigo = teclado.nextInt();
        teclado.nextLine();
		System.out.println("Insira o Nome: ");
		String nome = teclado.nextLine();
		System.out.println("Insira o valor: ");
		double valor = teclado.nextDouble();
		teclado.nextLine();
        System.out.println("Insira Categoria:");
		String categoria = teclado.nextLine();
		
		ControladorFornecedor control = new ControladorFornecedor();
		ArrayList<Fornecedor> fornecedores = control.listar();
		TelaCadastroProduto tela = new TelaCadastroProduto();
		tela.montarDisplayFornecedores(fornecedores);
		
		System.out.println("Insira o Codigo do fornecedor:");
		int forCod = teclado.nextInt();
        Fornecedor fornecedor = tela.getFornecedor(forCod);
        
		Produto produto = new Produto(1, nome, valor, categoria, fornecedor);
		return produto;
	}   
	private Fornecedor getFornecedor(int codigo) throws ClassNotFoundException, FornecedorNaoEncontradoException, IOException, SQLException{
	  Fachada fachada = new Fachada();
	  return fachada.procurarFornecedor(codigo);
	}
}
