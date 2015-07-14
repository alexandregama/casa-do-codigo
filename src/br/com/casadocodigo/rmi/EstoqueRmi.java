package br.com.casadocodigo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EstoqueRmi extends Remote {

	public ItemEstoque getItemEstoque(String codigo) throws RemoteException;
	
}
