package RMI.client;

public enum ServiceN {
	Deconnection("RMI.server.service.Deconnection"),
	AjouterNom("RMI.server.service.AjouterNom"),
	AjouterSurnom("RMI.server.service.AjouterSurnom"),
	SupprimerSurnom("RMI.server.service.SupprimerSurnom"),
	SupprimerNom("RMI.server.service.SupprimerNom"),
	ListerUn("RMI.server.service.ListerUn"),
	ListerRequete("RMI.server.service.ListerRequete"),
	ListerTout("RMI.server.service.ListerTout"),
	ModifierNom("RMI.server.service.ModifierNom"),
	ModifierSurnom("RMI.server.service.ModifierSurnom"),
	ModifierQualite("RMI.server.service.ModifierQualite"),
	ModifierDepartement("RMI.server.service.ModifierDepartement");
	
	private String name;
	
	private ServiceN(String s){
		this.name = s;
	}
	
	public String toString(){
		return name;
	}
}
