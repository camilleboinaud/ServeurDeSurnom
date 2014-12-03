package server.service;

import com.google.gson.annotations.SerializedName;

import server.Personne;
import server.ReponseEnum;
import server.RetourService;
import server.Services;


public class AjouterNom extends Services {
	
	@SerializedName("nom")
	private String nom;
	
	@SerializedName("apogee")
	private int apogee;
	
	@SerializedName("qualite")
	private String qualite;
	
	@SerializedName("departement")
	private String departement;
	

	public AjouterNom() {
		nom = "";
		apogee = 0;
		qualite = "";
		departement = "";
	}
	
	public RetourService execute(){
		
		Personne p = new Personne(this.nom, this.apogee, this.qualite, this.departement);
		String key = p.getNom()+p.getApogee();
		
		if(bdd.exists(key)) 
			return new RetourService(
					ReponseEnum.NOM_EXISTE_DEJA,
					"AJOUTER_NOM",
					"Erreur : le nom existe dejà",
					""
			);
		
		if(!p.getQualite().matches("PROF|ETUD[1-5]")) 
			return new RetourService(
					ReponseEnum.SYNTAXE_QUALITE,
					"AJOUTER_NOM",
					"Erreur : la syntaxe de la qualite n'est pas correcte",
					""
			);
		
		if(String.valueOf(p.getApogee()).length()!=8) 
			return new RetourService(
					ReponseEnum.SYNTAXE_APOGEE,
					"AJOUTER_NOM",
					"Erreur : la syntaxe du numero apogee n'est pas correcte",
					""
			);
		
		if(!p.getDepartement().matches("SI|MAM|ELEC|ELECII|GE|GB|BAT|PEIP")) 
			return new RetourService(
					ReponseEnum.DEPARTEMENT_INCONNU,
					"AJOUTER_NOM",
					"Erreur : la syntaxe du departement n'est pas correcte",
					""
			);
		
		bdd.insert(key, p);
			
		return new RetourService(
				ReponseEnum.SUCCESS,
				"AJOUTER_NOM",
				"Le nom a bien ete ajoute :",
				gson.toJson(p.toString())
		);
	}

}
