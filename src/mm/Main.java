package mm;

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.Timer;

public class Main {
	public static int game_state=0;
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static MainFrame main_frame = new MainFrame();
	public static Timer game_timer;
	public static float time;
	public static final int REFRESH_TIME = 50;
	public static Music background_music;
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		HomePage home_page = new HomePage();
		main_frame.add(home_page);
		main_frame.repaint();
		
		time = 0;
		ActionListener refresh_game = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time += (float)REFRESH_TIME/1000;
//				main_frame.repaint();
//				System.out.println(time);
			}
		};
		game_timer = new Timer(REFRESH_TIME, refresh_game );
		game_timer.start();
		
		background_music = new Music("silence_01", true);
		background_music.start();
		
		
		
//		new GamePlay();
		

	}
	
	


}
