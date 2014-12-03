package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurTCP {
	ServerSocket		soc			= null;
	Socket				clientSoc	= null;
	DataOutputStream	output;
	DataInputStream		input;
	BufferedReader		buffer;
	
	public ServeurTCP(int port) {
		try {
			soc = new ServerSocket(port);
			/*clientSoc = soc.accept();
			output = new DataOutputStream(clientSoc.getOutputStream());
			input = new DataInputStream(clientSoc.getInputStream());*/

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Renvoyer le message reçu
	 * @param lecture
	 */
	public String getMessageFromClient(){

		buffer = new BufferedReader(new InputStreamReader(input));
		String encours="";
		try {
			if((encours = this.buffer.readLine()) != null) return encours;
				//lecture+="\nSUC-"+encours.substring(encours.indexOf("<#>")+3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "";
	}
	/**
	 * Permet d'envoyer un message au client via le printstream
	 * @param tosend
	 */
	public void send(String tosend){
		try {
			System.out.println(tosend);
			this.output.writeBytes(tosend+"\n");
		}catch (IOException se){
			System.out.println(se.getMessage());
			this.disconnect();
		}
	}
	/**
	 * Fermer tous les streams et ports
	 */
	public void disconnect(){
		try {
			buffer.close();
			input.close();
			output.close();
			clientSoc.close();
			soc.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	 private boolean accept() {
		try {
			clientSoc = soc.accept();
			System.out.println("Client connecte");

			output = new DataOutputStream(clientSoc.getOutputStream());
			input = new DataInputStream(clientSoc.getInputStream());
		}
		catch (Exception e){return false;}

		return true;
	}
	
	public static void main(String args[]) {
		ServeurTCP s = new ServeurTCP(4040);
		TraitementRequete sc = new TraitementRequete();
		String rec="";
		boolean running=true;
		while(running){
			System.out.println("1er While");
			s.accept();
			while(true){
				System.out.println("2nd While");
				rec = sc.execute(s.getMessageFromClient());
				System.out.println(rec);
				if(rec.indexOf("DECONNECTION")!=-1){
					break;
				}else{
					s.send(rec);
				}
						
			}
		}
			s.disconnect();
	}
	
	}
