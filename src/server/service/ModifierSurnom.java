package server.service;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import server.Personne;
import server.ReponseEnum;
import server.RetourService;
import server.Services;

public class ModifierSurnom extends Services{
	
	@SerializedName("nom")
	private String nom;
	
	@SerializedName("apogee")
	private int apogee;
	
	@SerializedName("surnom")
	private String surnom;
	
	@SerializedName("nouveauSurnom")
	private String nouveauSurnom;

	public ModifierSurnom() {
		nom = "";
		apogee = 0;
		surnom = "";
		nouveauSurnom = "";
	}

	public RetourService execute(){
		String key = nom+apogee;
		
		if(!bdd.exists(key))
			return new RetourService(
					ReponseEnum.NOM_INCONNU,
					"MODIFIER_SURNOM",
					"Erreur : le nom entre est inconnu",
					""
			);

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		ArrayList<String> surnoms = p.getSurnoms();
		
		if(surnoms.contains(surnom))
			return new RetourService(
					ReponseEnum.SURNOM_INCONNU,
					"MODIFIER_SURNOM",
					"Erreur : le surnom entre est inconnu",
					""
			);
		
		surnoms.remove(surnom);
		surnoms.add(nouveauSurnom);
		p.setSurnoms(surnoms);
		
		bdd.insert(key, p);
		
		return new RetourService(
				ReponseEnum.SUCCESS, 
				"MODIFIER_SURNOM",
				"Le surnom a bien ete modifie :",
				gson.toJson(p.toString())
		);
	}
}
