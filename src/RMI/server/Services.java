package RMI.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class Services {
	
	protected transient Donnees bdd;
	protected GsonBuilder builder;
    protected Gson gson;
	
	public Services(){
		this.builder = new GsonBuilder();
		this.gson = builder.setPrettyPrinting().create();
		bdd = Donnees.bdd;
	}
	
	public abstract RetourService execute();
	
}