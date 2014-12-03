package server.service;

import server.ReponseEnum;
import server.RetourService;
import server.Services;

public class Deconnection extends Services{

	public Deconnection() {}
	
	public RetourService execute(){
		return new RetourService(
				ReponseEnum.DECONNECTION, 
				"DECONNECTION",
				"Deconnection du serveur",
				""
		);
	}

}
