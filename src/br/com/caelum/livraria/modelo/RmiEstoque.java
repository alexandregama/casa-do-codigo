package br.com.caelum.livraria.modelo;

import java.rmi.RemoteException;

import br.com.casadocodigo.rmi.EstoqueRmi;
import br.com.casadocodigo.rmi.ItemEstoque;

public class RmiEstoque implements Estoque {

	private EstoqueRmi estoqueRmi;

	public RmiEstoque(EstoqueRmi estoque) {
		this.estoqueRmi = estoque;
	}
	
	public ItemEstoque getItemEstoque(String codigo) {
		try {
			return estoqueRmi.getItemEstoque(codigo);
		} catch (RemoteException e) {
			throw new RuntimeException("Houve uma indisponibilidade com o serviço de RMI", e);
		}
	}

}
