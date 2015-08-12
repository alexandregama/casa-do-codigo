package br.com.caelum.livraria.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.caelum.estoque.soap.BuscaItensEstoque;
import br.com.caelum.estoque.soap.BuscaItensEstoqueResponse;
import br.com.caelum.estoque.soap.EstoqueWS;
import br.com.caelum.estoque.soap.EstoqueWSService;
import br.com.caelum.livraria.converter.ItemEstoqueWebServiceToItemEstoqueConverter;
import br.com.caelum.livraria.infra.EstoqueService;
import br.com.caelum.livraria.jms.EnviadorMensagemJms;
import br.com.caelum.livraria.rest.ClienteRest;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

@Component
@Scope("session")
public class Carrinho implements Serializable {

	private static final long serialVersionUID = 1L;

	private Set<ItemCompra> itensDeCompra = new LinkedHashSet<>();
	
	private BigDecimal valorFrete = BigDecimal.ZERO;
	
	private String cepDestino;
	
	private Pagamento pagamento;

	@Autowired
	private ClienteRest clienteRest;

	@Autowired
	private EnviadorMensagemJms enviador;
	
	@Autowired
	private ItemEstoqueWebServiceToItemEstoqueConverter itemEstoqueConverter;

	public void adicionarOuIncremantarQuantidadeDoItem(Livro livro, Formato formato) {

		ItemCompra item = new ItemCompra(livro, formato);

		if (jaExisteItem(item)) {
			ItemCompra itemCarrinho = this.procurarItem(item);
			itemCarrinho.incrementaQuantidade(item.getQuantidade());
		} else {
			this.itensDeCompra.add(item);
		}

		cancelarPagamento();
	}

	public void removerItemPeloCodigoEFormato(String codigo, Formato formato) {

		ItemCompra itemARemover = procurarItemPelaId(codigo, formato);

		if (itemARemover != null) {
			this.itensDeCompra.remove(itemARemover);
		}

		if (!this.isComLivrosImpressos()) {
			this.valorFrete = BigDecimal.ZERO;
		}

		cancelarPagamento();
	}

	public Pagamento criarPagamento(String numeroCartao, String nomeTitular) {
		Transacao transacao = new Transacao();
		transacao.setNumero(numeroCartao);
		transacao.setTitular(nomeTitular);
		transacao.setValor(this.getTotal());

		this.pagamento = this.clienteRest.criarPagamento(transacao);
		
		return this.pagamento;
	}

	private void cancelarPagamento() {
		this.pagamento = null;
		//poderia ter chamada do WS para cancelar o pagamento
	}

	public Pedido finalizarPedido() {

		Pedido pedido = new Pedido();
		pedido.setData(Calendar.getInstance());
		pedido.setItens(new LinkedHashSet<>(this.itensDeCompra));

		this.pagamento = this.clienteRest.confirmarPagamento(pagamento);
		
		pedido.setPagamento(pagamento);
		this.enviador.enviar(pedido);

		this.limparCarrinho();

		return pedido;
	}

	public void atualizarFrete(final String novoCepDestino) {
		this.cepDestino = novoCepDestino;

		this.valorFrete = new BigDecimal("25");
		//servico web do correios aqui
	}

	public String getCepDestino() {
		return cepDestino;
	}

	public List<ItemCompra> getItensCompra() {
		return new ArrayList<ItemCompra>(this.itensDeCompra);
	}

	public BigDecimal getTotal() {

		BigDecimal total = BigDecimal.ZERO;

		for (ItemCompra item : this.itensDeCompra) {
			total = total.add(item.getTotal());
		}

		return total.add(valorFrete);
	}

	public Pagamento getPagamento() {
		return this.pagamento;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public boolean isFreteCalculado() {
		return !this.valorFrete.equals(BigDecimal.ZERO) || !this.isComLivrosImpressos();
	}

	public boolean isPagamentoCriado() {
		if (this.pagamento == null) {
			return false;
		}
		return this.pagamento.ehCriado();
	}

	public boolean isProntoParaSerFinalizado() {
		return this.isFreteCalculado() && this.isPagamentoCriado();
	}

	public boolean isComLivrosImpressos() {

		for (ItemCompra itemCompra : this.itensDeCompra) {
			if (itemCompra.isImpresso()) {
				return true;
			}
		}
		return false;
	}

	private void atualizarQuantidadeDisponivelDoItemCompra(final ItemEstoque itemEstoque) {
		ItemCompra item = Iterables.find(this.itensDeCompra, new Predicate<ItemCompra>() {

			@Override
			public boolean apply(ItemCompra item) {
				return item.temCodigo(itemEstoque.getCodigo());
			}
		});

		item.setQuantidadeNoEstoque(itemEstoque.getQuantidade());
	}

	private void limparCarrinho() {
		this.itensDeCompra = new LinkedHashSet<>();
		this.valorFrete = BigDecimal.ZERO;
	}

	private boolean jaExisteItem(final ItemCompra item) {
		return this.itensDeCompra.contains(item);
	}

	private ItemCompra procurarItem(final ItemCompra itemProcurado) {
		for (ItemCompra item : this.itensDeCompra) {
			if (item.equals(itemProcurado)) {
				return item;
			}
		}
		throw new IllegalStateException("Item não encontrado");
	}

	private ItemCompra procurarItemPelaId(final String codigo, Formato formato) {

		for (ItemCompra item : this.itensDeCompra) {
			if (item.getCodigo().equals(codigo) && item.getFormato().equals(formato)) {
				return item;
			}
		}

		return null;
	}

	public void verificaDisponibilidadeNoEstoqueComRMI() {
		EstoqueService service = new EstoqueService();
		Estoque estoque = service.getEstoque();

		for (ItemCompra item : itensDeCompra) {
			if (item.isImpresso()) {
				ItemEstoque itemEstoque = estoque.getItemEstoque(item.getCodigo());

				System.out.println("Disponibilidade do item: " + itemEstoque);

				item.setQuantidadeNoEstoque(itemEstoque.getQuantidade());
			}
		}
	}
	
	public void verificarDisponibilidadeNoEstoqueComSOAP() {
		List<String> codigos = this.getCodigosDosItensImpressos();
		
		if (codigos != null && !codigos.isEmpty()) {
			EstoqueWS estoqueWS = new EstoqueWSService().getEstoqueWSPort();
			
			BuscaItensEstoque itensPeloCodigo = new BuscaItensEstoque();
			itensPeloCodigo.getCodigos().addAll(codigos);
			
			BuscaItensEstoqueResponse response = estoqueWS.buscaItensEstoque(itensPeloCodigo, "TOKEN123");
			
			List<br.com.caelum.estoque.soap.ItemEstoque> itensWebService = response.getItensEstoque();
			List<ItemEstoque> itens = itemEstoqueConverter.convert(itensWebService);
			
			for (ItemEstoque item : itens) {
				this.atualizarQuantidadeDisponivelDoItemCompra(item);
			}
		}
	}

	private List<String> getCodigosDosItensImpressos() {
		List<String> codigos = new ArrayList<>();

		for (ItemCompra item : this.itensDeCompra) {
			if (item.isImpresso())
				codigos.add(item.getCodigo());
		}
		return codigos;
	}
}
