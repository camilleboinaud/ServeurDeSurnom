package Socket.server.service;
import Socket.server.*;

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
