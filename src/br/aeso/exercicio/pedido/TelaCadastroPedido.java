package br.aeso.exercicio.pedido;

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
import br.aeso.exercicio.pedido.Pedido;
import br.aeso.exercicio.pedido.PedidoJaCadastradoException;
import br.aeso.exercicio.pedido.PedidoNaoEncontradoException;
import br.aeso.exercicio.pedido.TelaCadastroPedido;
import br.aeso.exercicio.util.CPFInvalidoException;
import br.aeso.exercicio.vendedor.ControladorVendedor;
import br.aeso.exercicio.vendedor.Vendedor;
import br.aeso.exercicio.vendedor.VendedorNaoEncontradoException;

public class TelaCadastroPedido {
	private static Scanner teclado = new Scanner(System.in);
	public static void main(String args[]) throws IllegalArgumentException, CPFInvalidoException, PedidoJaCadastradoException, PedidoNaoEncontradoException{
		Fachada fachada = new Fachada();
		System.out.println("Escolha a sua Opção: ");
		System.out.println("1 - Cadastrar, 2 - lista, 3 - remover:");
		int opcao = teclado.nextInt();
		if(opcao == 1){
			try{
				Pedido pedido = TelaCadastroPedido.cadastrarPedido();
				fachada.cadastrarPedido(pedido);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else if(opcao == 2){
			try{
				ArrayList<Pedido> lista = fachada.listarPedido();
				TelaCadastroPedido.montarDisplay(lista);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else if(opcao == 3){
			System.out.println("Digite o Codigo:");
			int codigo = teclado.nextInt();
			try{
				fachada.removerPedido(codigo);
				ArrayList<Pedido> lista = fachada.listarPedido();
				TelaCadastroPedido.montarDisplay(lista);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		
	}
	public static void montarDisplay(ArrayList<Pedido> lista){
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
	public static void montarDisplayCliente(ArrayList<Cliente> lista){
		System.out.println("Codigo \tNome \tEmail \tCPF \tEmail");
		for(Cliente cliente : lista){
			System.out.print(cliente.getCodigo());
			System.out.print("\t");
			System.out.print(cliente.getNome());
			System.out.print("\t");
			System.out.print(cliente.getCpf());
			System.out.print("\t");
			System.out.print(cliente.getCEP());
			System.out.print("\t");
			if(cliente.getEmails().size() > 0){
				System.out.print(cliente.getEmails().get(0).getEmail());
			}
			System.out.print("\n");
		}
	}
	public static void montarDisplayVendedor(ArrayList<Vendedor> lista){
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
	public static Pedido cadastrarPedido() throws ClassNotFoundException, IOException, SQLException, ClienteNaoExncontradoException, VendedorNaoEncontradoException{
		System.out.println("Cadastro de CLiente: ");
		System.out.println("Insira o codigo: ");
		int codigo = teclado.nextInt();
        teclado.nextLine();
		System.out.println("Insira o valor: ");
		double valor = teclado.nextDouble();
		teclado.nextLine();
        Date data = new Date();
		
		
		TelaCadastroPedido tela = new TelaCadastroPedido();
		
		ControladorCliente controlCliente = new ControladorCliente();
		ArrayList<Cliente> clientes = controlCliente.listar();
		tela.montarDisplayCliente(clientes);
		
		System.out.println("Insira o Codigo do Cliente:");
		int cliCod = teclado.nextInt();
        Cliente cliente = tela.getCliente(cliCod);
        
        ControladorVendedor controlVendedor = new ControladorVendedor();
		ArrayList<Vendedor> vendedores = controlVendedor.listar();
		tela.montarDisplayVendedor(vendedores);
		
        System.out.println("Insira o Codigo do Vendedor:");
		int venCod = teclado.nextInt();
        Vendedor vendedor = tela.getVendedor(venCod);
        
		Pedido pedido = new Pedido(1, cliente, vendedor, valor, data);
		return pedido;
	}   
	private Cliente getCliente(int codigo) throws ClassNotFoundException, ClienteNaoExncontradoException, IOException, SQLException{
	  Fachada fachada = new Fachada();
	  return fachada.procurarCliente(codigo);
	}
	private Vendedor getVendedor(int codigo) throws ClassNotFoundException, IOException, SQLException, VendedorNaoEncontradoException{
	  Fachada fachada = new Fachada();
	  return fachada.procurarVendedor(codigo);
	}
}
