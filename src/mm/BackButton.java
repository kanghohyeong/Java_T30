package mm;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BackButton extends JButton{
	private ImageIcon back_button_image = new ImageIcon("./Image/backbutton.png");
	private ImageIcon back_button_f_image = new ImageIcon("./Image/backbutton_f.png");
	int state=0;
	
	BackButton(){
		this.setIcon(back_button_image);
		this.setBounds(1020, 10, 200, 90);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setRolloverIcon(back_button_f_image);
	}
	
	public void setBackPage(JPanel page) {
		if(this.getActionListeners().length >0) {
			this.removeActionListener(this.getActionListeners()[0]);
		}
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Music click_sound = new Music("click",false);
				click_sound.start();
				Main.main_frame.getContentPane().removeAll();
				Main.main_frame.getContentPane().add(page);
				Main.main_frame.repaint();
				
			}
			
		});
	}
	
	public void setState(int state) {
		this.state = state;
	}
	public int getState() {
		return this.state;
	}

}
