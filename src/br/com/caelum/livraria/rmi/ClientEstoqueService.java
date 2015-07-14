package br.com.caelum.livraria.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.com.casadocodigo.rmi.EstoqueRmi;
import br.com.casadocodigo.rmi.ItemEstoque;

public class ClientEstoqueService {

	private static final String ESTOQUE_SERVICE_URL = "rmi://localhost:1099/estoque";

	public static void main(String[] args) {
		try {
			EstoqueRmi estoque = (EstoqueRmi) Naming.lookup(ESTOQUE_SERVICE_URL);
			ItemEstoque item = estoque.getItemEstoque("ARQ");
			
			System.out.println(item);
		} catch (MalformedURLException e) {
			throw new RuntimeException("Ocorreu um erro ao chamar o servico pelo nome associado: " + ESTOQUE_SERVICE_URL, e);
		} catch (RemoteException e) {
			throw new RuntimeException("Ocorreu um erro ao fazer a chamada RMI", e);
		} catch (NotBoundException e) {
			throw new RuntimeException("Ocorreu um erro ao tentar identificar o servico pelo naming indicado: " + ESTOQUE_SERVICE_URL);
		}
	}
	
}
