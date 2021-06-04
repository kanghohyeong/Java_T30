package mm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;


public class SettingPage extends JPanel{
	private static Image settingpage_background = new ImageIcon("./Image/settingpage.png").getImage();
	private ImageIcon start_button_image = new ImageIcon("./Image/startbutton.png");
	private ImageIcon start_button_f_image = new ImageIcon("./Image/startbutton_f.png");
	JLabel background_label;
	
	JTextField input_card_row = new JTextField();
	JTextField input_card_col = new JTextField();
	JTextField input_game_life = new JTextField();
	JTextField input_game_time_limit = new JTextField();
	JLabel label_card_row = new JLabel("카드 수(세로)");
	JLabel label_card_col = new JLabel("카드 수(가로)");
	JLabel label_game_life = new JLabel("목숨 수");
	JLabel label_game_time_limit = new JLabel("제한 시간(초)");
	BackButton back_button = new BackButton();
	
	SettingPage() {
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		this.setLayout(null);
		background_label = new JLabel() {
			public void paintComponent(Graphics g) {
				g.drawImage(SettingPage.settingpage_background,0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		background_label.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		this.add(background_label);
		
		Font setting_font = new Font("HY견명조",Font.PLAIN,30);
		
		JButton start_button = new JButton(start_button_image);
		start_button.setBounds(500,500, 270, 100);
		start_button.setContentAreaFilled(false);
		start_button.setRolloverIcon(start_button_f_image);
		start_button.addActionListener(new SettingButtonClickListener());
		back_button.addActionListener(new BackButtonClickListener());
		
		input_card_row.setBounds(600, 50, 400, 50);
		input_card_row.setFont(setting_font);
		input_card_row.setHorizontalAlignment(JTextField.CENTER);
		input_card_col.setBounds(600, 150, 400, 50);
		input_card_col.setFont(setting_font);
		input_card_col.setHorizontalAlignment(JTextField.CENTER);
		input_game_life.setBounds(600,250, 400, 50);
		input_game_life.setFont(setting_font);
		input_game_life.setHorizontalAlignment(JTextField.CENTER);
		input_game_time_limit.setBounds(600,350, 400,50);
		input_game_time_limit.setFont(setting_font);
		input_game_time_limit.setHorizontalAlignment(JTextField.CENTER);
		
		
		label_card_row.setBounds(300, 50, 220, 50);
		label_card_row.setFont(setting_font);
		label_card_row.setForeground(new Color(188,38,38));
		label_card_row.setHorizontalAlignment(JLabel.CENTER);
		label_card_row.setBackground(new Color(0,0,0,90));
		label_card_row.setOpaque(true);
		
		label_card_col.setBounds(300, 150, 220, 50);
		label_card_col.setFont(setting_font);
		label_card_col.setForeground(new Color(188,38,38));
		label_card_col.setHorizontalAlignment(JLabel.CENTER);
		label_card_col.setBackground(new Color(0,0,0,90));
		label_card_col.setOpaque(true);
		
		label_game_life.setBounds(300,250, 220, 50);
		label_game_life.setFont(setting_font);
		label_game_life.setForeground(new Color(188,38,38));
		label_game_life.setHorizontalAlignment(JLabel.CENTER);
		label_game_life.setBackground(new Color(0,0,0,90));
		label_game_life.setOpaque(true);
		
		label_game_time_limit.setBounds(300,350, 220,50);
		label_game_time_limit.setFont(setting_font);
		label_game_time_limit.setForeground(new Color(188,38,38));
		label_game_time_limit.setHorizontalAlignment(JLabel.CENTER);
		label_game_time_limit.setBackground(new Color(0,0,0,90));
		label_game_time_limit.setOpaque(true);
		
		
		this.add(input_card_row);
		this.add(input_card_col);
		this.add(input_game_life);
		this.add(input_game_time_limit);
		
		this.add(label_card_row);
		this.add(label_card_col);
		this.add(label_game_life);
		this.add(label_game_time_limit);
		this.add(start_button);
		this.add(back_button);
		this.setComponentZOrder(background_label, this.getComponentCount()-1);
	}
	
	class SettingButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int card_row = Integer.parseInt(input_card_row.getText());
			int card_col = Integer.parseInt(input_card_col.getText());
			int game_life = Integer.parseInt(input_game_life.getText());
			int game_time_limit = Integer.parseInt(input_game_life.getText());
			
			if(card_row > 8 || card_col > 8) {
				JOptionPane.showMessageDialog(null, "카드 수는 가로, 세로 각각 최대 8개까지 가능합니다.");
			}
			else if((card_row * card_col )%2 != 0) {
				JOptionPane.showMessageDialog(null, "카드 수의 가로 세로 곱은 짝수여야 합니다.");
			}
			else {
				Main.main_frame.getContentPane().removeAll();
				Main.main_frame.getContentPane().add(new PlayPage(card_row, card_col,game_life,game_time_limit,0,1));
				Main.main_frame.repaint();
			}
			
		}
		
	}
	class BackButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Music click_sound = new Music("click",false);
			click_sound.start();
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new ModePage());
			Main.main_frame.repaint();
		}
	}

}
