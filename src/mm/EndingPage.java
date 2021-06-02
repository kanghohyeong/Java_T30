package mm;

import java.awt.event.*;
import javax.swing.*;
import gamesocket.EchoClient;

public class EndingPage extends JPanel{
	JLabel label_result = new JLabel();
	
	EndingPage(boolean result){
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		this.setLayout(null);
		JButton home_button = new JButton("ó������");
		home_button.addActionListener(new HomeButtonClickListener());
		
		if(result) { // win
			label_result.setText("�¸�!");
		}
		else {
			label_result.setText("�й�!");
		}
		
		label_result.setBounds(500,200,400,100);
		home_button.setBounds(500, 400, 400,100);
		
		this.add(label_result);
		this.add(home_button);
		
		EchoClient ec = new EchoClient();
		int client_state = ec.getConnectionState();
		if(client_state == -1) {
			System.out.println("no server connection");
		} else if(client_state == 2) {
			System.out.println(ec.getResponseMessage());
		}
		
	}
	
	class HomeButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new HomePage());
			Main.main_frame.repaint();
		}
		
	}
	

}
