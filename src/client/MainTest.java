package client;

public class MainTest {
	public static void main(String[] args) {

		TraitementReponse reponse = null;
		ConstructionRequete requete = null;
<<<<<<< HEAD
		
		ClientTCP client = new ClientTCP("172.19.250.194", 6464);
		//ClientTCP client = new ClientTCP("172.19.250.108", 6464);
		System.out.println("client créé");
		client.connect();
        System.out.println("client connecté");
			
		do{
=======

		System.out.println("debug 1");

		ClientTCP client = new ClientTCP("localhost", 6565);
		client.connect();

		do {
>>>>>>> 1b32b95f081ed6a917b2891b38fda86d2a693971
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			requete = new ConstructionRequete();
			System.out.println("rq: " + requete.toString());
			client.send(requete.toString());
            System.out.println(client.receive());
			reponse = new TraitementReponse(client.receive());
		} while (!reponse.affichage());

		client.disconnect();

	}
}
