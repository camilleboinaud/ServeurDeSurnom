package client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainTest {
	public static void main(String[] args) {
		
		Gson gson = new GsonBuilder().create();
		TraitementReponse r = new TraitementReponse();
        
		ConstructionRequete requete;
		
		ClientTCP client = new ClientTCP("localhost", 7070);
		//ClientTCP client = new ClientTCP("172.19.250.194", 2424);
		//ClientUDP client = new ClientUDP("localhost", 7070);
		client.connect();


		do {
			requete = new ConstructionRequete();
			String json = gson.toJson(requete);
			client.send(json.toString());
			System.out.println(json.toString());
			String received = client.receive();
			if(received.indexOf(ServiceN.Deconnection.toString())!=-1) break;
			r.affichage(received);
		} while (true);

		client.disconnect();

	}
}
