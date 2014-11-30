package client;

import java.io.*;
import java.net.*;

import server.ServeurUDP;

public class ClientUDP {
	int			port;
	InetAddress	serveur;
	DatagramPacket datapacketsend;
	DatagramSocket socket;
	DatagramPacket datapacketRecieved;
	int length;

	public ClientUDP(int port) {
		this.port = port;
		datapacketsend = null;
		this.length=1024;
		try {
			serveur = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			System.out.println("Erreur constructeur clientudp "
					+ e.getMessage());
		}
	}

	public void execute() {
		
		byte buffer[] = new byte[1024];
		buffer = ("test").getBytes();
		this.datapacketsend = new DatagramPacket(buffer, buffer.length, serveur,
				this.port);
		try {
			socket = new DatagramSocket();
			socket.send(this.datapacketsend);
			datapacketRecieved = new DatagramPacket(new byte[length],
					length);
			socket.receive(datapacketRecieved);
			
		} catch (IOException e) {
			System.out.println(""+e.getMessage());
		}
		
		System.out.println("Data recieved : "
				+ new String(datapacketRecieved.getData()));
		System.out.println("From : " + datapacketRecieved.getAddress() + ":"
				+ datapacketRecieved.getPort());
	}

	public static void main(String args[]) {
		ClientUDP cudp = new ClientUDP(4040);
		cudp.execute();
	}
}