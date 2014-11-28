package client;

import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainTest {
	public static void main(String[] args) {
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();

		TraitementReponse reponse = null;
		ConstructionRequete requete;
		
		ClientTCP client = new ClientTCP("localhost", 2424);
		//ClientTCP client = new ClientTCP("172.19.250.194", 2424);
		client.connect();


		do {
			requete = new ConstructionRequete();
			String json = gson.toJson(requete);
			System.out.println(json);
			client.send(requete.toString());
			if(requete.toString().indexOf(ServiceN.DECONNECTION.toString())!=-1) break;
			reponse = new TraitementReponse(client.receive());
			reponse.affichage();
		} while (true);

		client.disconnect();

	}
}
