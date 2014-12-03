package server.service;

import com.google.gson.annotations.SerializedName;

import server.ReponseEnum;
import server.RetourService;
import server.Services;

public class ListerUn extends Services{

	@SerializedName("nom")
	private String nom;
	
	@SerializedName("apogee")
	private int apogee;
	
	public ListerUn() {
		nom = "";
		apogee = 0;
	}
	
	public RetourService execute(){
		String key = nom+apogee;
		if(!bdd.exists(key)) 
			return new RetourService(
					ReponseEnum.NOM_INCONNU,
					"LISTER_UN",
					"Erreur : le nom entre est inconnu",
					""
			);
		
		return new RetourService(
				ReponseEnum.SUCCESS,
				"LISTER_UN",
				"Personne recherchee :",
				gson.toJson(bdd.getPersonne(key).toString())
		);
	}

}
