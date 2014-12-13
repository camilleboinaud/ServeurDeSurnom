package Socket.server.service;
import Socket.server.*;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class SupprimerSurnom extends Services{
	
	@SerializedName("nom")
	private String nom;
	
	@SerializedName("apogee")
	private int apogee;
	
	@SerializedName("surnom")
	private String surnom;

	public SupprimerSurnom() {
		nom = "";
		apogee = 0;
		surnom = "";
	}
	
	public RetourService execute(){
		String key = nom+apogee;
		
		if(!bdd.exists(key))
			return new RetourService(
					ReponseEnum.NOM_INCONNU,
					"SUPPRIMER_SURNOM",
					"Erreur : le nom entre est inconnu",
					""
			);

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		ArrayList<String> surnoms = p.getSurnoms();
		if(!surnoms.contains(surnom))
			return new RetourService(
					ReponseEnum.SURNOM_INCONNU,
					"SUPPRIMER_SURNOM",
					"Erreur : le surnom entre est inconnu",
					""
			);
		
		surnoms.remove(surnom);
		p.setSurnoms(surnoms);
		
		bdd.insert(key, p);
		
		return new RetourService(
				ReponseEnum.SUCCESS, 
				"SUPPRIMER_SURNOM",
				"Le surnom a bien ete supprime :",
				gson.toJson(p.toString())
		);
	}

}
