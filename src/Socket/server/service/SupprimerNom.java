package Socket.server.service;
import Socket.server.*;
import com.google.gson.annotations.SerializedName;

public class SupprimerNom extends Services{
	
	@SerializedName("nom")
	private String nom;
	
	@SerializedName("apogee")
	private int apogee;

	public SupprimerNom() {
		nom = "";
		apogee = 0;
	}
	
	public RetourService execute(){
		String key = nom+apogee;
		
		if(!bdd.exists(key))
			return new RetourService(
					ReponseEnum.NOM_INCONNU,
					"SUPPRIMER_NOM",
					"Erreur : le nom entre est inconnu",
					""
			);
			
		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		return new RetourService(
				ReponseEnum.SUCCESS, 
				"SUPPRIMER_NOM",
				"La personne a bien ete supprimee :",
				gson.toJson(p.toString())
		);
	}

}
