package server;

import java.io.IOException;

import client.ServiceN;

public class ServeurCom {
	public static String	MARQUEUR_DE_FIN	= "<#end>";
	Serveur					s				= null;
	ServiceN				serviceDemande	= null;
	ReponseEnum				reponseToSend	= null;

	public ServeurCom(int port) {
		s = new Serveur(port);
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
		/*
		 * System.out.println("reçu : " + msg); s.send(msg);
		 */
		this.traitementRequete(msg);

	}

	private ReponseEnum traitementRequete(String msg) {
		System.out.println("msg " + msg);
		String params[] = msg.split("<#>");
		System.out.println("params0" + params[0]);
		System.out.println("params1" + params[1]);
		if (params[0] == "REQ") {
			// Nous sommes dans une requete
			switch (params[1]) {
			case "AJOUTER_NOM":
				reponseToSend = ajouterNom(params[2],
						Integer.parseInt(params[3]), params[4].charAt(0),
						params[5], Integer.parseInt(params[6]), params[7]);
				break;
			case "AJOUTER_SURNOM":
				reponseToSend = ajouterSurnom(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			case "LISTER_REQUETE":
				reponseToSend = listerRequete(params[2]);
				break;
			case "LISTER_TOUT":
				reponseToSend = listerTout();
				break;
			case "LISTER_UN":
				reponseToSend = listerUn(params[2], Integer.parseInt(params[3]));
				break;
			case "MODIFIER_APOGEE":
				reponseToSend = modifierApogee(params[2],
						Integer.parseInt(params[3]),
						Integer.parseInt(params[4]));
				break;
			case "MODIFIER_DEPARTEMENT":
				reponseToSend = modifierDepartement(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			case "MODIFIER_NOM":
				reponseToSend = modifierNom(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			case "MODIFIER_QUALITE":
				reponseToSend = modifierQualite(params[2],
						Integer.parseInt(params[3]), params[4],
						Integer.parseInt(params[5]));
				break;
			case "MODIFIER_SURNOM":
				reponseToSend = modifierSurnom(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			case "SUPPRIMER_NOM":
				reponseToSend = supprimerNom(params[2],
						Integer.parseInt(params[3]));
				break;
			case "SUPPRIMER_SURNOM":
				reponseToSend = supprimerSurnom(params[2],
						Integer.parseInt(params[3]), params[4]);
				break;
			default:
				return ReponseEnum.SERVICE_INCONNU;
			}
		}
		return reponseToSend;
	}

	private ReponseEnum listerUn(String nom, int numeroApoge) {
		return ReponseEnum.SUC;
	}

	private ReponseEnum ajouterNom(String nom, int numeroApoge, char genre,
			String statut, int annee, String departement) {
		return ReponseEnum.SUC;
	}

	private ReponseEnum ajouterSurnom(String nom, int numeroApoge, String surnom) {
		return ReponseEnum.SUC;
	}

	private ReponseEnum listerRequete(String filtre) {
		return ReponseEnum.SUC;
	}

	private ReponseEnum listerTout() {
		System.out.println("lister tout");
		this.s.send("lister tout okay");
		this.s.send("SUC-LISTER_TOUT<#><#end>");
		return ReponseEnum.SUC;
	}

	private ReponseEnum modifierApogee(String nom, int apogee, int nouveauApogee) {
		return ReponseEnum.SUC;
	}

	private ReponseEnum modifierDepartement(String nom, int apogee,
			String departement) {
		return ReponseEnum.SUC;
	}

	private ReponseEnum modifierNom(String nom, int apogee, String newNom) {
		return ReponseEnum.SUC;
	}

	private ReponseEnum modifierQualite(String nom, int apogee, String statut,
			int annee) {
		return ReponseEnum.SUC;
	}

	private ReponseEnum modifierSurnom(String nom, int apogee, String surnom) {
		return ReponseEnum.SUC;
	}

	private ReponseEnum supprimerNom(String nom, int apogee) {
		return ReponseEnum.SUC;
	}

	private ReponseEnum supprimerSurnom(String nom, int apogee, String surnom) {
		return ReponseEnum.SUC;
	}

	public void disconnect() throws IOException {
		s.disconnect();
	}

	public static void main(String args[]) {
		ServeurCom sc = new ServeurCom(6464);
		try {
			sc.execute();
			sc.disconnect();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
