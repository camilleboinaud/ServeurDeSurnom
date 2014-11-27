package server;


import client.ServiceN;

public class ServeurCom {
	public static String	MARQUEUR_DE_FIN	= "<#end>";
	private ServiceN		serviceDemande	= null;
	private String			retours;
	private Services		services;

	public ServeurCom() {
		services = new Services();
		retours = "";
	}

	public String execute(String lecture){
		
		if(lecture.indexOf(ReponseEnum.DECONNECTION.toString()) != -1){
			return ReponseEnum.DECONNECTION.toString();
		}
		// lecture+=s.getMessageFromClient();
		if ((lecture.indexOf(MARQUEUR_DE_FIN) != (lecture.length() - MARQUEUR_DE_FIN
				.length()))) {
			 return ("ERR-ERREUR_FIN_REQUETE<#end>\n");
		} else {
			return this.decrypt(lecture);
		}
	}

	private String decrypt(String msg) {
		RetourService reponse = this.traitementRequete(msg);
		return this.sendReponse(serviceDemande, reponse.getStatut(), reponse.getReponse());

	}

	private RetourService traitementRequete(String msg) {
		RetourService reponseToSend = null;
		String params[] = msg.split("<#>|<#end>");
		serviceDemande = client.ServiceN.valueOf(params[1]);
		
		if (params[0].equals("REQ")) {
			switch (serviceDemande) {
			case AJOUTER_NOM:
				reponseToSend = services.ajouterPersonne(new Personne(params[2],
						Integer.parseInt(params[3]), params[4],
						params[5], params[6]));
				break;
			case AJOUTER_SURNOM:
				reponseToSend = services.ajouterSurnom(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			case LISTER_REQUETE:
				reponseToSend = services.listerRequete(params[2]);
				break;
			case LISTER_TOUT:
				reponseToSend = services.listerTout();
				break;
			case LISTER_UN:
				reponseToSend = services.listerUn(params[2], Integer.parseInt(params[3]));
				break;
			case MODIFIER_APOGEE:
				reponseToSend = services.modifierApogee(params[2],
						Integer.parseInt(params[3]),
						Integer.parseInt(params[4]));
				break;
			case MODIFIER_DEPARTEMENT:
				reponseToSend = services.modifierDepartement(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			case MODIFIER_NOM:
				reponseToSend = services.modifierNom(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			case MODIFIER_QUALITE:
				reponseToSend = services.modifierQualite(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			case MODIFIER_SURNOM:
				reponseToSend = services.modifierSurnom(params[2],
						Integer.parseInt(params[3]), params[4], params[5]);
				break;
			case SUPPRIMER_NOM:
				reponseToSend = services.supprimerPersonne(params[2],
						Integer.parseInt(params[3]));
				break;
			case SUPPRIMER_SURNOM:
				reponseToSend = services.supprimerSurnom(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			default:
				return new RetourService(ReponseEnum.SERVICE_INCONNU,"");
			}
		}
		return reponseToSend;
	}


	private String sendReponse(ServiceN service, ReponseEnum rep, String retours) {
		if (rep == ReponseEnum.SUC) {
			String reponse = "SUC-" + service.toString() +"<#>"+retours+ "<#end>\n";
			System.out.println(""+reponse);
			return(reponse);
			
		}else{
			String error = "ERR-"+rep+"<#end>\n";
			System.out.println(""+error);
			return(error);
			
		}
		// this.s.send("SUC")
	}

	public ServiceN getServiceDemande() {
		return serviceDemande;
	}

	public void setServiceDemande(ServiceN serviceDemande) {
		this.serviceDemande = serviceDemande;
	}

	public String getRetours() {
		return retours;
	}

	public void setRetours(String retours) {
		this.retours = retours;
	}


	

}
