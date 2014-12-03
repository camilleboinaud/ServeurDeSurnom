package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurTCP {
	ServerSocket		soc			= null;
	Socket				clientSoc	= null;
	
	public ServeurTCP(int port) {
		try {
			soc = new ServerSocket(port);	
			while(true){
					clientSoc = soc.accept();
					ThreadClient t = new ThreadClient(clientSoc);
					new Thread(t).start();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	class ThreadClient implements Runnable{
		Socket threadSocket;
        
        public ThreadClient (Socket socket) {
            threadSocket = socket;
        }
         
        public void run () {
            try {
                PrintWriter output = new PrintWriter(threadSocket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(threadSocket.getInputStream()));              
                TraitementRequete requete = new TraitementRequete();
                while (true) {
                    String in = input.readLine();
                    if (in == null) break;
                    String req = requete.execute(in)+"\n";
                    if(req.indexOf("DECONNECTION")!=-1){
    					break;
    				} else{
    					output.println(req);
    				}
                }
            } catch(IOException exception) {
                System.out.println("Error: " + exception);
            }
        }
	}
	
	public static void main(String[] args){
		new ServeurTCP(7070);
	}
}
