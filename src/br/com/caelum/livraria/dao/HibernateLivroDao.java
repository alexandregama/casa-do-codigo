package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

import br.com.caelum.livraria.modelo.Livro;

@Component
class HibernateLivroDao implements Livros {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Optional<Livro> buscaPor(Integer idLivro) {
		Livro livro = manager.find(Livro.class, idLivro);
		
		return Optional.fromNullable(livro);
	}
	
	
}
