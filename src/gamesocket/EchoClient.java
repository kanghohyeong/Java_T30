package gamesocket;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;

public class EchoClient {
	String serverIP = "localhost";
	Socket soc;
	/*
	 0 ready
	 -1 connect error
	 1 connect ok
	 2 job ok 
	 */
	int connection_state; 
	String response_message;
	
	public EchoClient() {
		try {
			soc = new Socket(serverIP, 33175);
			System.out.println("connect ok");
			connection_state = 1;
			
			InputStream in = soc.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			OutputStream out = soc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			
			dos.writeUTF("echo");
			response_message = dis.readUTF();
			System.out.println(response_message);
			System.out.println("job ok");
			connection_state = 2;
			
		} catch(IOException e) {
			connection_state = -1;
			System.out.println("connect error");
		}
		
	}
	
	public int getConnectionState() {
		return this.connection_state;
	}
	public String getResponseMessage() {
		return this.response_message;
	}
}
