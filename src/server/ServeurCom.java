package server;

import java.io.IOException;


public class ServeurCom {
	public static String MARQUEUR_DE_FIN = "<#end>";
	Serveur s = null;
	public ServeurCom(int port){
		s = new Serveur(port);
	}
	public void execute(){
		String lecture = s.getMessageFromClient();
		//lecture+=s.getMessageFromClient();
		if((lecture.length()>= MARQUEUR_DE_FIN.length()) && (lecture.substring(lecture.length()-MARQUEUR_DE_FIN.length())==MARQUEUR_DE_FIN)){
			s.send("ERR-ERREUR_FIN_REQUETE<#><#>\n");
		}else{
			this.decrypt(lecture);
			s.send("SUC");
		}
		
	}
	public void decrypt(String msg){
		System.out.println("reçu : "+msg);
		s.send(msg);
		
	}
	public void disconnect() throws IOException{
		s.disconnect();
	}
	public static void main(String args[]) {
		ServeurCom sc = new ServeurCom(6565);
		try{
			sc.execute();
			sc.disconnect();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

}
