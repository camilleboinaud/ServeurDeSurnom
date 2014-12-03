package server.service;

import java.util.Iterator;

import com.google.gson.annotations.SerializedName;

import server.ReponseEnum;
import server.RetourService;
import server.Services;

public class ListerRequete extends Services{
	
	@SerializedName("filtre")
	private String filtre;

	public ListerRequete() {
		filtre = "";
	}
	
	public RetourService execute(){
		String data = "";
		String test;
		
		Iterator<String> it = bdd.getIterator();
		while(it.hasNext()){
			test = bdd.getPersonne(it.next()).toString();
			if(test.indexOf(filtre)!=-1) data+=test;
		}	
		
		if(data.equals("")) 
			return new RetourService(
					ReponseEnum.FILTRE_INCONNU,
					"LISTER_REQUETE",
					"Erreur : le filtre de recherche n'a retourne aucun resultat",
					""
			);
		
		return new RetourService(
				ReponseEnum.SUCCESS,
				"LISTER_REQUETE",
				"Resultats de la recherche :",
				gson.toJson(data)
		);
	}

}
