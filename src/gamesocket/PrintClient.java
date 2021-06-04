package gamesocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PrintClient extends Thread {
	String serverIP = "112.171.248.24";
	Socket soc;
	/*
	 0 ready
	 -1 connect error
	 1 connect ok
	 2 job ok 
	 */
	int connection_state; 
	String response_message;
	int card;
	int level;
	
	public PrintClient(int card, int level){
		this.card = card;
		this.level = level;
	}
	
	
	public void run() {
		try {
			soc = new Socket(serverIP, 33175);
			System.out.println("print connect ok");
			connection_state = 1;
			
			InputStream in = soc.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			OutputStream out = soc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			
			dos.writeUTF("print");
			dos.writeUTF(parseLevel());
			response_message = dis.readUTF();
			System.out.println("job ok");
			connection_state = 2;
			
		} catch(IOException e) {
			connection_state = -1;
			System.out.println("connect error");
		}
	}
	
	String parseLevel() {
		String game_level = "";
		game_level += String.valueOf(this.card);
		if(this.level == 1) {
			game_level += "easy";
		}
		else if(this.level == 2) {
			game_level += "normal";
		}
		else if(this.level == 3) {
			game_level += "hard";
		}
		else if(this.level == 4){
			game_level += "hell";
		}
		return game_level;
	}
	public int getConnectionState() {
		return this.connection_state;
	}
	public String getResponseMessage() {
		return this.response_message;
	}
}
