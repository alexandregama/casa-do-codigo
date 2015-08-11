package br.com.caelum.livraria.converter;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.caelum.livraria.modelo.ItemEstoque;

@Component
public class ItemEstoqueWebServiceToItemEstoqueConverter {

	public List<ItemEstoque> convert(List<br.com.caelum.estoque.soap.ItemEstoque> itensWebService) {
		List<ItemEstoque> itens = new ArrayList<>();
		for (br.com.caelum.estoque.soap.ItemEstoque itemEstoque : itensWebService) {
			ItemEstoque item = new ItemEstoque(itemEstoque.getCodigo(), itemEstoque.getQuantidade(), 0);
			itens.add(item);
		}
		return itens;
	}

}
