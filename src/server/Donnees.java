package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Donnees {
	
	private HashMap<String,Personne> donnees;
	private ServeurCom server;
	
	public Donnees(ServeurCom server){
		donnees = new HashMap<String,Personne>();
		this.server = server;
	}
	
	public ReponseEnum ajouterPersonne(Personne p){
		String key = p.getNom()+p.getApogee();
		
		if(donnees.containsKey(key)) return ReponseEnum.NOM_EXISTE_DEJA;
		if(!p.getQualite().matches("PROF|ETUD[1-5]")) return ReponseEnum.SYNTAXE_QUALITE;
		if(String.valueOf(p.getApogee()).length()!=8) return ReponseEnum.SYNTAXE_APOGEE;
		if(!p.getDepartement().matches("SI|MAM|ELEC|ELECII|GE|GB|BAT|PEIP")) return ReponseEnum.DEPARTEMENT_INCONNU;
		
		donnees.put(key, p);

		server.setRetours(p.toString());
		
		return ReponseEnum.SUC;
	}
	
	public ReponseEnum ajouterSurnom(String nom, int apogee, String surnom){
		String key = nom+apogee;
		if(!donnees.containsKey(key)) return ReponseEnum.NOM_INCONNU;
		Personne p = donnees.get(key);
		ArrayList<String> s = p.getSurnoms();
		
		s.add(surnom);
		p.setSurnoms(s);
		
		donnees.remove(key);
		donnees.put(key, p);
		
		server.setRetours(p.toString());
		
		return ReponseEnum.SUC;
	}
	
	public ReponseEnum listerRequete(String filtre){
		String data = "";
		String test;
		
		Iterator<String> it = donnees.keySet().iterator();
		while(it.hasNext()){
			test = donnees.get(it.next()).toString();
			if(test.indexOf(filtre)!=-1) data.concat(test);
		}	
		
		if(data.equals("")) return ReponseEnum.FILTRE_INCONNU;
		
		server.setRetours(data);
		
		return ReponseEnum.SUC;
	}
	
	public ReponseEnum listerUn(String nom, int apogee){
		String key = nom+apogee;
		if(!donnees.containsKey(key)) return ReponseEnum.NOM_INCONNU;
		
		server.setRetours(donnees.get(key).toString());

		return ReponseEnum.SUC;
	}
	
	public ReponseEnum listerTout(){
		String data = "";
		Iterator<String> it = donnees.keySet().iterator();
		
		while(it.hasNext()){
			data.concat(donnees.get(it.next()).toString());
		}
		
		server.setRetours(data);
		
		return ReponseEnum.SUC;
	}
	
	public ReponseEnum modifierNom(String nom, int apogee, String newNom){
		String key = nom+apogee;
		if(!donnees.containsKey(key)) return ReponseEnum.NOM_INCONNU;

		Personne p = donnees.get(key);
		donnees.remove(key);
		
		p.setNom(newNom);
		key = newNom+apogee;
		
		donnees.put(key, p);
		server.setRetours(p.toString());
		
		return ReponseEnum.SUC;
	}
	
	public ReponseEnum modifierSurnom(String nom, int apogee, String surnom, String newSurnom){
		String key = nom+apogee;
		if(!donnees.containsKey(key)) return ReponseEnum.NOM_INCONNU;

		
		Personne p = donnees.get(key);
		donnees.remove(key);
		
		ArrayList<String> surnoms = p.getSurnoms();
		surnoms.remove(surnom);
		surnoms.add(newSurnom);
		p.setSurnoms(surnoms);
		
		donnees.put(key, p);
		server.setRetours(p.toString());
		
		return ReponseEnum.SUC;
	}
	
	public ReponseEnum modifierApogee(String nom, int apogee, int newApogee){
		String key = nom+apogee;
		if(!donnees.containsKey(key)) return ReponseEnum.NOM_INCONNU;
		if(String.valueOf(newApogee).length()!=8) return ReponseEnum.SYNTAXE_APOGEE;

		Personne p = donnees.get(key);
		donnees.remove(key);
		
		p.setApogee(newApogee);
		key = nom+newApogee;
		
		donnees.put(key, p);
		
		return ReponseEnum.SUC;
	}
	
	public ReponseEnum modifierDepartement(String nom, int apogee, String departement){
		String key = nom+apogee;
		if(!donnees.containsKey(key)) return ReponseEnum.NOM_INCONNU;
		if(!departement.matches("SI|MAM|ELEC|ELECII|GE|GB|BAT|PEIP")) return ReponseEnum.DEPARTEMENT_INCONNU;

		Personne p = donnees.get(key);
		donnees.remove(key);
		
		p.setDepartement(departement);
		
		donnees.put(key, p);
		server.setRetours(p.toString());
		
		return ReponseEnum.SUC;
	}
	
	public ReponseEnum modifierQualite(String nom, int apogee, String qualite){
		String key = nom+apogee;
		if(!donnees.containsKey(key)) return ReponseEnum.NOM_INCONNU;
		if(!qualite.matches("PROF|ETUD[1-5]")) return ReponseEnum.SYNTAXE_QUALITE;

		Personne p = donnees.get(key);
		donnees.remove(key);
		
		p.setQualite(qualite);
		
		donnees.put(key, p);
		server.setRetours(p.toString());
		
		return ReponseEnum.SUC;
	}
	
	public ReponseEnum supprimerPersonne(String nom, String apogee){
		String key = nom+apogee;
		if(!donnees.containsKey(key)) return ReponseEnum.NOM_INCONNU;
		Personne p = donnees.get(key);
		donnees.remove(key);
		server.setRetours(p.toString());
		
		return ReponseEnum.SUC;
	}
	
	public ReponseEnum supprimerSurnom(String nom, String apogee, String surnom){
		String key = nom+apogee;
		if(!donnees.containsKey(key)) return ReponseEnum.NOM_INCONNU;

		Personne p = donnees.get(key);
		donnees.remove(key);
		
		ArrayList<String> surnoms = p.getSurnoms();
		if(!surnoms.contains(surnom)) return ReponseEnum.SURNOM_INCONNU;
		
		surnoms.remove(surnom);
		p.setSurnoms(surnoms);
		
		donnees.put(key, p);
		server.setRetours(p.toString());
		
		return ReponseEnum.SUC;
	}

}
