package Socket.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Donnees {
	
	private HashMap<String,Personne> donnees;
	
	public Donnees(){
		donnees = new HashMap<String,Personne>();
		this.init();		
	}
	
	public void init(){
		Personne p1 = new Personne("Monsieur X", 21200000, "PROF", "SI");
		donnees.put(p1.getNom()+p1.getApogee(), p1);
		Personne p2 = new Personne("Madame Y", 20200000, "PROF", "BAT");
		donnees.put(p2.getNom()+p2.getApogee(), p2);
		Personne p3 = new Personne("Paul", 21201111, "ETUD3", "MAM");
		ArrayList<String> s3 = p3.getSurnoms();
		s3.add("Paupol");
		p3.setSurnoms(s3);
		donnees.put(p3.getNom()+p3.getApogee(), p3);
		Personne p4 = new Personne("Paulette", 21202222, "ETUD5", "GE");
		donnees.put(p4.getNom()+p4.getApogee(), p4);
		Personne p5 = new Personne("Jean", 21203333, "ETUD1", "PEIP");
		donnees.put(p5.getNom()+p5.getApogee(), p5);
	}
	
	public void insert(String key, Personne p){
		donnees.put(key, p);
	}
	
	public boolean exists(String key){
		return donnees.containsKey(key);
	}
	
	public Personne getPersonne(String key){
		return donnees.get(key);
	}
	
	public void deletePersonne(String key){
		donnees.remove(key);
	}
	
	public Iterator<String> getIterator(){
		return donnees.keySet().iterator();
	}
	
}
