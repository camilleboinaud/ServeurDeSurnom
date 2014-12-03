package client;
public enum ServiceN {
	Deconnection("server.service.Deconnection"),
	AjouterNom("server.service.AjouterNom"),
	AjouterSurnom("server.service.AjouterSurnom"),
	SupprimerSurnom("server.service.SupprimerSurnom"),
	SupprimerNom("server.service.SupprimerNom"),
	ListerUn("server.service.ListerUn"),
	ListerRequete("server.service.ListerRequete"),
	ListerTout("server.service.ListerTout"),
	ModifierNom("server.service.ModifierNom"),
	ModifierSurnom("server.service.ModifierSurnom"),
	ModifierQualite("server.service.ModifierQualite"),
	ModifierDepartement("server.service.ModifierDepartement");
	
	private String name;
	
	private ServiceN(String s){
		this.name = s;
	}
	
	public String toString(){
		return name;
	}
}
