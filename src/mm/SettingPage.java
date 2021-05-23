package mm;

import java.awt.event.*;
import javax.swing.*;


public class SettingPage extends JPanel{
	JTextField input_card_row = new JTextField();
	JTextField input_card_col = new JTextField();
	JTextField input_game_life = new JTextField();
	JTextField input_game_time_limit = new JTextField();
	JLabel label_card_row = new JLabel("세로 카드 수");
	JLabel label_card_col = new JLabel("가로 카드 수");
	JLabel label_game_life = new JLabel("시도횟수");
	JLabel label_game_time_limit = new JLabel("시간 제한");
	
	SettingPage() {
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		this.setLayout(null);
		JButton start_button = new JButton("게임 시작");
		
		input_card_row.setBounds(500, 50, 400, 50);
		input_card_col.setBounds(500, 150, 400, 50);
		input_game_life.setBounds(500,250, 400, 50);
		input_game_time_limit.setBounds(500,350, 400,50);
		
		label_card_row.setBounds(200, 50, 400, 50);
		label_card_col.setBounds(200, 150, 400, 50);
		label_game_life.setBounds(200,250, 400, 50);
		label_game_time_limit.setBounds(200,350, 400,50);
		start_button.setBounds(500,500, 400, 100);
		
		start_button.addActionListener(new SettingButtonClickListener());
		
		this.add(input_card_row);
		this.add(input_card_col);
		this.add(input_game_life);
		this.add(input_game_time_limit);
		
		this.add(label_card_row);
		this.add(label_card_col);
		this.add(label_game_life);
		this.add(label_game_time_limit);
		this.add(start_button);
	}
	
	class SettingButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int card_row = Integer.parseInt(input_card_row.getText());
			int card_col = Integer.parseInt(input_card_col.getText());
			int game_life = Integer.parseInt(input_game_life.getText());
			int game_time_limit = Integer.parseInt(input_game_life.getText());
			
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new PlayPage(card_row, card_col,game_life,game_time_limit));
		}
		
	}

}
