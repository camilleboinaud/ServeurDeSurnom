package Socket.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientUDP {
	int				port;
	InetAddress		serveur;
	DatagramPacket	datapacketsend;
	DatagramSocket	socket;
	DatagramPacket	datapacketRecieved;
	int				length;
	String			host;
	byte[]			bufferoutput;

	public ClientUDP(String host, int port) {
		this.port = port;
		datapacketsend = null;
		this.length = 1024;
		bufferoutput = new byte[1024];

	}

	public void connect() {
		try {
			serveur = InetAddress.getByName(host);
			socket = new DatagramSocket();
		} catch (UnknownHostException | SocketException e) {
			System.out.println("Erreur constructeur clientudp "
					+ e.getMessage());
		}
	}

	public void send(String tosend) {
		this.datapacketsend = new DatagramPacket(bufferoutput,
				bufferoutput.length, serveur, this.port);
		try {
			socket.send(this.datapacketsend);
		} catch (IOException e) {
			System.out.println(""+e.getMessage());
		}
	}

	public String receive() {
		String rec;
		datapacketRecieved = new DatagramPacket(new byte[length], length);
		try {
			socket.receive(datapacketRecieved);
		} catch (IOException e) {
			System.out.println(""+e.getMessage());
		}
		rec = new String(datapacketRecieved.getData());
		System.out.println("Data recieved : "
				+rec );
		System.out.println("From : " + datapacketRecieved.getAddress() + ":"
				+ datapacketRecieved.getPort());
		return rec;
	}
	public void disconnect(){
		socket.close();
	}

	/*
	 * public static void main(String args[]) { ClientUDP cudp = new
	 * ClientUDP("localhost", 6060); cudp.connect(); cudp.execute(); }
	 */
}