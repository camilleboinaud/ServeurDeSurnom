package server;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class Services {
	
	protected Donnees bdd;
	protected GsonBuilder builder;
    protected Gson gson;
	
	public Services(){
		this.bdd = new Donnees();
		this.builder = new GsonBuilder();
		this.gson = builder.setPrettyPrinting().create();
	}
	
	public abstract RetourService execute();


}