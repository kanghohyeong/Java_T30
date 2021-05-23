package mm;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	MainFrame() {
		setTitle("Memory Matching");
		this.setLayout(null);
		
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
