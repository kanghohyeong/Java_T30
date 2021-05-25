package mm;

import java.awt.Image;
import javax.swing.*;

public class MainFrame extends JFrame{
	
	private Image main_screen_image;
	
	MainFrame() {
		setTitle("Memory Matching");
		this.setLayout(null);
		
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
