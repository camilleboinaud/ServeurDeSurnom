package server;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Services {
	
	Donnees bdd;
	GsonBuilder builder;
    Gson gson;
	
	public Services(){
		this.bdd = new Donnees();
		this.builder = new GsonBuilder();
		this.gson = builder.setPrettyPrinting().create();
	}
	
	public RetourService ajouterPersonne(Personne p){
		String key = p.getNom()+p.getApogee();
		
		if(bdd.exists(key)) 
			return new RetourService(
					ReponseEnum.NOM_EXISTE_DEJA,
					"AJOUTER_NOM",
					"Erreur : le nom existe déjà",
					""
			);
		
		if(!p.getQualite().matches("PROF|ETUD[1-5]")) 
			return new RetourService(
					ReponseEnum.SYNTAXE_QUALITE,
					"AJOUTER_NOM",
					"Erreur : la syntaxe de la qualité n'est pas correcte",
					""
			);
		
		if(String.valueOf(p.getApogee()).length()!=8) 
			return new RetourService(
					ReponseEnum.SYNTAXE_APOGEE,
					"AJOUTER_NOM",
					"Erreur : la syntaxe du numéro apogée n'est pas correcte",
					""
			);
		
		if(!p.getDepartement().matches("SI|MAM|ELEC|ELECII|GE|GB|BAT|PEIP")) 
			return new RetourService(
					ReponseEnum.DEPARTEMENT_INCONNU,
					"AJOUTER_NOM",
					"Erreur : la syntaxe du département n'est pas correcte",
					""
			);
		
		bdd.insert(key, p);
			
		return new RetourService(
				ReponseEnum.SUCCESS,
				"AJOUTER_NOM",
				"Le nom a bien été ajouté :",
				gson.toJson(p)
		);
	}
	
	public RetourService ajouterSurnom(String nom, int apogee, String surnom){
		String key = nom+apogee;
		
		if(!bdd.exists(key)) 
			return  new RetourService(
					ReponseEnum.NOM_INCONNU,
					"AJOUTER_SURNOM",
					"Erreur : le nom entré est inconnu",
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
				"Le surnom a bien été ajouté :",
				gson.toJson(p)
		);
	}
	
	public RetourService listerRequete(String filtre){
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
					"Erreur : le filtre de recherche n'a retourné aucun résultat",
					""
			);
		
		return new RetourService(
				ReponseEnum.SUCCESS,
				"LISTER_REQUETE",
				"Résultats de la recherche :",
				gson.toJson(data)
		);
	}
	
	public RetourService listerUn(String nom, int apogee){
		String key = nom+apogee;
		if(!bdd.exists(key)) 
			return new RetourService(
					ReponseEnum.NOM_INCONNU,
					"LISTER_UN",
					"Erreur : le nom entré est inconnu",
					""
			);
		
		return new RetourService(
				ReponseEnum.SUCCESS,
				"LISTER_UN",
				"Personne recherchée :",
				gson.toJson(bdd.getPersonne(key))
		);
	}
	
	public RetourService listerTout(){
		String data = "";
		Iterator<String> it = bdd.getIterator();
		
		do{
			data = data +bdd.getPersonne(it.next()).toString();
		}while(it.hasNext());
	
		return new RetourService(
				ReponseEnum.SUCCESS,
				"LISTER_TOUT",
				"Toutes les personnes de la base de données :",
				gson.toJson(data)
		);
	}
	
	public RetourService modifierNom(String nom, int apogee, String newNom){
		String key = nom+apogee;
		if(!bdd.exists(key)) 
			return new RetourService(
					ReponseEnum.NOM_INCONNU,
					"MODIFIER_NOM",
					"Erreur : le nom entré est inconnu",
					""
			);

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		p.setNom(newNom);
		key = newNom+apogee;
		
		bdd.insert(key, p);
	
		return new RetourService(
				ReponseEnum.SUCCESS, 
				"MODIFIER_NOM",
				"Le nom a bien été modifié :",
				gson.toJson(p)
		);
	}
	
	public RetourService modifierSurnom(String nom, int apogee, String surnom, String newSurnom){
		String key = nom+apogee;
		
		if(!bdd.exists(key))
			return new RetourService(
					ReponseEnum.NOM_INCONNU,
					"MODIFIER_SURNOM",
					"Erreur : le nom entré est inconnu",
					""
			);

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		ArrayList<String> surnoms = p.getSurnoms();
		
		if(surnoms.contains(surnom))
			return new RetourService(
					ReponseEnum.SURNOM_INCONNU,
					"MODIFIER_SURNOM",
					"Erreur : le surnom entré est inconnu",
					""
			);
		
		surnoms.remove(surnom);
		surnoms.add(newSurnom);
		p.setSurnoms(surnoms);
		
		bdd.insert(key, p);
		
		return new RetourService(
				ReponseEnum.SUCCESS, 
				"MODIFIER_SURNOM",
				"Le surnom a bien été modifié :",
				gson.toJson(p)
		);
	}
	
	public RetourService modifierDepartement(String nom, int apogee, String departement){
		String key = nom+apogee;
		if(!bdd.exists(key)) 
			return new RetourService(
					ReponseEnum.NOM_INCONNU,
					"MODIFIER_DEPARTEMENT",
					"Erreur : le nom entré est inconnu",
					""
			);
			
		if(!departement.matches("SI|MAM|ELEC|ELECII|GE|GB|BAT|PEIP")) 
			return new RetourService(
					ReponseEnum.DEPARTEMENT_INCONNU,
					"MODIFIER_DEPARTEMENT",
					"Erreur : la syntaxe du département n'est pas correcte",
					""
			);

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		p.setDepartement(departement);
		
		bdd.insert(key, p);
		
		return new RetourService(
				ReponseEnum.SUCCESS, 
				"MODIFIER_DEPARTEMENT",
				"Le département a bien été modifié :",
				gson.toJson(p)
		);
	}
	
	public RetourService modifierQualite(String nom, int apogee, String qualite){
		String key = nom+apogee;
		
		if(!bdd.exists(key))
			return new RetourService(
					ReponseEnum.NOM_INCONNU,
					"MODIFIER_QUALITE",
					"Erreur : le nom entré est inconnu",
					""
			);
		
		if(!qualite.matches("PROF|ETUD[1-5]")) 
			return new RetourService(
					ReponseEnum.SYNTAXE_QUALITE,
					"MODIFIER_QUALITE",
					"Erreur : la syntaxe de la qualité n'est pas correcte",
					""
			);

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		p.setQualite(qualite);
		
		bdd.insert(key, p);
		
		return new RetourService(
				ReponseEnum.SUCCESS, 
				"MODIFIER_QUALITE",
				"La qualité a bien été modifié :",
				gson.toJson(p)
		);
	}
	
	public RetourService supprimerPersonne(String nom, int apogee){
		String key = nom+apogee;
		
		if(!bdd.exists(key))
			return new RetourService(
					ReponseEnum.NOM_INCONNU,
					"SUPPRIMER_NOM",
					"Erreur : le nom entré est inconnu",
					""
			);
			
		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		return new RetourService(
				ReponseEnum.SUCCESS, 
				"SUPPRIMER_NOM",
				"La personne a bien été supprimée :",
				gson.toJson(p)
		);
	}
	
	public RetourService supprimerSurnom(String nom, int apogee,String surnom){
		String key = nom+apogee;
		
		if(!bdd.exists(key))
			return new RetourService(
					ReponseEnum.NOM_INCONNU,
					"SUPPRIMER_SURNOM",
					"Erreur : le nom entré est inconnu",
					""
			);

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		ArrayList<String> surnoms = p.getSurnoms();
		if(!surnoms.contains(surnom))
			return new RetourService(
					ReponseEnum.SURNOM_INCONNU,
					"SUPPRIMER_SURNOM",
					"Erreur : le surnom entré est inconnu",
					""
			);
		
		surnoms.remove(surnom);
		p.setSurnoms(surnoms);
		
		bdd.insert(key, p);
		
		return new RetourService(
				ReponseEnum.SUCCESS, 
				"SUPPRIMER_SURNOM",
				"Le surnom a bien été supprimé :",
				gson.toJson(p)
		);
	}
	
	public RetourService deconnection(){
		return new RetourService(
				ReponseEnum.DECONNECTION, 
				"DECONNECTION",
				"Déconnection du serveur",
				""
		);
	}


}