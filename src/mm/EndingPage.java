package mm;

import java.awt.event.*;
import javax.swing.*;

public class EndingPage extends JPanel{
	JLabel label_result = new JLabel();
	
	EndingPage(boolean result){
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		this.setLayout(null);
		JButton home_button = new JButton("처음으로");
		home_button.addActionListener(new HomeButtonClickListener());
		
		if(result) { // win
			label_result.setText("승리!");
		}
		else {
			label_result.setText("패배!");
		}
		
		label_result.setBounds(500,200,400,100);
		home_button.setBounds(500, 400, 400,100);
		
		this.add(label_result);
		this.add(home_button);
		
	}
	
	class HomeButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new HomePage());
			Main.main_frame.repaint();
		}
		
	}
	

}
