package server;

import java.io.IOException;

import client.ServiceN;

public class ServeurCom {
	public static String	MARQUEUR_DE_FIN	= "<end>";
	Serveur					s				= null;
	ServiceN				serviceDemande	= null;
	ReponseEnum			reponseToSend		= null;

	public ServeurCom(int port) {
		s = new Serveur(port);
	}

	public void execute() {
		String lecture = s.getMessageFromClient();
		// lecture+=s.getMessageFromClient();
		if ((lecture.indexOf(MARQUEUR_DE_FIN) != (lecture.length() - MARQUEUR_DE_FIN
				.length()))) {
			s.send("ERR-ERREUR_FIN_REQUETE<#end>\n");
		} else {
			this.decrypt(lecture);
		}

	}

	private void decrypt(String msg) {
		/*
		 * System.out.println("reçu : " + msg); s.send(msg);
		 */
		this.traitementRequete(msg);

	}

	private void traitementRequete(String msg) {
		String params[] = msg.split("<#>");
		if(params[0] == "REQ"){
			//Nous sommes dans une requete
			switch(params[1]){
			case "AJOUTER_NOM" :
				ajouterNom(params[2], Integer.parseInt(params[3]), params[4].charAt(0), params[5], Integer.parseInt(params[6]), params[7]);
				break;
			case "AJOUTER_SURNOM" :
				ajouterSurnom(params[2], Integer.parseInt(params[3]), params[4]);
				break;
			case "LISTER_REQUETE" :
				listerRequete(params[2]);
				break;
			case "LISTER_TOUT" :
				listerTout();
				break;
			case "LISTER_UN":
				listerUn(params[2], Integer.parseInt(params[3]));
				break;
			case "MODIFIER_APOGEE":
				modifierApogee(params[2],Integer.parseInt(params[3]),Integer.parseInt(params[4]));
				break;
			case "MODIFIER_DEPARTEMENT":
				modifierApogee(params[2],Integer.parseInt(params[3]),String departement);
				break;
			case "MODIFIER_NOM":
				break;
			case "MODIFIER_QUALITE":
				break;
			case "MODIFIER_SURNOM":
				break;
			case "SUPPRIMER_NOM":
				break;
			case "SUPPRIMER_SURNOM":
				break;
			default : 
				break;
			}
			
			}
		// TODO en cours traitement requete
		}
		
	
	private void listerUn(String nom, int numeroApoge) {
		
	
	}
	private void ajouterNom(String nom, int numeroApoge, char genre, String statut, int annee, String departement) {
		
		
	}
	private void ajouterSurnom(String nom, int numeroApoge, String surnom) {
		
		
	}
	private void listerRequete(String filtre) {
		
		
	}
	private void listerTout() {
		
		
	}
	private void modifierApogee(String nom, int apogee, int nouveauApogee){
		
	}
	private void modifierApogee(String nom,int apogee,String departement){
		
	}
	public void disconnect() throws IOException {
		s.disconnect();
	}

	public static void main(String args[]) {
		ServeurCom sc = new ServeurCom(6565);
		try {
			sc.execute();
			sc.disconnect();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
