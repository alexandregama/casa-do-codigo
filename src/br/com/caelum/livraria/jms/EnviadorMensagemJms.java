package br.com.caelum.livraria.jms;

import java.io.Serializable;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.com.caelum.livraria.modelo.Pedido;

@Component
@Lazy(true)
public class EnviadorMensagemJms implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ConnectionFactory connection;

	@Autowired
	private Topic topico;
	
	public void enviar(Pedido pedido) {
		System.out.println("Enviando pedido para a fila");
		try (JMSContext context = connection.createContext("jms", "jms2")) {
			JMSProducer producer = context.createProducer();
			
			producer.setProperty("format", pedido.getFormato());
			
			producer.send(topico, pedido.toString());
		}
	}
}
