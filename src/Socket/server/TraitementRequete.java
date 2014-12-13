package Socket.server;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class TraitementRequete {
    private Gson json;

	public TraitementRequete() {
		json = new GsonBuilder().disableHtmlEscaping().create();
	}

	public String execute(String lecture){
		Map<String,Object> map = json.fromJson(lecture, new TypeToken<Map<String,Object>>() {}.getType()); 
		RetourService reponse = null;
		try {
			Services s = (Services) new GsonBuilder().create().fromJson(map.get("parametres").toString(), Class.forName((String)map.get("service")));
			reponse = s.execute();
		} catch (JsonSyntaxException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return json.toJson(reponse);
	}

}
