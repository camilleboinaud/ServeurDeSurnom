package RMI.server.service;
import RMI.server.*;

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
