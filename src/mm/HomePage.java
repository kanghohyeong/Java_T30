package mm;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HomePage extends JPanel{
	HomePage() {
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		
		JButton start_button = new JButton("���� ����");
		start_button.setBounds(200, 500, 400, 100);
		start_button.addActionListener(new StartButtonClickListener());
		
		
		JButton manual_button = new JButton("���� ����");
		manual_button.setBounds(800, 500, 400, 100);
		
		this.add(start_button);
		this.add(manual_button);
		
	}
	
	class StartButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new SettingPage());
		}
		
	}

}
