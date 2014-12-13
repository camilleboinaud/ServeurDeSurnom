package RMI.server.service;
import RMI.server.*;
import com.google.gson.annotations.SerializedName;

public class ModifierNom extends Services {
	
	@SerializedName("nom")
	private String nom;
	
	@SerializedName("apogee")
	private int apogee;
	
	@SerializedName("nouveauNom")
	private String nouveauNom;

	public ModifierNom() {
		nom = "";
		apogee = 0;
		nouveauNom = "";
	}
	
	public RetourService execute(){
		String key = nom+apogee;
		if(!bdd.exists(key)) 
			return new RetourService(
					ReponseEnum.NOM_INCONNU,
					"MODIFIER_NOM",
					"Erreur : le nom entre est inconnu",
					""
			);

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		p.setNom(nouveauNom);
		key = nouveauNom+apogee;
		
		bdd.insert(key, p);
	
		return new RetourService(
				ReponseEnum.SUCCESS, 
				"MODIFIER_NOM",
				"Le nom a bien ete modifie :",
				gson.toJson(p.toString())
		);
	}

}
