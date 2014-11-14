package client;

public class MainTest {
	public static void main(String[] args) {

		TraitementReponse reponse = null;
		ConstructionRequete requete = null;
		
		ClientTCP client = new ClientTCP("172.19.250.194", 2222);
		//ClientTCP client = new ClientTCP("172.19.250.108", 6464);
		System.out.println("client créé");
		client.connect();
        System.out.println("client connecté");


		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			requete = new ConstructionRequete();
			System.out.println("rq: " + requete.toString());
			client.send(requete.toString());
            for(String s : client.receive()){
            	System.err.println(s);
            }
			reponse = new TraitementReponse(client.receive());
		} while (!reponse.affichage());

		client.disconnect();

	}
}
