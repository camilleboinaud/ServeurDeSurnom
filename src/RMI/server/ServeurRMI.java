package RMI.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import RMI.protocole.*;

public class ServeurRMI {
	
	public static void main(String[] args){
		
		int port = 2020;
		String host = "localhost";

		try {
			LocateRegistry.createRegistry(port); 
			Requete obj = new TraitementRequete();
			Naming.rebind("rmi://"+host+":"+port+"/requete", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
