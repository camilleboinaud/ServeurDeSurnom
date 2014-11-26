package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

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
	 * Renvoyer le message reçu
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
			output = new DataOutputStream(clientSoc.getOutputStream());
			input = new DataInputStream(clientSoc.getInputStream());
		}
		catch (Exception e){return false;}

		System.out.println("Client connecté");
		return true;
	}
	
	public static void main(String args[]) {
		Serveur s = new Serveur(2424);
		ServeurCom sc = new ServeurCom();
		String rec="";
		boolean running=true;
		while(running){
			System.out.println("1er While");
			s.accept();
			while(true){
				System.out.println("2nd While");
				rec = sc.execute(s.getMessageFromClient());
				if(ReponseEnum.DECONNECTION.toString().equals(rec)){
					break;
				}else{
					s.send(rec);
				}
						
			}
		}
			s.disconnect();
	}
	
	}
