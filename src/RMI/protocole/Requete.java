package RMI.protocole;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Requete extends Remote {
	
	public abstract String execute(String json) throws RemoteException;

}
