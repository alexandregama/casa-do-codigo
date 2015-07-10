package br.com.caelum.livraria.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EstoqueRmi extends Remote {

	ItemEstoque getItemEstoque(String codigo) throws RemoteException;
	
}
