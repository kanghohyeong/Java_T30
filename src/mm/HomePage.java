package mm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HomePage extends JPanel{
//	private static Image homepage_backgound = new ImageIcon(Main.class.getResource("/Image/homepage.png")).getImage();
//	private ImageIcon start_button_image = new ImageIcon(Main.class.getResource("/Image/startbutton.png"));
//	private ImageIcon manual_button_image = new ImageIcon(Main.class.getResource("/Image/manualbutton.png"));
//	private ImageIcon start_button_f_image = new ImageIcon(Main.class.getResource("/Image/startbutton_f.png"));
//	private ImageIcon manual_button_f_image = new ImageIcon(Main.class.getResource("/Image/manualbutton_f.png"));
	private static Image homepage_backgound = new ImageIcon("./Image/homepage.png").getImage();
	private ImageIcon start_button_image = new ImageIcon("./Image/startbutton.png");
	private ImageIcon manual_button_image = new ImageIcon("./Image/manualbutton.png");
	private ImageIcon start_button_f_image = new ImageIcon("./Image/startbutton_f.png");
	private ImageIcon manual_button_f_image = new ImageIcon("./Image/manualbutton_f.png");
	HomePage() {
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		
		JPanel background_panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(HomePage.homepage_backgound,0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		background_panel.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		JButton start_button = new JButton(start_button_image);
		start_button.setBounds(60, 300, 270, 100);
		start_button.setContentAreaFilled(false);
		start_button.setRolloverIcon(start_button_f_image);
		start_button.addActionListener(new StartButtonClickListener());
		
		
		JButton manual_button = new JButton(manual_button_image);
		manual_button.setBounds(60, 420, 270, 100);
		manual_button.setRolloverIcon(manual_button_f_image);
		manual_button.setContentAreaFilled(false);
		
		this.add(background_panel);
		this.add(start_button);
		this.add(manual_button);
		this.setComponentZOrder(background_panel, 2);
		this.setComponentZOrder(start_button, 0);
		
		if(Main.background_music != null) {
			Main.background_music.changeMusic("silence_01", true);
		}
	}
//	public void paint(Graphics g) {
//		g.drawImage(homepage_backgound, 0,0, null);
//		paintComponents(g);
//	}
	
	class StartButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Music click_sound = new Music("click",false);
			click_sound.start();
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new SettingPage());
			Main.main_frame.repaint();
		}
		
	}
	

}
