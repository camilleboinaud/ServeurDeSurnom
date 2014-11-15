package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	ServerSocket		soc			= null;
	Socket				clientSoc	= null;
	DataOutputStream	output;
	DataInputStream		input;
	BufferedReader		buffer;
	
	public Serveur(int port) {
		try {
			soc = new ServerSocket(port);
			clientSoc = soc.accept();
			output = new DataOutputStream(clientSoc.getOutputStream());
			input = new DataInputStream(clientSoc.getInputStream());

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Décrypter le message reçu
	 * @param lecture
	 */
	public String getMessageFromClient(){
		buffer = new BufferedReader(new InputStreamReader(input));

		String lecture="";
		String encours="";
		try {
			if((encours = this.buffer.readLine()) != null){
				lecture+=encours;
				//lecture+="\nSUC-"+encours.substring(encours.indexOf("<#>")+3);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lecture;
	}
	/**
	 * Permet d'envoyer un message au client via le printstream
	 * @param tosend
	 */
	public void send(String tosend){
		try {
			this.output.writeBytes(tosend);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Fermer tous les streams et ports
	 */
	public void disconnect() throws IOException{
		buffer.close();
		input.close();
		output.close();
		clientSoc.close();
		soc.close();
	}
	
	
	

}
