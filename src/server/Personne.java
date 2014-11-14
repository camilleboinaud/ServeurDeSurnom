package server;

import java.util.ArrayList;

public class Personne {
	
	private String nom;
	private int apogee;
	private String genre;
	private String qualite;
	private String departement;
	private ArrayList<String> surnoms;
	
	public Personne(String nom, int apogee, String genre, String qualite, String departement){
		this.nom = nom;
		this.apogee = apogee;
		this.genre = genre;
		this.qualite = qualite;
		this.departement = departement;
		this.surnoms = new ArrayList<String>();
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getApogee() {
		return apogee;
	}

	public void setApogee(int apogee) {
		this.apogee = apogee;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getQualite() {
		return qualite;
	}

	public void setQualite(String qualite) {
		this.qualite = qualite;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public ArrayList<String> getSurnoms() {
		return surnoms;
	}

	public void setSurnoms(ArrayList<String> surnoms) {
		this.surnoms = surnoms;
	}

	public String toString(){
		String result =  nom+"</>"+apogee+"</>"+genre+"</>"+qualite+"</>"+departement+"<:>";
		if(surnoms.size()>0) result.concat(surnoms.get(0));
		if(surnoms.size()>1){
			for(String surnom : surnoms){
				result.concat(","+surnom);
			}
		}
		return result.concat(";");
	}

}
