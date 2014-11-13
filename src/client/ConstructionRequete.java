package client;

import java.util.ArrayList;
import java.util.Scanner;

public class ConstructionRequete {
	
	public static final String SEP = "<#>";
	public static final String END = "<#end>";

	
	private Scanner sc = null;
	private Scanner scInt = null;

	private ServiceN service = null;
	private ArrayList<String> params = null;
	public String requete = "";

	
	public ConstructionRequete(){
		sc = new Scanner(System.in);
		scInt = new Scanner(System.in);

		service = menuService();
		params = menuParametre();
	}
	
	
	private ArrayList<String> menuParametre(){
		ArrayList<String> result = null;

		switch(service){
		case AJOUTER_NOM :
			result = ask(true,false, true, false, true, true, true,false,false);
			break;
		case AJOUTER_SURNOM :
			result = ask(true,false, true, false, false, false, false,true,false);
			break;
		case LISTER_REQUETE :
			result = ask(false,false, false, false, false, false, false,false,true);
			break;
		case LISTER_UN:
			result = ask(true,false, true, false, false, false, false,false,false);
			break;
		case MODIFIER_APOGEE:
			result = ask(true,false, true, true, false, false, false,false,false);
			break;
		case MODIFIER_DEPARTEMENT:
			result = ask(true,false, true, false, false, false, true,false,false);
			break;
		case MODIFIER_NOM:
			result = ask(true,true, true, false, false, false, false,false,false);
			break;
		case MODIFIER_QUALITE:
			result = ask(true,false, true, false, false, true, false,false,false);
			break;
		case MODIFIER_SURNOM:
			result = ask(true,false, true, false, false, false, false,true,false);
			break;
		case SUPPRIMER_NOM:
			result = ask(true,false, true, false, false, false, false,false,false);
			break;
		case SUPPRIMER_SURNOM:
			result = ask(true,false, true, false, false, false, false,true,false);
			break;
		default : 
			break;
		}
		
		
		return result;
		
	}
	
	private ArrayList<String> ask(
			boolean nom, boolean newNom,
			boolean apogee, boolean newApogee,
			boolean genre, 
			boolean qualite, 
			boolean departement,  
			boolean surnom,
			boolean filtre){
		
		ArrayList<String> result = new ArrayList<String>();
				
		if(nom){
			result.add(askNom("NOM"));
		}
		
		if(apogee){
			result.add(askApogee());
		}
		
		if(genre){
			result.add(askGenre());
		}
		
		if(qualite){
			result.add(askQualite());
		}
		
		if(departement){
			result.add(askDepartement());
		}
		
		if(surnom){
			result.add(askNom("SURNOM"));
		}
		
		if(newNom){
			result.add(askNom("NOM"));
		}
		
		if(newApogee){
			result.add(askApogee());
		}
				
		if(filtre){
			result.add(askNom("FILTRE"));
		}
				
		return result;
	}
	
	private String askNom(String type){
		String answer = "";
		do{
			if(type.equals("NOM")) System.out.println("Saisissez le nom de la personne : ");
			else if(type.equals("SURNOM")) System.out.println("Saisissez un surnom de la personne : ");
			else System.out.println("Saisissez un filtre de recherche : ");
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
	
	private String askGenre(){
		String answer = "";
		
		do{
			System.out.println("Saisissez le genre de la personne (M ou F) : ");
			answer = sc.nextLine();
		} while(!answer.equals("M") && !answer.equals("F"));
	
		return answer;
	}
	
	private String askQualite(){
		String answer = "";
		
		do{
			System.out.println("Saisissez le statut de la personne (ETUD pour un étudiant, PROF pour un professeur) : ");
			answer = sc.nextLine();
		} while(!answer.equals("ETUD") && !answer.equals("PROF"));
		if(answer.equals("ETUD")){
			String annee = "";
			do{
				System.out.println("Saisissez le numéro d'année de la personne (1 à 5) : ");
				annee = sc.nextLine();
			} while(!annee.equals("1") && !annee.equals("2")&& !annee.equals("3") && !annee.equals("4") && !annee.equals("5"));
			answer = answer.concat(annee);
		}
	
		return answer;
	}
	
	private String askDepartement(){
		String answer = "";
		
		do{
			System.out.println("Saisissez le département où travaille la personne : ");
			answer = sc.nextLine();
		} while(!answer.equals("SI") 
				&& !answer.equals("MAM")
				&& !answer.equals("ELEC")
				&& !answer.equals("GE")
				&& !answer.equals("BAT")
				&& !answer.equals("GB")
				&& !answer.equals("ELECII")
				&& !answer.equals("PEIP"));
	
		return answer;
	}
	
	
	private ServiceN menuService(){
		ServiceN s = null;
		int choice = 0;
		do{
		System.out.println("Entrez le numéro du service que vous souhaitez effectuer : ");
		System.out.println("\t1.  "+ServiceN.AJOUTER_NOM+"\t\t2.  "+ServiceN.AJOUTER_SURNOM+"\t3.  "+ServiceN.LISTER_REQUETE+"\t\t4.  "+ServiceN.LISTER_TOUT+"\n" +
				"\t5.  "+ServiceN.LISTER_UN+"\t\t6.  "+ServiceN.MODIFIER_APOGEE+"\t7.  "+ServiceN.MODIFIER_DEPARTEMENT+"\t8.  "+ServiceN.MODIFIER_NOM+"\n"+
				"\t9.  "+ServiceN.MODIFIER_QUALITE+"\t10. "+ServiceN.MODIFIER_SURNOM+"\t11. "+ServiceN.SUPPRIMER_NOM+"\t\t12. "+ServiceN.SUPPRIMER_SURNOM);
		choice = scInt.nextInt();
		} while(choice!= 1 && choice!= 2 && choice!= 3 && choice!= 4 && choice!= 5 && choice!= 6
				&& choice!= 7 && choice!= 8 && choice!= 9 && choice!= 10 && choice!= 11 && choice!= 12);
		
		switch(choice){
		case 1 :
			s = ServiceN.AJOUTER_NOM;
			break;
		case 2 :
			s = ServiceN.AJOUTER_SURNOM;
			break;
		case 3 :
			s = ServiceN.LISTER_REQUETE;
			break;
		case 4 :
			s = ServiceN.LISTER_TOUT;
			break;
		case 5 :
			s = ServiceN.LISTER_UN;
			break;
		case 6 :
			s = ServiceN.MODIFIER_APOGEE;
			break;
		case 7 :
			s = ServiceN.MODIFIER_DEPARTEMENT;
			break;
		case 8 :
			s = ServiceN.MODIFIER_NOM;
			break;
		case 9 :
			s = ServiceN.MODIFIER_QUALITE;
			break;
		case 10 :
			s = ServiceN.MODIFIER_SURNOM;
			break;
		case 11 :
			s = ServiceN.SUPPRIMER_NOM;
			break;
		case 12 :
			s = ServiceN.SUPPRIMER_SURNOM;
			break;
		}
		
		return s;
	}
	
	@Override
	public String toString(){
		String result = "";
		
		result = "REQ"+SEP+service.toString();
		if(params!=null){
			for(String s : params){
				if(!s.equals("")) result = result+SEP+s;
			}
		}
		return result+END+"\n";
	}
}
