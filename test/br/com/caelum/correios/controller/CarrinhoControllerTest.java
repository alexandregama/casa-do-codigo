package br.com.caelum.correios.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.caelum.livraria.controller.CarrinhoController;
import br.com.caelum.livraria.controller.CarrinhoRedirectUrl;
import br.com.caelum.livraria.dao.Livros;
import br.com.caelum.livraria.dao.Pedidos;
import br.com.caelum.livraria.modelo.Carrinho;
import br.com.caelum.livraria.modelo.Formato;
import br.com.caelum.livraria.modelo.Livro;

import com.google.common.base.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CarrinhoControllerTest {

	@Mock
	private Carrinho carrinho;
	
	@Mock
	private Livros livros;
	
	@Mock
	private Pedidos pedidos;

	private CarrinhoController controller;
	
	private Livro livroQualquer = new LivroBuilder().build();

	@Before
	public void setUp() {
		controller = new CarrinhoController(carrinho, livros, pedidos);
	}
	
	@Test
	public void deveriaAdicionarUmLivroNoCarrinhoQuandoOLivroExiste() throws Exception {
		Livro livro = new LivroBuilder().build();
		Optional<Livro> livroOptional = Optional.of(livro);
		
		when(livros.buscaPor(1)).thenReturn(livroOptional);
		
		controller.adicionarItemNoCarrinho(1, Formato.IMPRESSO);
		
		verify(carrinho).adicionarOuIncremantarQuantidadeDoItem(livro, Formato.IMPRESSO);
	}
	
	@Test
	public void naoDeveriaAdicionarUmLivroNoCarrinhoQuandoOLivroNaoExiste() throws Exception {
		Optional<Livro> livroInexistente = Optional.absent();
		
		when(livros.buscaPor(1)).thenReturn(livroInexistente);
		
		controller.adicionarItemNoCarrinho(1, Formato.IMPRESSO);
		
		verify(carrinho, never()).adicionarOuIncremantarQuantidadeDoItem(livroQualquer, Formato.IMPRESSO);
	}
	
	@Test
	public void deveriaRedirecionarparaAListagemDoCarrinhoQuandoUmLivroEhAdicionado() throws Exception {
		Livro livro = new LivroBuilder().build();
		Optional<Livro> livroOptional = Optional.of(livro);
		
		when(livros.buscaPor(1)).thenReturn(livroOptional);
		
		String urlDeRedirecionamento = controller.adicionarItemNoCarrinho(1, Formato.IMPRESSO);
		
		assertEquals(CarrinhoRedirectUrl.REDIRECT_CARRINHO_LISTAR, urlDeRedirecionamento);
	}
	
}
