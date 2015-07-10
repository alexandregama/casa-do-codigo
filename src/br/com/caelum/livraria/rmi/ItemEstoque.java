package br.com.caelum.livraria.rmi;

import java.io.Serializable;

public class ItemEstoque implements Serializable {

	private static final long serialVersionUID = -6468531905839365906L;

	private final String codigo;

	private final int quantidade;
	
	private transient final int codigoInterno;

	public ItemEstoque(String codigo, int quantidade, int codigoInterno) {
		this.codigo = codigo;
		this.quantidade = quantidade;
		this.codigoInterno = codigoInterno;
	}
	
	@Override
	public String toString() {
		return "ItemEstoque [codigo=" + codigo + ", quantidade=" + quantidade
				+ ", codigoInterno=" + codigoInterno + "]";
	}

	public String getCodigo() {
		return codigo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public int getCodigoInterno() {
		return codigoInterno;
	}
	
}
