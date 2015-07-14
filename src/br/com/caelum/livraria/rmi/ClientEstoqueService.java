package br.com.caelum.livraria.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.com.caelum.livraria.infra.UriIntegration;
import br.com.caelum.livraria.modelo.ItemEstoque;
import br.com.casadocodigo.rmi.EstoqueRmi;

public class ClientEstoqueService {

	public static void main(String[] args) {
		String rmiEstoqueUrl = UriIntegration.RMI_ESTOQUE_URL;
		try {
			EstoqueRmi estoque = (EstoqueRmi) Naming.lookup(rmiEstoqueUrl);
			ItemEstoque item = estoque.getItemEstoque("ARQ");
			
			System.out.println(item);
		} catch (MalformedURLException e) {
			throw new RuntimeException("Ocorreu um erro ao chamar o servico pelo nome associado: " + rmiEstoqueUrl, e);
		} catch (RemoteException e) {
			throw new RuntimeException("Ocorreu um erro ao fazer a chamada RMI", e);
		} catch (NotBoundException e) {
			throw new RuntimeException("Ocorreu um erro ao tentar identificar o servico pelo naming indicado: " + rmiEstoqueUrl);
		}
	}
	
}
