package server;

import java.util.ArrayList;

public class Personne {
	
	private String nom;
	private int apogee;
	private String qualite;
	private String departement;
	private ArrayList<String> surnoms;
	
	public Personne(String nom, int apogee, String qualite, String departement){
		this.nom = nom;
		this.apogee = apogee;
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

}
