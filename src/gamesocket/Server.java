package gamesocket;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ServerSocket ss;
		
		ss = new ServerSocket(33175);
		System.out.println("server start");
		while(true) {
			Socket soc = ss.accept();
			System.out.println("client accept");
			new ServerThread(soc).start();
		}

	}

}

class ServerThread extends Thread{
	private String t_name;
	private Socket client;
	
	public ServerThread(Socket client) {
		this.t_name = "ServerThread" + this.getId();
		this.client = client;
	}
	
	public void run() {
		System.out.println("service start" + t_name);
		try {
			InputStream in = client.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			OutputStream out = client.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			
			String requested_job = dis.readUTF();
			
			if(requested_job.equals("echo")) {
				System.out.println("request : echo");
				dos.writeUTF("hello");
				client.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}