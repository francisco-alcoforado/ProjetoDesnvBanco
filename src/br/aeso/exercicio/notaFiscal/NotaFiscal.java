package br.aeso.exercicio.notaFiscal;

import java.util.Date;

import br.aeso.exercicio.pedido.Pedido;

public class NotaFiscal{
	private int codigo;
	private String Emitente;
	private Pedido pedido;
	private Date Data_Emissao;
	public NotaFiscal(int codigo, String emitente, Pedido pedido, Date data_Emissao) {
		super();
		this.codigo = codigo;
		Emitente = emitente;
		this.pedido = pedido;
		Data_Emissao = data_Emissao;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getEmitente() {
		return Emitente;
	}
	public void setEmitente(String emitente) {
		Emitente = emitente;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Date getData_Emissao() {
		return Data_Emissao;
	}
	public void setData_Emissao(Date data_Emissao) {
		Data_Emissao = data_Emissao;
	}
	@Override
	public String toString() {
		return "NotaFiscal [codigo=" + codigo + ", Emitente=" + Emitente + ", pedido=" + pedido + ", Data_Emissao="
				+ Data_Emissao + "]";
	}
	
}
