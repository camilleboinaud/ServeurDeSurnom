package client;

import java.util.ArrayList;

public class MainTest {
	public static void main(String[] args) {

		TraitementReponse reponse = null;
		ConstructionRequete requete = null;
		
		//ClientTCP client = new ClientTCP("localhost", 2222);
		ClientTCP client = new ClientTCP("172.19.250.194", 2424);
		client.connect();


		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			requete = new ConstructionRequete();
			client.send(requete.toString());
			if(requete.toString().indexOf(ServiceN.DECONNECTION.toString())!=-1) break;
			reponse = new TraitementReponse(client.receive());
			reponse.affichage();
		} while (true);

		client.disconnect();

	}
}
