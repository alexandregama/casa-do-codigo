package br.com.caelum.livraria.dao;

import com.google.common.base.Optional;

import br.com.caelum.livraria.modelo.Livro;

public interface Livros {

	Optional<Livro> buscaPor(Integer idLivro);

}
