package RMI.client;

import Socket.client.ClientTCP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainTest {
	public static void main(String[] args) {
		
		Gson gson = new GsonBuilder().create();
		TraitementReponse r = new TraitementReponse();
        
		ConstructionRequete requete;
		
		ClientTCP client = new ClientTCP("localhost", 7070);
		//ClientUDP client = new ClientUDP("localhost", 7070);
		client.connect();


		do {
			requete = new ConstructionRequete();
			String json = gson.toJson(requete);
			client.send(json.toString());
			if(json.indexOf(ServiceN.Deconnection.toString())!=-1) break;
			String received = client.receive();
			r.affichage(received);
		} while (true);
		System.out.println("Déconnection");
		client.disconnect();

	}
}
