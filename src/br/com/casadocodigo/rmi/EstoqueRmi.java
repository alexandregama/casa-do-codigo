package br.com.casadocodigo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.com.caelum.livraria.modelo.ItemEstoque;

public interface EstoqueRmi extends Remote {

	ItemEstoque getItemEstoque(String codigo) throws RemoteException;
	
}
