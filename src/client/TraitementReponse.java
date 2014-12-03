package client;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class TraitementReponse {
	
    private Gson json;
	private String reponse;
	
	public TraitementReponse(){
		this.reponse = reponse;
		this.json = new GsonBuilder().create();
	}
	
	public void affichage(String reponse){
		Map<String,Object> map = json.fromJson(reponse, new TypeToken<Map<String,Object>>() {}.getType());
		System.out.println("\n"+map.get("message")+"\n");
		String s = (String)map.get("reponse");
		System.out.println(s.replaceAll("\"", "").replaceAll("@","\n")+"\n");
	}
	
}