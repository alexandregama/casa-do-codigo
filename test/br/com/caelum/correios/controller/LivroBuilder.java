package br.com.caelum.correios.controller;

import java.math.BigDecimal;

import br.com.caelum.livraria.modelo.Livro;

public class LivroBuilder {

	private String codigo;
	private String titulo;
	private String tituloCurto;
	private String nomeAutor;
	private String imagem;
	private BigDecimal valorEbook;
	private BigDecimal valorImpresso;
	private String descricao;
	
	public LivroBuilder comCodigo(String codigo) {
		this.codigo = codigo;
		return this;
	}
	
	public LivroBuilder deTitulo(String titulo) {
		this.titulo = titulo;
		return this;
	}
	
	public LivroBuilder comTituloCurto(String tituloCurto) {
		this.tituloCurto = tituloCurto;
		return this;
	}
	
	public LivroBuilder doAutor(String autor) {
		this.nomeAutor = autor;
		return this;
	}
	
	public LivroBuilder comImagem(String imagem) {
		this.imagem = imagem;
		return this;
	}
	
	public LivroBuilder deDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	
	public LivroBuilder comValorDoEbookDe(BigDecimal valor) {
		this.valorEbook = valor;
		return this;
	}
	
	public LivroBuilder comValorDoImpressoDe(BigDecimal valor) {
		this.valorImpresso = valor;
		return this;
	}
	
	public Livro build() {
		return new Livro(codigo, titulo, tituloCurto, descricao, nomeAutor, imagem, valorEbook, valorImpresso);
	}
	
}
