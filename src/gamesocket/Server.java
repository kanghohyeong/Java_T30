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
		System.out.println(t_name+ ">>service start" );
		try {
			InputStream in = client.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			OutputStream out = client.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			
			String requested_job = dis.readUTF();
			
			synchronized (ServerThread.class) {
				if(requested_job.equals("echo")) {
					System.out.println(t_name + ">>request : echo");
					dos.writeUTF("hello");
					client.close();
				}
				else if(requested_job.equals("print")) { //스코어보드 가져오기
					System.out.println(t_name+">>request : print score board");
					ScoreHandler sh = new ScoreHandler(dis.readUTF());
					String score_board = sh.printScoreBoard();
					dos.writeUTF(score_board);
					System.out.println(t_name+">>print finish");
					client.close();
				}
				else if(requested_job.equals("insert")) { //점수 추가하기
					System.out.println(t_name+">>request : insert score");
					ScoreHandler sh = new ScoreHandler(dis.readUTF());
					String[] player_info = dis.readUTF().split(" ");
					int rank = sh.insertNewScore(player_info[0], Integer.parseInt(player_info[1]), Integer.parseInt(player_info[2]));
					sh.saveScoreBoard();
					dos.writeUTF(sh.printScoreBoard());
					dos.writeUTF(String.valueOf(rank));
					System.out.println(t_name+">>insert finish");
					client.close();
				}
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}