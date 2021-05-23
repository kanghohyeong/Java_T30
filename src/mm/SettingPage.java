package mm;

import java.awt.event.*;
import javax.swing.*;


public class SettingPage extends JPanel{
	JTextField input_card_row = new JTextField();
	JTextField input_card_col = new JTextField();
	JLabel label_card_row = new JLabel("세로 카드 수");
	JLabel label_card_col = new JLabel("가로 카드 수");
	int card_row;
	int card_col;
	
	SettingPage() {
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		this.setLayout(null);
		JButton start_button = new JButton("게임 시작");
		
		input_card_row.setBounds(500, 100, 400, 100);
		input_card_col.setBounds(500, 300, 400, 100);
		label_card_row.setBounds(200, 100, 400, 100);
		label_card_col.setBounds(200, 300, 400, 100);
		start_button.setBounds(500,500, 400, 100);
		
		start_button.addActionListener(new SettingButtonClickListener());
		
		this.add(input_card_row);
		this.add(input_card_col);
		this.add(label_card_row);
		this.add(label_card_col);
		this.add(start_button);
	}
	
	class SettingButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			card_row = Integer.parseInt(input_card_row.getText());
			card_col = Integer.parseInt(input_card_col.getText());
			
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new PlayPage(card_row, card_col));
		}
		
	}

}
