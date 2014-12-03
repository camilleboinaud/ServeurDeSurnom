package server;


import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import client.ServiceN;

public class TraitementRequete {
    private Gson json;

	public TraitementRequete() {
		json = new GsonBuilder().disableHtmlEscaping().create();
	}

	public String execute(String lecture){
		System.out.println(lecture);
		Map<String,Object> map = json.fromJson(lecture, new TypeToken<Map<String,Object>>() {}.getType()); 
		RetourService reponse = null;
		try {
			System.out.println(map.get("service"));
			Services s = (Services) new GsonBuilder().create().fromJson(map.get("parametres").toString(), Class.forName((String)map.get("service")));
			reponse = s.execute();
		} catch (JsonSyntaxException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return json.toJson(reponse);
	}

}
