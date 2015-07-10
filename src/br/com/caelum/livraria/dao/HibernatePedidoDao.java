package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import br.com.caelum.livraria.modelo.Pedido;

@Component
public class HibernatePedidoDao implements Pedidos {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void salva(Pedido pedido) {
		manager.persist(pedido);
	}

}
