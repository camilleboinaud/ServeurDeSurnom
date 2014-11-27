package server;

public class RetourService{
	
	private ReponseEnum statut;
	private String reponse;
	
	public RetourService(ReponseEnum statut, String reponse){
		this.statut = statut;
		this.reponse = reponse;
	}
	
	public ReponseEnum getStatut(){
		return statut;
	}
	
	public String getReponse(){
		return reponse;
	}
	
}