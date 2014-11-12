package client;

import java.util.ArrayList;

public class TraitementReponse {
	
	private ArrayList<String> reponse;
	
	public TraitementReponse(ArrayList<String> reponse){
		this.reponse = reponse;
	}
	
	public boolean affichage(){
		
		boolean result = true;
		
		for(String s : reponse){
			String[] tab = s.split("<#>|<#end>");
			String[] typeRep = tab[0].split("-");
			
			if(typeRep[0].equals("ERR")){
				System.err.println("\nERREUR : \n\t" + typeRep[1]);
				switch(typeRep[1]){
				case "ERREUR_FIN_REQUETE":
					System.err.println("\t-> Le serveur n'a pas pu détecter la fin de la requête.");
					break;
				case "SYNTAXE_APOGEE":
					System.err.println("\t-> Le numéro apogée saisie ne respecte pas le format correct. \n\t   (8 chiffres, ex : 21208556)");
					break;
				case "NOM_EXISTE_DEJA":
					System.err.println("\t-> La personne que vous avez essayé d'entrer existe déjà");
					break;
				case "SYNTAXE_QUALITE":
					System.err.println("\t-> La catégorie de la personne (PROF ou ETUD) ou son année \n\t   (Chiffre entre 1 et 5 si c'est un étudiant) ne respecte pas le format correct.");
					break;
				case "DEPARTEMENT_INCONNU":
					System.err.println("\t-> Le nom du département entré est inconnu. Seul les départements : \n\t   SI, MAM, ELEC, GE, BAT, GB, ELECII ou PEIP sont reconnus à Polytech");
					break;
				case "NOM_INCONNU":
					System.err.println("\t-> Le nom saisi n'est pas présent dans le serveur de surnoms. \n\t   Il faut commencer par l'ajouter ou s'assurer qu'il n'y a pas de faute.");
					break;
				case "SURNOM_INCONNU":
					System.err.println("\t-> Le surnom saisi n'est pas présent dans le serveur de surnoms. \n\t   Il faut commencer par l'ajouter ou s'assurer qu'il n'y a pas de faute.");
					break;
				case "FILTRE_INCONNU":
					System.err.println("\t-> Le filtre saisi n'a aucune occurence dans le serveur de surnoms. \n\t   Choisissez un autre filtre.");
					break;
				}
				result = false;			
			} else if(result){
				switch(typeRep[1]){
				case "AJOUTER_NOM" :
					break;
				case "AJOUTER_SURNOM" :
					break;
				case "LISTER_REQUETE" :
					break;
				case "LISTER_UN":
					break;
				case "MODIFIER_APOGEE":
					break;
				case "MODIFIER_DEPARTEMENT":
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
		}
		System.out.println();
		return result;
	}

}
