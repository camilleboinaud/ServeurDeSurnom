package client;

public class MainTest {
	public static void main(String[] args){
		
		TraitementReponse reponse = null;
		ConstructionRequete requete = null;
		
		System.out.println("debug 1");
		
		ClientTCP client = new ClientTCP("10.212.127.153", 6565);
		client.connect();
			
		do{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			requete = new ConstructionRequete();
			client.send(requete.toString());
			reponse = new TraitementReponse(client.receive());
		} while(!reponse.affichage());
		
		client.disconnect();

	}
}
