package RMI.client;
import RMI.protocole.*;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ClientRMI {
	public static void main(String[] args){
		try {
			LocateRegistry.getRegistry();
			Requete req = (Requete)Naming.lookup("rmi://localhost:2020/requete");
			ConstructionRequete constr;
			TraitementReponse r = new TraitementReponse();
			
			do{
				constr = new ConstructionRequete();
				String mess = constr.toString();
				if(mess.indexOf(ServiceN.Deconnection.toString())!=-1) break;
				String resp = req.execute(mess);
				System.out.println("Réponse du serveur :\n");
				r.affichage(resp);
			}while(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
