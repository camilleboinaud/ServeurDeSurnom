package client;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientTCP {
	
	private String ipServer;
	private int numPort;
	
	//Socket client : 
	private Socket mySocket = null;
	private DataOutputStream os = null;
	private DataInputStream is = null;
	private BufferedReader bf = null;
	
	public ClientTCP(String ipServer, int numPort){
		this.ipServer = ipServer;
		this.numPort = numPort;
		try{
			mySocket = new Socket(ipServer, numPort);
			os = new DataOutputStream(mySocket.getOutputStream());
			is = new DataInputStream(mySocket.getInputStream());
		} catch(UnknownHostException e){
			System.err.println("Unknown host : "+ipServer);
		} catch(IOException e){
			System.err.println("Impossible to reach I/O from host : "+ipServer);
			System.err.println(e.getMessage());
		}
		this.exchange();
	}
		
	private void exchange(){
		if(mySocket!=null && os!=null && is!=null){
			try{
				os.writeBytes("REQ<#>AJOUTER_NOM<#>Nom<#>21208556<#>ETUD4<#>SI<#end>\n");
							
				bf = new BufferedReader(new InputStreamReader(is));
				String readServer;
				while((readServer = bf.readLine())!=null){
					System.out.println("Server Response : "+readServer);
					if(readServer.indexOf("<#end>")!=-1) break;
				}
				
			}catch(IOException e){
				System.err.println("Impossible to reach I/O from host : "+ipServer);
				System.err.println(e.getMessage());
			}
			this.closeConnection();
		}
		
	}
	
	private void sendRequest(){
		
	}
	
	private void receiveRequest(){
		
	}
	
	private void closeConnection(){
		try {
			os.close();
			is.close();
			mySocket.close();
		} catch(UnknownHostException e){
			System.err.println("Unknown host : "+ipServer);
		} catch(IOException e){
			System.err.println("Impossible to reach I/O from host : "+ipServer);
			System.err.println(e.getMessage());
		}
		
	}

}
