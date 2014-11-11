package client;

public class MainTest {
	public static void main(String[] args){
		ClientMenu requete = new ClientMenu();
		ClientTCP client = new ClientTCP("192.168.0.12", 6565);
		client.connect();
		client.send(requete.toString());
		System.out.println(client.receive());
		client.disconnect();
	}
}
