package client;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


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
	}
	
	public void connect(){
		
		if(mySocket!=null && os!=null && is!=null) disconnect();
			
		try{
			mySocket = new Socket(ipServer, numPort);
			os = new DataOutputStream(mySocket.getOutputStream());
			is = new DataInputStream(mySocket.getInputStream());
		} catch(UnknownHostException e){
			System.err.println("Unknown host : "+ipServer);
			System.err.println(e.getMessage());
		} catch(IOException e){
			System.err.println("Impossible to reach I/O from host : "+ipServer);
			System.err.println(e.getMessage());
		}
	}
		
	public void send(String service){
		if(mySocket!=null && os!=null && is!=null){
			try{
				os.writeBytes(service+"\n");
			}catch(IOException e){
				System.err.println("Impossible to reach I/O from host : "+ipServer);
				System.err.println(e.getMessage());
			}
		}
	}
	
	public String receive(){
		String tmp = "";

		if(mySocket!=null && os!=null && is!=null){
			try{
				bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				if((tmp = bf.readLine())!=null)	return tmp;
			}catch(IOException e){
				System.err.println("Impossible to reach I/O from host : "+ipServer);
				System.err.println(e.getMessage());
			}
		}
		return "";
	}
	
	

	public void disconnect(){
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
