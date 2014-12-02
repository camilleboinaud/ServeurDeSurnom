package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class ServeurUDP {
	DatagramSocket		socket;
	int					port;
	byte			bufferinput[]	= new byte[1024];
	byte			bufferoutput[]	= new byte[1024];
	DatagramPacket		datareceive, datasend;
	InetSocketAddress	remAddr;

	public ServeurUDP(int port) {
		this.port = port;
		try {
			socket = new DatagramSocket(port);
			// remAddr = InetSocketAddress.createUnresolved("127.0.0.1", port);
		} catch (SocketException e) {
			System.out.println("Erreur Constructeur DatagramSocket. "
					+ e.getMessage());
		}
	}

	/**
	 * Fonction qui execute le serveur
	 */
	public void execute() {
		while (true) {
			DatagramPacket dt = this.receive();
			String recu = new String(dt.getData());
			System.out.println("String recu : " + recu);
			this.send(recu);
		}
	}

	/**
	 * Fonction d'envoi d'une string
	 * 
	 * @param tosend
	 *            la String à envoyer
	 */
	public void send(String tosend) {
		try {
			
			bufferoutput = tosend.getBytes();
			datasend = new DatagramPacket(bufferoutput, bufferoutput.length);
			System.out.println("pret a envoyer : "
					+ new String(datasend.getData()));
			socket.send(datasend);
		} catch (IOException e) {
			System.out.println("Erreur send : " + e.getMessage());
		}
	}

	/**
	 * Recevoir une chaîne des clients
	 * 
	 * @return retourne un datagrampacket contenant la reception
	 */
	public DatagramPacket receive() {

		try {
			datareceive = new DatagramPacket(bufferinput, bufferinput.length);
			socket.receive(datareceive);
		} catch (IOException e) {
			System.out.println("Erreur receive : " + e.getMessage());
		}
		return datareceive;
	}

	/**
	 * Fermer tous les streams et ports
	 */
	public void disconnect() {
		socket.close();
	}

	public static void main(String args[]) {
		ServeurUDP sudp = new ServeurUDP(7070);
		TraitementRequete sc = new TraitementRequete();
		String rec="";
		boolean running=true;
		while(running){
			System.out.println("1er While");
			while (true) {
				DatagramPacket dt = sudp.receive();
				System.out.println("avant traitement : "+new String(dt.getData()));
				rec = sc.execute(new String(dt.getData()));
				InetAddress addr = dt.getAddress();
				int porto = dt.getPort();
				String recu = new String(dt.getData());
				System.out.println("String recu : " + recu);
				System.out.println(rec);
				if(rec.indexOf("DECONNECTION")!=-1){
					break;
				}else{
					sudp.send(rec);
				}
			}
					
			}
		sudp.disconnect();
	}
}
