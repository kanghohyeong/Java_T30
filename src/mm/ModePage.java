package mm;

import java.awt.event.*;
import javax.swing.*;

public class ModePage extends JPanel{
	
	JButton ranking_button;
	JButton custom_button;
	JButton four_four_button;
	JButton six_six_button;
	JButton eight_eight_button;
	JButton easy_button;
	JButton normal_button;
	JButton hard_button;
	JButton hell_button;
	
	int card_level;
	int play_level;
	
	ModePage(){
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		this.setLayout(null);
		
		ranking_button = new JButton("랭킹 모드");
		custom_button = new JButton("커스텀 모드");
		four_four_button = new JButton("4X4");
		six_six_button = new JButton("6X6");
		eight_eight_button = new JButton("8X8");
		easy_button = new JButton("쉬운 난이도");
		normal_button = new JButton("보통 난이도");
		hard_button = new JButton("어려운 난이도");
		hell_button = new JButton("지옥 난이도");
		
		ranking_button.setBounds(200,300,400,100);
		custom_button.setBounds(700,300,400,100);
		four_four_button.setBounds(100, 300, 300, 100);
		six_six_button.setBounds(500, 300, 300, 100);
		eight_eight_button.setBounds(900, 300, 300, 100);
		easy_button.setBounds(200, 200, 400, 100);
		normal_button.setBounds(700, 200, 400, 100);
		hard_button.setBounds(200, 400, 400, 100);
		hell_button.setBounds(700, 400, 400, 100);
		
		
		ranking_button.addActionListener(new RankingButtonClickListener());
		custom_button.addActionListener(new CustomButtonClickListener());
		four_four_button.addActionListener(new CardLevelButtonClickListener());
		six_six_button.addActionListener(new CardLevelButtonClickListener());
		eight_eight_button.addActionListener(new CardLevelButtonClickListener());
		easy_button.addActionListener(new PlayLevelButtonClickListener());
		normal_button.addActionListener(new PlayLevelButtonClickListener());
		hard_button.addActionListener(new PlayLevelButtonClickListener());
		hell_button.addActionListener(new PlayLevelButtonClickListener());
		
		four_four_button.setVisible(false);
		six_six_button.setVisible(false);
		eight_eight_button.setVisible(false);
		easy_button.setVisible(false);
		normal_button.setVisible(false);
		hard_button.setVisible(false);
		hell_button.setVisible(false);
		
		
		this.add(ranking_button);
		this.add(custom_button);
		this.add(four_four_button);
		this.add(six_six_button);
		this.add(eight_eight_button);
		this.add(easy_button);
		this.add(normal_button);
		this.add(hard_button);
		this.add(hell_button);
	}
	
	class RankingButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Music click_sound = new Music("click",false);
			click_sound.start();
			ranking_button.setVisible(false);
			custom_button.setVisible(false);
			four_four_button.setVisible(true);
			six_six_button.setVisible(true);
			eight_eight_button.setVisible(true);
		}
	}
	
	class CardLevelButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Music click_sound = new Music("click",false);
			click_sound.start();
			four_four_button.setVisible(false);
			six_six_button.setVisible(false);
			eight_eight_button.setVisible(false);
			easy_button.setVisible(true);
			normal_button.setVisible(true);
			hard_button.setVisible(true);
			hell_button.setVisible(true);
			
			String selected_button = ((JButton) e.getSource()).getText();
			if(selected_button.equals("4X4")) card_level =4;
			else if(selected_button.equals("6X6")) card_level = 6;
			else if(selected_button.equals("8X8")) card_level = 8;
		}
	}
	
	class PlayLevelButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Music click_sound = new Music("click",false);
			click_sound.start();
			String selected_button = ((JButton) e.getSource()).getText();
			if(selected_button.equals("쉬운 난이도")) play_level =1;
			else if(selected_button.equals("보통 난이도")) play_level = 2;
			else if(selected_button.equals("어려운 난이도")) play_level = 3;
			else if(selected_button.equals("지옥 난이도")) play_level = 4;
			
//			System.out.println(card_level);
//			System.out.println(play_level);
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new PlayPage(card_level, card_level,card_level*4/play_level,card_level*10/play_level));
			Main.main_frame.repaint();
		}
	}
	
	class CustomButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Music click_sound = new Music("click",false);
			click_sound.start();
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new SettingPage());
			Main.main_frame.repaint();
		}
	}

}
