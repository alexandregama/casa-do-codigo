package br.com.caelum.livraria.rest;

import java.io.Serializable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import org.springframework.stereotype.Component;

import br.com.caelum.livraria.modelo.Pagamento;
import br.com.caelum.livraria.modelo.Transacao;

@Component
public class ClienteRest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final String SERVER_URI = "http://localhost:8080/casa-do-codigo-webservice";
	private static final String ENTRY_POINT = "/pagamentos/";

	public Pagamento criarPagamento(Transacao transacao) {
		Client client = ClientBuilder.newClient();
		Pagamento pagamentoCriado = client
				.target(SERVER_URI + ENTRY_POINT)
				.request()
				.buildPost(Entity.json(transacao))
				.invoke(Pagamento.class);
		
		System.out.println("Pagamento criado: " + pagamentoCriado);
		
		return pagamentoCriado;
	}

	public Pagamento confirmarPagamento(Pagamento pagamento) {
		return null;
	}

}
