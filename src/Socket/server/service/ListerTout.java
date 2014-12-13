package Socket.server.service;
import Socket.server.*;
import java.util.Iterator;

public class ListerTout extends Services {

	public ListerTout() {}
	
	public RetourService execute(){
		String data = "";
		Iterator<String> it = bdd.getIterator();
		
		do{
			data = data +bdd.getPersonne(it.next()).toString();
		}while(it.hasNext());
	
		return new RetourService(
				ReponseEnum.SUCCESS,
				"LISTER_TOUT",
				"Toutes les personnes de la base de donnees :",
				gson.toJson(data)
		);
	}

}
