package mm;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

public class ManualPage extends JPanel{
	private static Image manualpage_background = new ImageIcon("./Image/manualpage.png").getImage();
	private ImageIcon back_button_image = new ImageIcon("./Image/backbutton.png");
	private ImageIcon back_button_f_image = new ImageIcon("./Image/backbutton_f.png");
	JLabel background_label;
	JButton back_button;
	
	ManualPage(){
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		this.setOpaque(false);
		this.setLayout(null);
		
		background_label = new JLabel() {
			public void paintComponent(Graphics g) {
				g.drawImage(ManualPage.manualpage_background,0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		background_label.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		this.add(background_label);
		
		back_button = new JButton(back_button_image);
		back_button.setBounds(970, 30, 200, 90);
		back_button.setContentAreaFilled(false);
		back_button.setBorderPainted(false);
		back_button.setFocusPainted(false);
		back_button.setRolloverIcon(back_button_f_image);
		back_button.addActionListener(new BackButtonClickListener());
		this.add(back_button);
		this.setComponentZOrder(background_label, this.getComponentCount()-1);
		
	}
	
	class BackButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Music click_sound = new Music("click",false);
			click_sound.start();
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new HomePage());
			Main.main_frame.repaint();
		}
	}
}
