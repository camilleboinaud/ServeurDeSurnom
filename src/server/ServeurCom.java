package server;

import java.io.IOException;

import client.ServiceN;

public class ServeurCom {
	public static String	MARQUEUR_DE_FIN	= "<#end>";
	private Serveur			s				= null;
	private ServiceN		serviceDemande	= null;
	private String			retours;
	private Donnees			donnees;

	public ServeurCom(int port) {
		s = new Serveur(port);
		donnees = new Donnees(this);
		retours = "";
	}

	public void execute() {
		String lecture = s.getMessageFromClient();
		// lecture+=s.getMessageFromClient();
		if ((lecture.indexOf(MARQUEUR_DE_FIN) != (lecture.length() - MARQUEUR_DE_FIN
				.length()))) {
			s.send("ERR-ERREUR_FIN_REQUETE<#end>\n");
		} else {
			this.decrypt(lecture);
		}

	}

	private void decrypt(String msg) {
		ReponseEnum reponse = this.traitementRequete(msg);
		this.sendReponse(serviceDemande, reponse, retours);

	}

	private ReponseEnum traitementRequete(String msg) {
		ReponseEnum reponseToSend = null;
		String params[] = msg.split("<#>|<#end>");
		serviceDemande = client.ServiceN.valueOf(params[1]);
		
		if (params[0].equals("REQ")) {
			switch (serviceDemande) {
			case AJOUTER_NOM:
				reponseToSend = donnees.ajouterPersonne(new Personne(params[2],
						Integer.parseInt(params[3]), params[4],
						params[5], params[6]));
				break;
			case AJOUTER_SURNOM:
				reponseToSend = donnees.ajouterSurnom(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			case LISTER_REQUETE:
				reponseToSend = donnees.listerRequete(params[2]);
				break;
			case LISTER_TOUT:
				reponseToSend = donnees.listerTout();
				break;
			case LISTER_UN:
				reponseToSend = donnees.listerUn(params[2], Integer.parseInt(params[3]));
				break;
			case MODIFIER_APOGEE:
				reponseToSend = donnees.modifierApogee(params[2],
						Integer.parseInt(params[3]),
						Integer.parseInt(params[4]));
				break;
			case MODIFIER_DEPARTEMENT:
				reponseToSend = donnees.modifierDepartement(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			case MODIFIER_NOM:
				reponseToSend = donnees.modifierNom(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			case MODIFIER_QUALITE:
				reponseToSend = donnees.modifierQualite(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			case MODIFIER_SURNOM:
				reponseToSend = donnees.modifierSurnom(params[2],
						Integer.parseInt(params[3]), params[4], params[5]);
				break;
			case SUPPRIMER_NOM:
				reponseToSend = donnees.supprimerPersonne(params[2],
						Integer.parseInt(params[3]));
				break;
			case SUPPRIMER_SURNOM:
				reponseToSend = donnees.supprimerSurnom(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			case DECONNECTION:
				reponseToSend = ReponseEnum.DECONNECTION;
				break;
			default:
				return ReponseEnum.SERVICE_INCONNU;
			}
		}
		return reponseToSend;
	}


	private void sendReponse(ServiceN service, ReponseEnum rep, String retours) {
		if (rep == ReponseEnum.SUC) {
			this.s.send("SUC-" + service.toString() +"<#>"+retours+ "<#end>\n");
		} else if(rep == ReponseEnum.DECONNECTION){
			try {
				disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			this.s.send("ERR-"+rep+"<#end>\n");
		}
		// this.s.send("SUC")
	}

	public void disconnect() throws IOException {
		s.disconnect();
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

	public static void main(String args[]) {
		ServeurCom sc = new ServeurCom(2222);
		try {
			sc.execute();
			sc.disconnect();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
