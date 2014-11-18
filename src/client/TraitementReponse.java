package client;

import java.util.ArrayList;

public class TraitementReponse {
	
	private ArrayList<String> reponse;
	
	public TraitementReponse(ArrayList<String> reponse){
		this.reponse = reponse;
	}
	
	public void affichage(){
		boolean result = true;
		
		for(String s : reponse){
			String[] tab = s.split("<#>|<#end>");
			String[] typeRep = tab[0].split("-");
			
			if(typeRep[0].equals("ERR")){
				System.err.println("\nERREUR : \n\t" + typeRep[1]);
				switch(server.ReponseEnum.valueOf(typeRep[1])){
				case ERREUR_FIN_REQUETE:
					System.err.println("\t-> Le serveur n'a pas pu détecter la fin de la requête.");
					break;
				case SYNTAXE_APOGEE:
					System.err.println("\t-> Le numéro apogée saisie ne respecte pas le format correct. \n\t   (8 chiffres, ex : 21208556)");
					break;
				case NOM_EXISTE_DEJA:
					System.err.println("\t-> La personne que vous avez essayé d'entrer existe déjà");
					break;
				case SYNTAXE_QUALITE:
					System.err.println("\t-> La catégorie de la personne (PROF ou ETUD) ou son année \n\t   (Chiffre entre 1 et 5 si c'est un étudiant) ne respecte pas le format correct.");
					break;
				case DEPARTEMENT_INCONNU:
					System.err.println("\t-> Le nom du département entré est inconnu. Seul les départements : \n\t   SI, MAM, ELEC, GE, BAT, GB, ELECII ou PEIP sont reconnus à Polytech");
					break;
				case NOM_INCONNU:
					System.err.println("\t-> Le nom saisi n'est pas présent dans le serveur de surnoms. \n\t   Il faut commencer par l'ajouter ou s'assurer qu'il n'y a pas de faute.");
					break;
				case SURNOM_INCONNU:
					System.err.println("\t-> Le surnom saisi n'est pas présent dans le serveur de surnoms. \n\t   Il faut commencer par l'ajouter ou s'assurer qu'il n'y a pas de faute.");
					break;
				case FILTRE_INCONNU:
					System.err.println("\t-> Le filtre saisi n'a aucune occurence dans le serveur de surnoms. \n\t   Choisissez un autre filtre.");
					break;
				default: 
					System.err.println("\t-> Le service demandé n'est pas connu par le serveur.");
				}
				result = false;			
			} else if(result){
				switch(ServiceN.valueOf(typeRep[1])){
				case AJOUTER_NOM:
					afficherAjouterNom(tab);
					break;
				case AJOUTER_SURNOM:
					afficherAjouterSurnom(tab);
					break;
				case LISTER_REQUETE :
					afficherListerRequete(tab);
					break;
				case LISTER_UN:
					afficherPersonne(tab);
					break;
				case LISTER_TOUT:
					afficherPersonne(tab);
					break;
				case MODIFIER_APOGEE:
					afficherPersonne(tab);
					break;
				case MODIFIER_DEPARTEMENT:
					afficherPersonne(tab);
					break;
				case MODIFIER_NOM:
					afficherPersonne(tab);
					break;
				case MODIFIER_QUALITE:
					afficherPersonne(tab);
					break;
				case MODIFIER_SURNOM:
					afficherPersonne(tab);
					break;
				case SUPPRIMER_NOM:
					afficherSupprimerNom(tab);
					break;
				case SUPPRIMER_SURNOM:
					afficherSupprimerSurnom(tab);
					break;
				}
			} 
		}
	}
	
	private void afficherAjouterNom(String[] arg){
		System.out.println("Le nom à bien été ajouté : ");
		listerUnElement(arg[1]);
	}
	
	private void afficherAjouterSurnom(String[] arg){
		System.out.println("Le surnom à bien été ajouté : ");
		listerUnElement(arg[1]);
	}
	
	private void afficherListerRequete(String[] arg){
		System.out.println("Données concernant les personnes filtrées : ");
		listerPlusieursElements(arg[1]);
	}
	
	private void afficherPersonne(String[] arg){
		System.out.println("Données concernant toutes les personnes : ");
		if(arg.length>1) listerPlusieursElements(arg[1]);
		else System.out.println("\t--> AUCUNE DONNEE");
	}
	
	
	private void afficherSupprimerNom(String[] arg){
		System.out.println("Les données suivantes ont bien été supprimées :");
		listerUnElement(arg[1]);
	}
	
	private void afficherSupprimerSurnom(String[] arg){
		System.out.println("Le surnom à bien été supprimé. Voici les données conservées : ");
		listerUnElement(arg[1]);
	}
	
	private void listerPlusieursElements(String elements){
		String[] element = elements.split("<;>");
		
		for(int i = 0 ; i < element.length ; i++){
			listerUnElement(element[i]);
		}
	}
	
	private void listerUnElement(String elements){
		String[] element = elements.split("<;>");
		String[] enDeux = element[0].split("<:>");
		String[] infos = enDeux[0].split("</>");
		String[] surnoms;
		try{
			surnoms = enDeux[1].split("<,>");
		}catch(ArrayIndexOutOfBoundsException e){
			surnoms = null;
		}
		System.out.println("\n\t--> ["+infos[1]+"] "+infos[0]+" ("+affichageGenre(infos[2])+" - "+affichageQualite(infos[3])+" - "+affichageDepartement(infos[4])+") :");
		if(surnoms!=null){
			for(int i = 0; i < surnoms.length ; i++){
				System.out.println("\t\t- "+surnoms[i]);
			}
		}
	}
	
	private String affichageGenre(String arg){
		if(arg.equals("M")) return "Homme";
		return "Femme";
	}
	
	private String affichageQualite(String arg){
		if(arg.indexOf("ETUD")!=-1){
			if(arg.substring(4)=="1")
				return "Etudiant 1ère Année";
			else
				return "Etudiant "+arg.substring(4)+"ème Année";
		}
		return "Professeur";	
	}
	
	private String affichageDepartement(String arg){
		String result = "";
		if(arg.equals("PEIP"))
			result = "PEIP";
		else
			result = "Département "+arg;
		return result;
	}
	

}
