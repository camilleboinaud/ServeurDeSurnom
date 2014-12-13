package Socket.server;

public class RetourService{
	
	private ReponseEnum statut;
	private String service;
	private String message;
	private String reponse;
	
	public RetourService(ReponseEnum statut, String service, String message, String reponse){
		this.statut = statut;
		this.service = service;
		this.message = message;
		this.reponse = reponse;
	}
	
	public ReponseEnum getStatut(){
		return statut;
	}
	
	public String getReponse(){
		return reponse;
	}
	
	public String getMessage(){
		return message;
	}
	
	public String getService(){
		return service;
	}
	
}