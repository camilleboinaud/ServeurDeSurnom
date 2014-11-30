package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServeurUDP {
	DatagramSocket	socket;
	int port;
	static byte buffer[] = new byte[1024];
	
	public ServeurUDP(int port) {
		this.port=port;
		try {
			socket	= new DatagramSocket(port);
		} catch (SocketException e) {
			System.out.println("Erreur Constructeur DatagramSocket. "+e.getMessage());
		}
	}
	/* Serveur echo pour le moment */
	public void execute() {
		while (true) {
			DatagramPacket data = new DatagramPacket(buffer, buffer.length);
			try {
				socket.receive(data);
				System.out.println(data.getAddress());
				socket.send(data);
			} catch (IOException e) {
				System.out.println("Erreur execute : "+e.getMessage());
			}
			
		}
	}
	public static void main(String args[]){
		ServeurUDP sudp = new ServeurUDP(4040);
		sudp.execute();
	}
}
