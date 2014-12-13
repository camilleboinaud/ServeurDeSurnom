package Socket.server.service;
import Socket.server.*;
import com.google.gson.annotations.SerializedName;

public class ModifierDepartement extends Services {

	@SerializedName("nom")
	private String nom;
	
	@SerializedName("apogee")
	private int apogee;
	
	@SerializedName("departement")
	private String departement;
	
	public ModifierDepartement() {
		nom = "";
		apogee = 0;
		departement = "";
	}
	
	public RetourService execute(){
		String key = nom+apogee;
		if(!bdd.exists(key)) 
			return new RetourService(
					ReponseEnum.NOM_INCONNU,
					"MODIFIER_DEPARTEMENT",
					"Erreur : le nom entre est inconnu",
					""
			);
			
		if(!departement.matches("SI|MAM|ELEC|ELECII|GE|GB|BAT|PEIP")) 
			return new RetourService(
					ReponseEnum.DEPARTEMENT_INCONNU,
					"MODIFIER_DEPARTEMENT",
					"Erreur : la syntaxe du departement n'est pas correcte",
					""
			);

		Personne p = bdd.getPersonne(key);
		bdd.deletePersonne(key);
		
		p.setDepartement(departement);
		
		bdd.insert(key, p);
		
		return new RetourService(
				ReponseEnum.SUCCESS, 
				"MODIFIER_DEPARTEMENT",
				"Le departement a bien ete modifie :",
				gson.toJson(p.toString())
		);
	}

}
