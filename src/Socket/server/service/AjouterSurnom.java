package Socket.server.service;
import Socket.server.*;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class AjouterSurnom extends Services{

	@SerializedName("nom")
	private String nom;
	
	@SerializedName("apogee")
	private int apogee;
	
	@SerializedName("surnom")
	private String surnom;
	
	public AjouterSurnom() {
		nom = "";
		apogee = 0;	
		surnom = "";
	}
	
	public RetourService execute(){
		String key = nom+apogee;
		
		if(!bdd.exists(key)) 
			return  new RetourService(
					ReponseEnum.NOM_INCONNU,
					"AJOUTER_SURNOM",
					"Erreur : le nom entre est inconnu",
					""
			);
		
		Personne p = bdd.getPersonne(key);
		ArrayList<String> s = p.getSurnoms();
		
		s.add(surnom);
		p.setSurnoms(s);
			
		bdd.deletePersonne(key);
		bdd.insert(key, p);
		
		return  new RetourService(
				ReponseEnum.SUCCESS,
				"AJOUTER_SURNOM",
				"Le surnom a bien ete ajoute :",
				gson.toJson(p.toString())
		);
	}
		
}
