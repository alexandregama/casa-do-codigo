package br.com.caelum.livraria.modelo;

import br.com.casadocodigo.rmi.ItemEstoque;

public interface Estoque {

	ItemEstoque getItemEstoque(String codigo);
	
}
