package mm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

import gamesocket.EchoClient;

public class ModePage extends JPanel{
	private static Image settingpage_background = new ImageIcon("./Image/settingpage.png").getImage();
	private ImageIcon rank_button_image = new ImageIcon("./Image/rankbutton.png");
	private ImageIcon rank_button_f_image = new ImageIcon("./Image/rankbutton_f.png");
	private ImageIcon custom_button_image = new ImageIcon("./Image/custombutton.png");
	private ImageIcon custom_button_f_image = new ImageIcon("./Image/custombutton_f.png");
	private ImageIcon four_button_image = new ImageIcon("./Image/44button.png");
	private ImageIcon four_button_f_image = new ImageIcon("./Image/44button_f.png");
	private ImageIcon six_button_image = new ImageIcon("./Image/66button.png");
	private ImageIcon six_button_f_image = new ImageIcon("./Image/66button_f.png");
	private ImageIcon eight_button_image = new ImageIcon("./Image/88button.png");
	private ImageIcon eight_button_f_image = new ImageIcon("./Image/88button_f.png");
	private ImageIcon easy_button_image = new ImageIcon("./Image/easybutton.png");
	private ImageIcon normal_button_image = new ImageIcon("./Image/normalbutton.png");
	private ImageIcon hard_button_image = new ImageIcon("./Image/hardbutton.png");
	private ImageIcon hell_button_image = new ImageIcon("./Image/hellbutton.png");
	JLabel background_label;
	
	JButton ranking_button;
	JButton custom_button;
	JButton four_four_button;
	JButton six_six_button;
	JButton eight_eight_button;
	JButton easy_button;
	JButton normal_button;
	JButton hard_button;
	JButton hell_button;
	BackButton back_button = new BackButton();
	
	JLabel loading_label;
	
	int card_level;
	int play_level;
	
	public static String player_name;
	
	ModePage(){
		
		player_name = "";
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		this.setOpaque(false);
		this.setLayout(null);
		
		background_label = new JLabel() {
			public void paintComponent(Graphics g) {
				g.drawImage(ModePage.settingpage_background,0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		background_label.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		this.add(background_label);
		
		ranking_button = new JButton(rank_button_image);
		custom_button = new JButton(custom_button_image);
		four_four_button = new JButton(four_button_image);
		six_six_button = new JButton(six_button_image);
		eight_eight_button = new JButton(eight_button_image);
		easy_button = new JButton(easy_button_image);
		normal_button = new JButton(normal_button_image);
		hard_button = new JButton(hard_button_image);
		hell_button = new JButton(hell_button_image);
		loading_label = new JLabel("서버 연결중");
		
		ranking_button.setBounds(100,250,410,180);
		custom_button.setBounds(750,250,410,180);
		four_four_button.setBounds(80, 150, 350, 435);
		six_six_button.setBounds(480, 150, 350, 435);
		eight_eight_button.setBounds(880, 150, 350, 435);
		easy_button.setBounds(250, 200, 270, 100);
		normal_button.setBounds(750, 200, 270, 100);
		hard_button.setBounds(250, 400, 270, 100);
		hell_button.setBounds(750, 400, 270, 100);
		loading_label.setBounds(700,300,400,100);
		
		ranking_button.setContentAreaFilled(false);
		ranking_button.setRolloverIcon(rank_button_f_image);
		custom_button.setContentAreaFilled(false);
		custom_button.setRolloverIcon(custom_button_f_image);
		four_four_button.setContentAreaFilled(false);
		four_four_button.setRolloverIcon(four_button_f_image);
		six_six_button.setContentAreaFilled(false);
		six_six_button.setRolloverIcon(six_button_f_image);
		eight_eight_button.setContentAreaFilled(false);
		eight_eight_button.setRolloverIcon(eight_button_f_image);
		easy_button.setContentAreaFilled(false);
		normal_button.setContentAreaFilled(false);
		hard_button.setContentAreaFilled(false);
		hell_button.setContentAreaFilled(false);
		
		Font text_font = new Font("HY견명조",Font.PLAIN,50);
		loading_label.setFont(text_font);
		loading_label.setForeground(Color.white);
		loading_label.setBackground(Color.black);
		loading_label.setOpaque(true);
		
		four_four_button.setName("4X4");
		six_six_button.setName("6X6");
		eight_eight_button.setName("8X8");
		easy_button.setName("easy");
		normal_button.setName("normal");
		hard_button.setName("hard");
		hell_button.setName("hell");
		
		ranking_button.addActionListener(new RankingButtonClickListener());
		custom_button.addActionListener(new CustomButtonClickListener());
		four_four_button.addActionListener(new CardLevelButtonClickListener());
		six_six_button.addActionListener(new CardLevelButtonClickListener());
		eight_eight_button.addActionListener(new CardLevelButtonClickListener());
		easy_button.addActionListener(new PlayLevelButtonClickListener());
		normal_button.addActionListener(new PlayLevelButtonClickListener());
		hard_button.addActionListener(new PlayLevelButtonClickListener());
		hell_button.addActionListener(new PlayLevelButtonClickListener());
		back_button.addActionListener(new BackButtonClickListener());
		
		four_four_button.setVisible(false);
		six_six_button.setVisible(false);
		eight_eight_button.setVisible(false);
		easy_button.setVisible(false);
		normal_button.setVisible(false);
		hard_button.setVisible(false);
		hell_button.setVisible(false);
		loading_label.setVisible(false);
		
		
		this.add(ranking_button);
		this.add(custom_button);
		this.add(four_four_button);
		this.add(six_six_button);
		this.add(eight_eight_button);
		this.add(easy_button);
		this.add(normal_button);
		this.add(hard_button);
		this.add(hell_button);
		this.add(loading_label);
		this.add(back_button);
		this.setComponentZOrder(background_label, this.getComponentCount()-1);
	}
	
	class RankingButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Music click_sound = new Music("click",false);
			click_sound.start();
			ranking_button.setVisible(false);
			custom_button.setVisible(false);
			loading_label.setVisible(true);
//			four_four_button.setVisible(true);
//			six_six_button.setVisible(true);
//			eight_eight_button.setVisible(true);
			player_name = "";
			
			EchoClient ec = new EchoClient();
			ec.start();
			
			new Thread() {
				public void run() {
					try {
						ec.join();
						if(ec.getConnectionState() != 2) { //연결 애러
							int result = JOptionPane.showConfirmDialog(null, "서버와 연결할 수 없습니다.\n계속하시겠습니까?\n(점수는 저장되지 않습니다.)" ,"서버오류",JOptionPane.YES_NO_OPTION);
							if(result == JOptionPane.NO_OPTION || result == JOptionPane.CLOSED_OPTION) {
								loading_label.setVisible(false);
								ranking_button.setVisible(true);
								custom_button.setVisible(true);
							}
							else {
								loading_label.setVisible(false);
								four_four_button.setVisible(true);
								six_six_button.setVisible(true);
								eight_eight_button.setVisible(true);
								back_button.removeActionListener(back_button.getActionListeners()[0]);
								back_button.addActionListener(new BackButtonToMode());
							}
						}
						else {
							do {
								player_name = JOptionPane.showInputDialog("플레이어명을 입력하세요(8글자 이내)");
								if(player_name == null) {
									loading_label.setVisible(false);
									ranking_button.setVisible(true);
									custom_button.setVisible(true);
									break;
								}
							}while(player_name.contains("\\")||player_name.length()==0||player_name.length()>8);
							
							if(player_name != null) {
								loading_label.setVisible(false);
								four_four_button.setVisible(true);
								six_six_button.setVisible(true);
								eight_eight_button.setVisible(true);
								back_button.removeActionListener(back_button.getActionListeners()[0]);
								back_button.addActionListener(new BackButtonToMode());
							}
						}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
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
			back_button.removeActionListener(back_button.getActionListeners()[0]);
			back_button.addActionListener(new BackButtonToLevel());
			
			
			String selected_button = ((JButton) e.getSource()).getName();
			if(selected_button.equals("4X4")) card_level =4;
			else if(selected_button.equals("6X6")) card_level = 6;
			else if(selected_button.equals("8X8")) card_level = 8;
		}
	}
	
	class PlayLevelButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Music click_sound = new Music("click",false);
			click_sound.start();
			String selected_button = ((JButton) e.getSource()).getName();
			if(selected_button.equals("easy")) play_level =1;
			else if(selected_button.equals("normal")) play_level = 2;
			else if(selected_button.equals("hard")) play_level = 3;
			else if(selected_button.equals("hell")) play_level = 4;
		
			
//			System.out.println(card_level);
//			System.out.println(play_level);
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new PlayPage(card_level, card_level,card_level*4/play_level,card_level*10/play_level,play_level,0));
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
	
	class BackButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Music click_sound = new Music("click",false);
			click_sound.start();
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new HomePage());
			Main.main_frame.repaint();
		}
	}
	
	class BackButtonToMode implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			four_four_button.setVisible(false);
			six_six_button.setVisible(false);
			eight_eight_button.setVisible(false);
			ranking_button.setVisible(true);
			custom_button.setVisible(true);
		}
	}
	
	class BackButtonToLevel implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			four_four_button.setVisible(true);
			six_six_button.setVisible(true);
			eight_eight_button.setVisible(true);
			easy_button.setVisible(false);
			normal_button.setVisible(false);
			hard_button.setVisible(false);
			hell_button.setVisible(false);
			back_button.removeActionListener(back_button.getActionListeners()[0]);
			back_button.addActionListener(new BackButtonToMode());
		}
	}
	
	

}
