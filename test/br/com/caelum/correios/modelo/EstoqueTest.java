package br.com.caelum.correios.modelo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.livraria.infra.RmiEstoque;
import br.com.caelum.livraria.modelo.Estoque;
import br.com.casadocodigo.rmi.EstoqueRmi;

public class EstoqueTest {

	private EstoqueRmi estoqueRmi;

	@Before
	public void setUp() {
		estoqueRmi = mock(EstoqueRmi.class);
	}
	
	@Test
	public void deveriaRetornarOItemEstoque() throws Exception {
		Estoque estoque = new RmiEstoque(estoqueRmi);
		
		estoque.getItemEstoque("SOAP");
		
		verify(estoqueRmi).getItemEstoque("SOAP");
	}
	
}
