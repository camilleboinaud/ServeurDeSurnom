package client;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConstructionRequete {
	
	private Map<String,String> parametres = null;
	private ServiceN service = null;

	
	public ConstructionRequete(){
		parametres = menuService();
	}

	
	private String askNom(){
		String answer = "";
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("Saisissez le nom de la personne : ");
			answer = sc.nextLine();
		} while(answer.equals(""));
		return answer;
	}
	
	private String askNouveauNom(){
		String answer = "";
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("Saisissez le nouveau nom de la personne : ");
			answer = sc.nextLine();
		} while(answer.equals(""));
		return answer;
	}
	
	private String askSurnom(){
		Scanner sc = new Scanner(System.in);
		String answer = "";
		do{
			System.out.println("Saisissez un surnom de la personne : ");
			answer = sc.nextLine();
		} while(answer.equals(""));
		return answer;
	}
	
	private String askNouveauSurnom(){
		Scanner sc = new Scanner(System.in);
		String answer = "";
		do{
			System.out.println("Saisissez le nouveau surnom de la personne : ");
			answer = sc.nextLine();
		} while(answer.equals(""));
		return answer;
	}
	
	private String askFiltreRecherche(){
		Scanner sc = new Scanner(System.in);
		String answer = "";
		do{
			System.out.println("Saisissez un filtre de recherche : ");
			answer = sc.nextLine();
		} while(answer.equals(""));
		return answer;
	}
	
	private String askApogee(){
		String answer = "";
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("Saisissez le numéro Apogée de la personne : ");
			answer = sc.nextLine();
		} while(answer.length() != 8);
	
		return answer;
	}
	
	private String askQualite(){
		String answer = "";
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("Saisissez le statut de la personne (ETUD pour un étudiant, PROF pour un professeur) : ");
			answer = sc.nextLine();
		} while(!answer.matches("ETUD|PROF"));
		if(answer.equals("ETUD")){
			String annee = "";
			do{
				System.out.println("Saisissez le numéro d'année de la personne (1 à 5) : ");
				annee = sc.nextLine();
			} while(!annee.matches("1|2|3|4|5"));
			answer = answer.concat(annee);
		}
	
		return answer;
	}
	
	private String askDepartement(){
		String answer = "";
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("Saisissez le département où travaille la personne : ");
			answer = sc.nextLine();
		} while(!answer.matches("SI|MAM|ELEC|GE|BAT|GB|ELECII|PEIP"));
	
		return answer;
	}
	
	
	private Map<String,String> menuService(){
		Map<String,String> s = new HashMap<String,String>();
		String choice = "";
		Scanner scInt = new Scanner(System.in);
		do{
		System.out.println("Entrez le numéro du service que vous souhaitez effectuer : ");
		System.out.println("\t1.  "+ServiceN.AJOUTER_NOM+"\t\t2.  "+ServiceN.AJOUTER_SURNOM+"\t\t3.  "+ServiceN.LISTER_REQUETE+"\t\t4.  "+ServiceN.LISTER_TOUT+"\n" +
				"\t5.  "+ServiceN.LISTER_UN+"\t\t6.  "+ServiceN.MODIFIER_DEPARTEMENT+"\t7.  "+ServiceN.MODIFIER_NOM+"\t\t8.  "+ServiceN.MODIFIER_QUALITE+"\n"+
				"\t9. "+ServiceN.MODIFIER_SURNOM+"\t10. "+ServiceN.SUPPRIMER_NOM+"\t\t11. "+ServiceN.SUPPRIMER_SURNOM+"\t\t12. "+ServiceN.DECONNECTION);
		choice = scInt.nextLine();
		} while(!choice.matches("1|2|3|4|5|6|7|8|9|10|11|12|13"));
		
		switch(Integer.parseInt(choice)){
		case 1 :
			service = ServiceN.AJOUTER_NOM;
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			s.put("qualite",this.askQualite());
			s.put("departement",this.askDepartement());
			break;
		case 2 :
			service = ServiceN.AJOUTER_SURNOM;
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			s.put("surnom",this.askSurnom());
			break;
		case 3 :
			service = ServiceN.LISTER_REQUETE;
			s.put("filtre",this.askFiltreRecherche());
			break;
		case 4 :
			service = ServiceN.LISTER_TOUT;
			break;
		case 5 :
			service = ServiceN.LISTER_UN;
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			break;
		case 6 :
			service = ServiceN.MODIFIER_DEPARTEMENT;
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			s.put("departement",this.askDepartement());
			break;
		case 7 :
			service = ServiceN.MODIFIER_NOM;
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			s.put("nouveauNom",this.askNouveauNom());
			break;
		case 8 :
			service = ServiceN.MODIFIER_QUALITE;
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			s.put("qualite",this.askQualite());
			break;
		case 9 :
			service = ServiceN.MODIFIER_SURNOM;
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			s.put("surnom",this.askSurnom());
			s.put("nouveauSurnom",this.askNouveauSurnom());
			break;
		case 10 :
			service = ServiceN.SUPPRIMER_NOM;
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			break;
		case 11 :
			service = ServiceN.SUPPRIMER_SURNOM;
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			s.put("surnom",this.askSurnom());
			break;
		case 12 :
			service = ServiceN.DECONNECTION;
			break;
		}
		
	
		return s;
	}
	
	public ServiceN getService(){
		return this.service;
	}
	
	public Map<String,String> getParametres(){
		return this.parametres;
	}
	

}
