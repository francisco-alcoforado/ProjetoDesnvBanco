package br.aeso.exercicio.venda;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import br.aeso.exercicio.cliente.Cliente;
import br.aeso.exercicio.cliente.ClienteNaoExncontradoException;
import br.aeso.exercicio.cliente.ControladorCliente;
import br.aeso.exercicio.fachada.Fachada;
import br.aeso.exercicio.pedido.ControladorPedido;
import br.aeso.exercicio.pedido.Pedido;
import br.aeso.exercicio.pedido.PedidoNaoEncontradoException;
import br.aeso.exercicio.venda.ControladorVenda;
import br.aeso.exercicio.venda.Venda;
import br.aeso.exercicio.venda.TelaCadastroVenda;
import br.aeso.exercicio.produto.ControladorProduto;
import br.aeso.exercicio.produto.Produto;
import br.aeso.exercicio.produto.ProdutoNaoEncontradoException;
import br.aeso.exercicio.util.CPFInvalidoException;
import br.aeso.exercicio.vendedor.ControladorVendedor;
import br.aeso.exercicio.vendedor.Vendedor;
import br.aeso.exercicio.vendedor.VendedorNaoEncontradoException;

public class TelaCadastroVenda {
	private static Scanner teclado = new Scanner(System.in);
	public static void main(String args[]) throws IllegalArgumentException, CPFInvalidoException{
		Fachada fachada = new Fachada();
		System.out.println("Escolha a sua Opção: ");
		System.out.println("1 - Cadastrar, 2 - lista, 3 - remover:");
		int opcao = teclado.nextInt();
		if(opcao == 1){
			try{
				Venda venda = TelaCadastroVenda.cadastrarVenda();
				fachada.cadastrarVenda(venda);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else if(opcao == 2){
			try{
				ArrayList<Venda> lista = fachada.listarVenda();
				TelaCadastroVenda.montarDisplay(lista);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else if(opcao == 3){
			System.out.println("Digite o Codigo:");
			int codigo = teclado.nextInt();
			try{
				fachada.removerVenda(codigo);
				ArrayList<Venda> lista = fachada.listarVenda();
				TelaCadastroVenda.montarDisplay(lista);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		
	}
	public static void montarDisplay(ArrayList<Venda> lista){
		System.out.println("Codigo \tVal Pedido \tNome Produto \tValor \tData_Venda");
		for(Venda venda : lista){
			System.out.print(venda.getCodigo());
			System.out.print("\t");
			System.out.print(venda.getPedido().getValor());
			System.out.print("\t");
			System.out.print(venda.getProduto().getNome());
			System.out.print("\t");
			System.out.print(venda.getValor());
			System.out.print("\t");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdf.format(venda.getData_Venda());
			System.out.println(data);
			System.out.print("\n");
		}
	}
	public static void montarDisplayProduto(ArrayList<Produto> lista){
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
	
	public static void montarDisplayPedido(ArrayList<Pedido> lista){
		System.out.println("Codigo \tCliente \tVendedor \tValor \tData_Pedido");
		for(Pedido pedido : lista){
			System.out.print(pedido.getCodigo());
			System.out.print("\t");
			System.out.print(pedido.getCliente().getNome());
			System.out.print("\t");
			System.out.print(pedido.getVendedor().getNome());
			System.out.print("\t");
			System.out.print(pedido.getValor());
			System.out.print("\t");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String data = sdf.format(pedido.getData_pedido());
			System.out.println(data);
			System.out.print("\n");
		}
	}
	public static Venda cadastrarVenda() throws ClassNotFoundException, IOException, SQLException, ClienteNaoExncontradoException, VendedorNaoEncontradoException, ProdutoNaoEncontradoException, PedidoNaoEncontradoException{
		System.out.println("Cadastro de CLiente: ");
		System.out.println("Insira o codigo: ");
		int codigo = teclado.nextInt();
        teclado.nextLine();
		System.out.println("Insira o valor: ");
		double valor = teclado.nextDouble();
		teclado.nextLine();
		System.out.println("Insira o quantidade: ");
		int quantidade = teclado.nextInt();
        Date data = new Date();
		
		
		TelaCadastroVenda tela = new TelaCadastroVenda();
		
		ControladorProduto controlProduto = new ControladorProduto();
		ArrayList<Produto> produtos = controlProduto.listar();
		tela.montarDisplayProduto(produtos);
		
		System.out.println("Insira o Codigo do Produto:");
		int proCod = teclado.nextInt();
		Produto produto = null;
		try {
			produto = tela.getProduto(proCod);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ControladorPedido controlPedido =  new ControladorPedido();
		ArrayList<Pedido> pedidos = controlPedido.listar();
		tela.montarDisplayPedido(pedidos);
		
        System.out.println("Insira o Codigo do Pedido:");
		int pedCod = teclado.nextInt();
        Pedido pedido = tela.getPedido(pedCod);

		
        
		Venda venda = new Venda(1, pedido, produto, quantidade, valor, data);
		return venda;
	}   
	
    private Produto getProduto(int codigo) throws ClassNotFoundException, ProdutoNaoEncontradoException, IOException, SQLException{
	  Fachada fachada = new Fachada();
	  return fachada.procurarProduto(codigo);
	}
	private Pedido getPedido(int codigo) throws ClassNotFoundException, IOException, SQLException, ClienteNaoExncontradoException, VendedorNaoEncontradoException, PedidoNaoEncontradoException{
	  Fachada fachada = new Fachada();
	  return fachada.procurarPedido(codigo);
	}
}
