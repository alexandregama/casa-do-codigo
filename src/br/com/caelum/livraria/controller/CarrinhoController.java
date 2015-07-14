package br.com.caelum.livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.livraria.dao.Livros;
import br.com.caelum.livraria.dao.Pedidos;
import br.com.caelum.livraria.modelo.Carrinho;
import br.com.caelum.livraria.modelo.Formato;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Pedido;

import com.google.common.base.Optional;

@Controller
@RequestMapping("/carrinho")
@Scope("request")
public class CarrinhoController {
	
	private Carrinho carrinho;
	
	private Livros livros;
	
	private Pedidos pedidos;
	
	@Autowired
	public CarrinhoController(Carrinho carrinho, Livros livros, Pedidos pedidos) {
		this.carrinho = carrinho;
		this.livros = livros;
		this.pedidos = pedidos;
	}
	
	@Deprecated //Spring eyes only
	CarrinhoController() {
	}
	
	@RequestMapping("/adicionarItem")
	public String adicionarItemNoCarrinho(@RequestParam("id") Integer idLivro, @RequestParam("formatoLivro") Formato formato)  {
		Optional<Livro> livro = livros.buscaPor(idLivro);
		
		if (livro.isPresent()) {
			carrinho.adicionarOuIncremantarQuantidadeDoItem(livro.get(), formato);
		}

		return CarrinhoRedirectUrl.REDIRECT_CARRINHO_LISTAR;
	}

	@RequestMapping("/removerItem")
	public String removerItemNoCarrinho(@RequestParam("codigo") String codigo, @RequestParam("formato") Formato formato, 
											RedirectAttributes modelo) {
		
		carrinho.removerItemPeloCodigoEFormato(codigo, formato);
		
		modelo.addFlashAttribute("messageInfo", "O item foi removido com sucesso.");
		
		return CarrinhoRedirectUrl.REDIRECT_CARRINHO_LISTAR;
	}
	
	@RequestMapping("/calcularCep")
	public String calcularCep(@RequestParam("cepDestino") String novoCepDestino) {
		
		this.carrinho.atualizarFrete(novoCepDestino);

		return CarrinhoRedirectUrl.REDIRECT_CARRINHO_LISTAR;
	}
	
	
	@RequestMapping("/criarPagamento")
	public String criarPagamento(@RequestParam("numeroCartao") String numeroCartao, @RequestParam("titularCartao") String titularCartao, 
									RedirectAttributes modelo) {
	
		if(ehStringVazia(numeroCartao) || ehStringVazia(titularCartao)) {
			modelo.addFlashAttribute("messageWarn", "Por favor preenche os dados do cartão!");
			return CarrinhoRedirectUrl.REDIRECT_CARRINHO_LISTAR;
		}

		this.carrinho.criarPagamento(numeroCartao, titularCartao);

		if(this.carrinho.isPagamentoCriado()) {
			modelo.addFlashAttribute("messageInfo", "O seu pagamento foi criado! - Por favor confirme o pedido.");
		} else {
			modelo.addFlashAttribute("messageWarn", "Pagamento não foi criado!");
		}
		
		return CarrinhoRedirectUrl.REDIRECT_CARRINHO_CONFIRMAR;
	}
	
	@RequestMapping("/confirmarPagamento")
	public String confirmarPagamento() {
		return CarrinhoRedirectUrl.JSP_CARRINHO_CONFIRMAR;
	}

	@RequestMapping("/finalizar")
	@Transactional
	public String finalizarPedido(RedirectAttributes modelo) {
		
		if(!carrinho.isFreteCalculado()) {
			modelo.addFlashAttribute("messageWarn", "O Frete deve ser calculado.");
			return CarrinhoRedirectUrl.REDIRECT_CARRINHO_LISTAR;
		}
		
		if(!carrinho.isPagamentoCriado()) {
			modelo.addFlashAttribute("messageWarn", "O pagamento deve ser aprovado antes.");
			return CarrinhoRedirectUrl.REDIRECT_CARRINHO_LISTAR;
		}

		Pedido pedido = this.carrinho.finalizarPedido();
		pedidos.salva(pedido);

		modelo.addFlashAttribute("messageInfo", "Pedido realizado. STATUS: " + pedido.getStatus());

		return CarrinhoRedirectUrl.REDIRECT_CARRINHO_LISTAR;
	}
	
	@RequestMapping("/listar")
	public String listar() {
		
		carrinho.verificaDisponibilidadeNoEstoque();
		
		return CarrinhoRedirectUrl.JSP_CARRINHO_LISTAR;
	}
	
	private boolean ehStringVazia(String string) {
		return string == null || string.trim().isEmpty();
	}
	
}

