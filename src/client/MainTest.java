package client;

public class MainTest {
	public static void main(String[] args){
		ClientTCP client = new ClientTCP("192.168.0.12", 6565);
		client.connect();
		client.send("REQ<#>AJOUTER_NOM<#>Camille Boinaud<#>21208556<#>M<#>ETUD4<#>SI<#end>\n");
		System.out.println(client.receive());
		client.disconnect();
	}
}
