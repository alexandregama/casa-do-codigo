package br.com.caelum.livraria.infra;

import java.rmi.RemoteException;

import br.com.caelum.livraria.modelo.Estoque;
import br.com.caelum.livraria.modelo.ItemEstoque;
import br.com.casadocodigo.rmi.EstoqueRmi;

class RmiEstoque implements Estoque {

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
