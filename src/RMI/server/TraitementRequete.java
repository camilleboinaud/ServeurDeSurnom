package RMI.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import RMI.protocole.Requete;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class TraitementRequete extends UnicastRemoteObject implements Requete {

	private static final long serialVersionUID = -8314288986127859205L;
	private Gson json;

	public TraitementRequete()  throws RemoteException{
		super();
		json = new GsonBuilder().disableHtmlEscaping().create();
	}

	public String execute(String lecture) throws RemoteException {
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
