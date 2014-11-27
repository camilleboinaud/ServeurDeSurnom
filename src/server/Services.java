package server;

import java.util.ArrayList;
import java.util.Iterator;

public class Services {
	
	Donnees bdd;
	
	public Services(){
		this.bdd = new Donnees();
	}
	
	public RetourService ajouterPersonne(Personne p){
		String key = p.getNom()+p.getApogee();
		
		if(bdd.exists(key)) return new RetourService(ReponseEnum.NOM_EXISTE_DEJA,"");
		if(!p.getQualite().matches("PROF|ETUD[1-5]")) return new RetourService(ReponseEnum.SYNTAXE_QUALITE,"");
		if(String.valueOf(p.getApogee()).length()!=8) return new RetourService(ReponseEnum.SYNTAXE_APOGEE,"");
		if(!p.getDepartement().matches("SI|MAM|ELEC|ELECII|GE|GB|BAT|PEIP")) return new RetourService(ReponseEnum.DEPARTEMENT_INCONNU,"");
		
		bdd.insert(key, p);

		return new RetourService(ReponseEnum.SUC, p.toString());
	}
	
	public RetourService ajouterSurnom(String nom, int apogee, String surnom){
		String key = nom+apogee;
		if(!bdd.exists(key)) return  new RetourService(ReponseEnum.NOM_INCONNU,"");
		Personne p = bdd.getPersonne(key);
		ArrayList<String> s = p.getSurnoms();
		
		s.add(surnom);
		p.setSurnoms(s);
			
		bdd.deletePersonne(key);
		bdd.insert(key, p);
		
		return  new RetourService(ReponseEnum.SUC,p.toString());
	}
	
	public RetourService listerRequete(String filtre){
		String data = "";
		String test;
		
		Iterator<String> it = bdd.getIterator();
		while(it.hasNext()){
			test = bdd.getPersonne(it.next()).toString();
			if(test.indexOf(filtre)!=-1) data+=test;
		}	
		
		if(data.equals("")) return new RetourService(ReponseEnum.FILTRE_INCONNU,"");
		
		return new RetourService(ReponseEnum.SUC, data);
	}
	
	public RetourService listerUn(String nom, int apogee){
		String key = nom+apogee;
		if(!bdd.exists(key)) return new RetourService(ReponseEnum.NOM_INCONNU,"");
		
		return new RetourService(ReponseEnum.SUC,bdd.getPersonne(key).toString());
	}
	
	public RetourService listerTout(){
		String data = "";
		Iterator<String> it = bdd.getIterator();
		
		do{
			data = data +bdd.getPersonne(it.next()).toString();
		}while(it.hasNext());
	
		return new RetourService(ReponseEnum.SUC, data);
	}
	
	public RetourService modifierNom(String nom, int apogee, String newNom){
		String key = nom+apogee;
		if(!bdd.exists(key)) return new RetourService(ReponseEnum.NOM_INCONNU,"");

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		p.setNom(newNom);
		key = newNom+apogee;
		
		bdd.insert(key, p);
	
		return new RetourService(ReponseEnum.SUC,p.toString());
	}
	
	public RetourService modifierSurnom(String nom, int apogee, String surnom, String newSurnom){
		String key = nom+apogee;
		if(!bdd.exists(key)) return new RetourService(ReponseEnum.NOM_INCONNU,"");

		
		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		ArrayList<String> surnoms = p.getSurnoms();
		surnoms.remove(surnom);
		surnoms.add(newSurnom);
		p.setSurnoms(surnoms);
		
		bdd.insert(key, p);
		
		return new RetourService(ReponseEnum.SUC,p.toString());
	}
	
	public RetourService modifierApogee(String nom, int apogee, int newApogee){
		String key = nom+apogee;
		if(!bdd.exists(key)) return new RetourService(ReponseEnum.NOM_INCONNU,"");
		if(String.valueOf(newApogee).length()!=8) return new RetourService(ReponseEnum.SYNTAXE_APOGEE,"");

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		p.setApogee(newApogee);
		key = nom+newApogee;
		
		bdd.insert(key, p);
		
		return new RetourService(ReponseEnum.SUC,p.toString());
	}
	
	public RetourService modifierDepartement(String nom, int apogee, String departement){
		String key = nom+apogee;
		if(!bdd.exists(key)) return new RetourService(ReponseEnum.NOM_INCONNU,"");
		if(!departement.matches("SI|MAM|ELEC|ELECII|GE|GB|BAT|PEIP")) return new RetourService(ReponseEnum.DEPARTEMENT_INCONNU,"");

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		p.setDepartement(departement);
		
		bdd.insert(key, p);
		
		return new RetourService(ReponseEnum.SUC,p.toString());
	}
	
	public RetourService modifierQualite(String nom, int apogee, String qualite){
		String key = nom+apogee;
		if(!bdd.exists(key)) return new RetourService(ReponseEnum.NOM_INCONNU,"");
		if(!qualite.matches("PROF|ETUD[1-5]")) return new RetourService(ReponseEnum.SYNTAXE_QUALITE,"");

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		p.setQualite(qualite);
		
		bdd.insert(key, p);
		
		return new RetourService(ReponseEnum.SUC,p.toString());
	}
	
	public RetourService supprimerPersonne(String nom, int apogee){
		String key = nom+apogee;
		if(!bdd.exists(key)) return new RetourService(ReponseEnum.NOM_INCONNU,"");
		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		return new RetourService(ReponseEnum.SUC,p.toString());
	}
	
	public RetourService supprimerSurnom(String nom, int apogee,String surnom){
		String key = nom+apogee;
		if(!bdd.exists(key)) return new RetourService(ReponseEnum.NOM_INCONNU,"");

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		ArrayList<String> surnoms = p.getSurnoms();
		if(!surnoms.contains(surnom)) return new RetourService(ReponseEnum.SURNOM_INCONNU,"");
		
		surnoms.remove(surnom);
		p.setSurnoms(surnoms);
		
		bdd.insert(key, p);
		
		return new RetourService(ReponseEnum.SUC,p.toString());
	}


}