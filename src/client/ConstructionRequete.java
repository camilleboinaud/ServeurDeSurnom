package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConstructionRequete {
	
	public static final String SEP = "<#>";
	public static final String END = "<#end>";

	
	private Scanner sc = null;
	private Scanner scInt = null;

	private List<String> parametres = null;
	private String service = "";

	
	public ConstructionRequete(){
		sc = new Scanner(System.in);
		scInt = new Scanner(System.in);
		parametres = menuService();
	}

	
	private String askNom(){
		String answer = "";
		do{
			System.out.println("Saisissez le nom de la personne : ");
			answer = sc.nextLine();
		} while(answer.equals(""));
		return answer;
	}
	
	private String askNouveauNom(){
		String answer = "";
		do{
			System.out.println("Saisissez le nouveau nom de la personne : ");
			answer = sc.nextLine();
		} while(answer.equals(""));
		return answer;
	}
	
	private String askSurnom(){
		String answer = "";
		do{
			System.out.println("Saisissez un surnom de la personne : ");
			answer = sc.nextLine();
		} while(answer.equals(""));
		return answer;
	}
	
	private String askNouveauSurnom(){
		String answer = "";
		do{
			System.out.println("Saisissez le nouveau surnom de la personne : ");
			answer = sc.nextLine();
		} while(answer.equals(""));
		return answer;
	}
	
	private String askFiltreRecherche(){
		String answer = "";
		do{
			System.out.println("Saisissez un filtre de recherche : ");
			answer = sc.nextLine();
		} while(answer.equals(""));
		return answer;
	}
	
	private String askApogee(){
		String answer = "";
		
		do{
			System.out.println("Saisissez le numéro Apogée de la personne : ");
			answer = sc.nextLine();
		} while(answer.length() != 8);
	
		return answer;
	}
	
	private String askQualite(){
		String answer = "";
		
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
		
		do{
			System.out.println("Saisissez le département où travaille la personne : ");
			answer = sc.nextLine();
		} while(!answer.matches("SI|MAM|ELEC|GE|BAT|GB|ELECII|PEIP"));
	
		return answer;
	}
	
	
	private List<String> menuService(){
		List<String> s = new ArrayList<String>();
		String choice = "";
		do{
		System.out.println("Entrez le numéro du service que vous souhaitez effectuer : ");
		System.out.println("\t1.  "+ServiceN.AJOUTER_NOM+"\t\t2.  "+ServiceN.AJOUTER_SURNOM+"\t\t3.  "+ServiceN.LISTER_REQUETE+"\t\t4.  "+ServiceN.LISTER_TOUT+"\n" +
				"\t5.  "+ServiceN.LISTER_UN+"\t\t6.  "+ServiceN.MODIFIER_DEPARTEMENT+"\t7.  "+ServiceN.MODIFIER_NOM+"\t\t8.  "+ServiceN.MODIFIER_QUALITE+"\n"+
				"\t9. "+ServiceN.MODIFIER_SURNOM+"\t10. "+ServiceN.SUPPRIMER_NOM+"\t\t11. "+ServiceN.SUPPRIMER_SURNOM+"\t\t12. "+ServiceN.DECONNECTION);
		choice = scInt.nextLine();
		} while(!choice.matches("1|2|3|4|5|6|7|8|9|10|11|12|13"));
		
		switch(Integer.parseInt(choice)){
		case 1 :
			service = ServiceN.AJOUTER_NOM.toString();
			s.add(this.askNom());
			s.add(this.askApogee());
			s.add(this.askQualite());
			s.add(this.askDepartement());
			break;
		case 2 :
			service = ServiceN.AJOUTER_SURNOM.toString();
			s.add(this.askNom());
			s.add(this.askApogee());
			s.add(this.askSurnom());
			break;
		case 3 :
			service = ServiceN.LISTER_REQUETE.toString();
			s.add(this.askFiltreRecherche());
			break;
		case 4 :
			service = ServiceN.LISTER_TOUT.toString();
			break;
		case 5 :
			service = ServiceN.LISTER_UN.toString();
			s.add(this.askNom());
			s.add(this.askApogee());
			break;
		case 6 :
			service = ServiceN.MODIFIER_DEPARTEMENT.toString();
			s.add(this.askNom());
			s.add(this.askApogee());
			s.add(this.askDepartement());
			break;
		case 7 :
			service = ServiceN.MODIFIER_NOM.toString();
			s.add(this.askNom());
			s.add(this.askApogee());
			s.add(this.askNouveauNom());
			break;
		case 8 :
			service = ServiceN.MODIFIER_QUALITE.toString();
			s.add(this.askNom());
			s.add(this.askApogee());
			s.add(this.askQualite());
			break;
		case 9 :
			service = ServiceN.MODIFIER_SURNOM.toString();
			s.add(this.askNom());
			s.add(this.askApogee());
			s.add(this.askSurnom());
			s.add(this.askNouveauSurnom());
			break;
		case 10 :
			service = ServiceN.SUPPRIMER_NOM.toString();
			s.add(this.askNom());
			s.add(this.askApogee());
			break;
		case 11 :
			service = ServiceN.SUPPRIMER_SURNOM.toString();
			s.add(this.askNom());
			s.add(this.askApogee());
			s.add(this.askSurnom());
			break;
		case 12 :
			service = ServiceN.DECONNECTION.toString();
			break;
		}
		
		return s;
	}
	
	public String getService(){
		return this.service;
	}
	
	public List<String> getParametres(){
		return this.parametres;
	}
	

}
