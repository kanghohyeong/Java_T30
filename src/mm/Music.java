package mm;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Music extends Thread{
	private String name;
	private Player player;
	private boolean isLoop;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			this.name = name;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		isLoop = false;
		player.close();
		try {
			bis.close(); fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.interrupt();
	}
	
	public void changeMusic(String name, boolean isLoop) {
		player.close();
//		this.interrupt();
		this.name = name;
		this.isLoop = isLoop;
		
		
	}
	
	public void run() {
		do {
			try {
				fis = new FileInputStream(new File("./Sound/"+this.name+".mp3"));
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
				player.play();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(isLoop);
	}

}