package RMI.server.service;
import RMI.server.*;
import com.google.gson.annotations.SerializedName;

public class ModifierQualite extends Services {
	
	@SerializedName("nom")
	private String nom;
	
	@SerializedName("apogee")
	private int apogee;
	
	@SerializedName("qualite")
	private String qualite;

	public ModifierQualite() {
		nom = "";
		apogee = 0;
		qualite = "";
	}
	
	public RetourService execute(){
		String key = nom+apogee;
		
		if(!bdd.exists(key))
			return new RetourService(
					ReponseEnum.NOM_INCONNU,
					"MODIFIER_QUALITE",
					"Erreur : le nom entre est inconnu",
					""
			);
		
		if(!qualite.matches("PROF|ETUD[1-5]")) 
			return new RetourService(
					ReponseEnum.SYNTAXE_QUALITE,
					"MODIFIER_QUALITE",
					"Erreur : la syntaxe de la qualite n'est pas correcte",
					""
			);

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		p.setQualite(qualite);
		
		bdd.insert(key, p);
		
		return new RetourService(
				ReponseEnum.SUCCESS, 
				"MODIFIER_QUALITE",
				"La qualite a bien ete modifie :",
				gson.toJson(p.toString())
		);
	}

}
