package Socket.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConstructionRequete {
	
	private Map<String,String> parametres = null;
	private String service = "";

	
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
			System.out.println("Saisissez le numero Apogee de la personne : ");
			answer = sc.nextLine();
		} while(answer.length() != 8);
	
		return answer;
	}
	
	private String askQualite(){
		String answer = "";
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("Saisissez le statut de la personne (ETUD pour un etudiant, PROF pour un professeur) : ");
			answer = sc.nextLine();
		} while(!answer.matches("ETUD|PROF"));
		if(answer.equals("ETUD")){
			String annee = "";
			do{
				System.out.println("Saisissez le numero d'annee de la personne (1 à 5) : ");
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
			System.out.println("Saisissez le departement où travaille la personne : ");
			answer = sc.nextLine();
		} while(!answer.matches("SI|MAM|ELEC|GE|BAT|GB|ELECII|PEIP"));
	
		return answer;
	}
	
	
	private Map<String,String> menuService(){
		Map<String,String> s = new HashMap<String,String>();
		String choice = "";
		Scanner scInt = new Scanner(System.in);
		do{
		System.out.println("Entrez le numero du service que vous souhaitez effectuer : ");
		System.out.println("\t1.  AjouterNom\t\t2.  AjouterSurnom\t\t3.  ListerRequete\t\t4.  ListerTout\n" +
				"\t5.  ListerUn\t\t6.  ModifierDepartement\t\t7.  ModifierNom\t\t\t8.  ModifierQualite\n"+
				"\t9. ModifierSurnom\t10. SupprimerNom\t\t11. SupprimerSurnom\t\t12. Deconnection");
		choice = scInt.nextLine();
		} while(!choice.matches("1|2|3|4|5|6|7|8|9|10|11|12|13"));
		
		switch(Integer.parseInt(choice)){
		case 1 :
			service = ServiceN.AjouterNom.toString();
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			s.put("qualite",this.askQualite());
			s.put("departement",this.askDepartement());
			break;
		case 2 :
			service = ServiceN.AjouterSurnom.toString();
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			s.put("surnom",this.askSurnom());
			break;
		case 3 :
			service = ServiceN.ListerRequete.toString();
			s.put("filtre",this.askFiltreRecherche());
			break;
		case 4 :
			service = ServiceN.ListerTout.toString();
			break;
		case 5 :
			service = ServiceN.ListerUn.toString();
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			break;
		case 6 :
			service = ServiceN.ModifierDepartement.toString();
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			s.put("departement",this.askDepartement());
			break;
		case 7 :
			service = ServiceN.ModifierNom.toString();
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			s.put("nouveauNom",this.askNouveauNom());
			break;
		case 8 :
			service = ServiceN.ModifierQualite.toString();
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			s.put("qualite",this.askQualite());
			break;
		case 9 :
			service = ServiceN.ModifierSurnom.toString();
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			s.put("surnom",this.askSurnom());
			s.put("nouveauSurnom",this.askNouveauSurnom());
			break;
		case 10 :
			service = ServiceN.SupprimerNom.toString();
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			break;
		case 11 :
			service = ServiceN.SupprimerSurnom.toString();
			s.put("nom",this.askNom());
			s.put("apogee",this.askApogee());
			s.put("surnom",this.askSurnom());
			break;
		case 12 :
			service = ServiceN.Deconnection.toString();
			break;
		}
		
	
		return s;
	}
	
	public String getService(){
		return this.service;
	}
	
	public Map<String,String> getParametres(){
		return this.parametres;
	}
	

}
