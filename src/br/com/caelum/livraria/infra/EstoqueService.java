package br.com.caelum.livraria.infra;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.com.caelum.livraria.modelo.Estoque;
import br.com.casadocodigo.rmi.EstoqueRmi;

public class EstoqueService {

	public Estoque getEstoque() {
		String url = UriIntegration.RMI_ESTOQUE_URL;
		try {
			EstoqueRmi estoque = (EstoqueRmi) Naming.lookup(url);
			return new RmiEstoque(estoque);
		} catch (MalformedURLException e) {
			throw new RuntimeException("Ocorreu um erro com a formacao da seguinte url para a chamada RMI: " + url, e);
		} catch (RemoteException e) {
			throw new RuntimeException("Ocorreu um erro com a chamada RMI ao Serviço de Estoque", e);
		} catch (NotBoundException e) {
			throw new RuntimeException("Ocorreu um erro ao chamar o Serviço de Estoque com a url: " + url, e);
		}
	}

}
