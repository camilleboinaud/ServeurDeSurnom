package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Donnees {
	
	private HashMap<String,Personne> donnees;
	
	public Donnees(ServeurCom server){
		donnees = new HashMap<String,Personne>();
	}
	
	public String ajouterPersonne(Personne p){
		String key = p.getNom()+p.getApogee();
		donnees.put(key, p);
		
		return p.toString();
	}
	
	public String ajouterSurnom(String nom, int apogee, String surnom){
		String key = nom+apogee;
		Personne p = donnees.get(key);
		ArrayList<String> s = p.getSurnoms();
		
		s.add(surnom);
		p.setSurnoms(s);
		
		donnees.remove(key);
		donnees.put(key, p);
		
		return p.toString();
	}
	
	public String listerRequete(String filtre){
		String data = "";
		String test = "";
		
		Iterator<String> it = donnees.keySet().iterator();
		while(it.hasNext()){
			test = donnees.get(it.next()).toString();
			if(test.indexOf("filtre")!=-1) data.concat(test);
		}		
		
		return data;
	}
	
	public String listerUn(String nom, int apogee){
		String key = nom+apogee;
		return donnees.get(key).toString();
	}
	
	public String listerTout(){
		String data = "";
		Iterator<String> it = donnees.keySet().iterator();
		
		while(it.hasNext()){
			data.concat(donnees.get(it.next()).toString());
		}
		
		return data;
	}
	
	public String modifierNom(String nom, int apogee, String newNom){
		String key = nom+apogee;
		Personne p = donnees.get(key);
		donnees.remove(key);
		
		p.setNom(newNom);
		key = newNom+apogee;
		
		donnees.put(key, p);
		
		return p.toString();
	}
	
	public String modifierSurnom(String nom, int apogee, String surnom, String newSurnom){
		String key = nom+apogee;
		Personne p = donnees.get(key);
		donnees.remove(key);
		
		ArrayList<String> surnoms = p.getSurnoms();
		surnoms.remove(surnom);
		surnoms.add(newSurnom);
		p.setSurnoms(surnoms);
		
		donnees.put(key, p);
		
		return p.toString();
	}
	
	public String modifierApogee(String nom, int apogee, int newApogee){
		String key = nom+apogee;
		Personne p = donnees.get(key);
		donnees.remove(key);
		
		p.setApogee(newApogee);
		key = nom+newApogee;
		
		donnees.put(key, p);
		
		return p.toString();
	}
	
	public String modifierDepartement(String nom, int apogee, String departement){
		String key = nom+apogee;
		Personne p = donnees.get(key);
		donnees.remove(key);
		
		p.setDepartement(departement);
		
		donnees.put(key, p);
		
		return p.toString();
	}
	
	public String modifierQualite(String nom, int apogee, String qualite){
		String key = nom+apogee;
		Personne p = donnees.get(key);
		donnees.remove(key);
		
		p.setQualite(qualite);
		
		donnees.put(key, p);
		
		return p.toString();
	}
	
	public String supprimerPersonne(String nom, String apogee){
		String key = nom+apogee;
		Personne p = donnees.get(key);
		donnees.remove(key);
		
		return p.toString();
	}
	
	public String supprimerSurnom(String nom, String apogee, String surnom){
		String key = nom+apogee;
		Personne p = donnees.get(key);
		donnees.remove(key);
		
		ArrayList<String> surnoms = p.getSurnoms();
		surnoms.remove(surnom);
		p.setSurnoms(surnoms);
		
		donnees.put(key, p);
		
		return p.toString();
	}

}
