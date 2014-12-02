package client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainTest {
	public static void main(String[] args) {
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();

		ConstructionRequete requete;
		
		ClientTCP client = new ClientTCP("localhost", 7070);
		//ClientTCP client = new ClientTCP("172.19.250.194", 2424);
		//ClientUDP client = new ClientUDP("localhost", 7070);
		client.connect();


		do {
			requete = new ConstructionRequete();
			String json = gson.toJson(requete);
			System.out.println(json);
			client.send(json);
			if(requete.toString().indexOf(ServiceN.DECONNECTION.toString())!=-1) break;
			System.out.println(client.receive());
			//reponse = new TraitementReponse();
			//reponse.affichage();
		} while (true);

		client.disconnect();

	}
}
