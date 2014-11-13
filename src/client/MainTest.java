package client;

public class MainTest {
	public static void main(String[] args){
		
		TraitementReponse reponse = null;
		ConstructionRequete requete = null;
		
		ClientTCP client = new ClientTCP("192.168.0.12", 6565);
		client.connect();
			
		do{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			requete = new ConstructionRequete();
			System.out.println("rq: "+requete.toString());
			client.send(requete.toString());
			reponse = new TraitementReponse(client.receive());
		} while(!reponse.affichage());
		
		client.disconnect();

	}
}
