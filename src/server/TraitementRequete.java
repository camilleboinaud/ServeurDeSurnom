package server;


import com.google.gson.*;
import client.ServiceN;

public class TraitementRequete {
	public static String	MARQUEUR_DE_FIN	= "<#end>";
	private ServiceN		serviceName	= null;
	private Services		services;
	private GsonBuilder builder;
    private Gson gson;

	public TraitementRequete() {
		builder = new GsonBuilder();
		gson = builder.setPrettyPrinting().create();
		services = new Services();
	}

	public String execute(String lecture){
		RetourService reponse = this.traitementRequete(lecture);
		return gson.toJson(reponse);
	}

	private RetourService traitementRequete(String msg) {
		//Conversion du json en objet
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(msg);
		JsonObject service = element.getAsJsonObject();
		
		serviceName = client.ServiceN.valueOf(service.get("service").getAsString());
		JsonObject parametres = service.get("parametres").getAsJsonObject();

		RetourService reponseToSend = null;
		
		switch (serviceName) {
			case AJOUTER_NOM:
				reponseToSend = services.ajouterPersonne(
						new Personne(parametres.get("nom").getAsString(),
									parametres.get("apogee").getAsInt(),
									parametres.get("qualite").getAsString(),
									parametres.get("departement").getAsString()
						));
				break;
			case AJOUTER_SURNOM:
				reponseToSend = services.ajouterSurnom(
						parametres.get("nom").getAsString(),
						parametres.get("apogee").getAsInt(),
						parametres.get("surnom").getAsString()
				);
				break;
			case LISTER_REQUETE:
				reponseToSend = services.listerRequete(
						parametres.get("filtre").getAsString()
				);
				break;
			case LISTER_TOUT:
				reponseToSend = services.listerTout();
				break;
			case LISTER_UN:
				reponseToSend = services.listerUn(
						parametres.get("nom").getAsString(),
						parametres.get("apogee").getAsInt()
				);
				break;
			case MODIFIER_DEPARTEMENT:
				reponseToSend = services.modifierDepartement(
						parametres.get("nom").getAsString(),
						parametres.get("apogee").getAsInt(),
						parametres.get("departement").getAsString()
				);
				break;
			case MODIFIER_NOM:
				reponseToSend = services.modifierNom(
						parametres.get("nom").getAsString(),
						parametres.get("apogee").getAsInt(),
						parametres.get("nouveauNom").getAsString()
				);
				break;
			case MODIFIER_QUALITE:
				reponseToSend = services.modifierQualite(
						parametres.get("nom").getAsString(),
						parametres.get("apogee").getAsInt(),
						parametres.get("qualite").getAsString()
				);
				break;
			case MODIFIER_SURNOM:
				reponseToSend = services.modifierSurnom(
						parametres.get("nom").getAsString(),
						parametres.get("apogee").getAsInt(),
						parametres.get("surnom").getAsString(),
						parametres.get("nouveauSurnom").getAsString()
				);
				break;
			case SUPPRIMER_NOM:
				reponseToSend = services.supprimerPersonne(
						parametres.get("nom").getAsString(),
						parametres.get("apogee").getAsInt()
				);
				break;
			case SUPPRIMER_SURNOM:
				reponseToSend = services.supprimerSurnom(
						parametres.get("nom").getAsString(),
						parametres.get("apogee").getAsInt(),
						parametres.get("surnom").getAsString()
				);
				break;
			case DECONNECTION:
				reponseToSend = services.deconnection();
				break;
			default:
				return new RetourService(ReponseEnum.SERVICE_INCONNU,"","Le service demandé est inconnu","");
			}
		return reponseToSend;
	}


	public ServiceN getServiceDemande() {
		return serviceName;
	}

	public void setServiceDemande(ServiceN serviceDemande) {
		this.serviceName = serviceDemande;
	}

}
