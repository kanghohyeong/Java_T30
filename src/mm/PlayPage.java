package mm;

import cardgame.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;


public class PlayPage extends JPanel{
	
	private static Image playpage_backgound = new ImageIcon("./Image/playpage.png").getImage();
	
	MainBoard game_board;
	static JPanel board_panel = new JPanel();
	static JLabel background_panel;
	JLabel game_life;
	JLabel game_time;
	JLabel game_remain;
	static int card_row;
	static int card_col;
	Card[][] card_deck;
	Card[][] secret_deck;
	public static final int GAME_SCREEN_WIDTH = 900;
	public static final int GAME_SCREEN_HEIGHT = 600;
	public static volatile int game_state = 0; //0 초기/ 1 카드선택1/ 2 카드선택2/
	public static Queue<int[]> selected_card_queue = new LinkedList<>(); //카드선택 큐
	public static Timer refresh_timer;
	static int mode;
	static int level;
	int time=0;
	
	PlayPage(int row, int col, int life, int time_limit,int level, int mode){
		this.mode= mode;//게임 모드 0 랭킹 1 커스텀
		this.level = level; 
		
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		this.setOpaque(false);
		this.setLayout(null);
		
		background_panel = new JLabel() {
			public void paintComponent(Graphics g) {
				g.drawImage(PlayPage.playpage_backgound,0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
//			public void paint(Graphics g) {
//				g.drawImage(PlayPage.playpage_backgound,0,0,null);
//			}
		};
		background_panel.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		this.add(background_panel);
		
		board_panel.setBounds(300, 60, GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);
		board_panel.setLayout(null);
		board_panel.setOpaque(false);
//		board_panel.setLayout(new GridLayout(row,col));
		this.add(board_panel);
		
		this.card_row = row;
		this.card_col = col;
		this.card_deck = new Card[row][col];
		
		
		
		//게임 초기화
		game_board = new MainBoard(row,col, life, time_limit);
		game_board.randNumber();
		
		game_life = new JLabel("목숨 : "+String.valueOf(game_board.getLife()));
		game_life.setBounds(100, 100, 200, 50);
		Font status_font = new Font("HY견명조", Font.PLAIN, 30);
		game_life.setFont(status_font);
		game_life.setForeground(new Color(188,38,38));
		this.add(game_life);
		
		game_time = new JLabel("시간 : "+ game_board.getTimeLimit());
		game_time.setBounds(100, 200, 200, 50);
		game_time.setFont(status_font);
		game_time.setForeground(new Color(188,38,38));
		this.add(game_time);
		
		game_remain = new JLabel("카드 : "+String.valueOf(game_board.getRamainCard()*2));
		game_remain.setBounds(100, 300, 200, 50);
		game_remain.setFont(status_font);
		game_remain.setForeground(new Color(188,38,38));
		this.add(game_remain);
		
		this.setComponentZOrder(background_panel, this.getComponentCount()-1);

		//ㅇ음악
		Main.background_music.changeMusic("action_01", true);
		
		//게임 시작
		new GamePlay().start();
		
		
		ActionListener refresh_game = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.main_frame.revalidate();
				Main.main_frame.repaint();
//				System.out.println(time);
			}
		};
		refresh_timer = new Timer(100, refresh_game );
		refresh_timer.start();
		
		
	}
	
//	public void paint(Graphics g) {
//		g.drawImage(playpage_backgound, 0,0, null);
//		paintComponents(g);
//	}
	
	void initCardDeck(int[][] card) {
		for(int i = 0; i < this.card_row; i++) {
			for(int j = 0; j < this.card_col; j++) {
				this.card_deck[i][j] = new Card(card[i][j], i, j);
				this.card_deck[i][j].setBounds(j*GAME_SCREEN_WIDTH/(this.card_col),i*GAME_SCREEN_HEIGHT/(this.card_row) ,GAME_SCREEN_WIDTH/(this.card_col) , GAME_SCREEN_HEIGHT/(this.card_row));
//				this.card_deck[i][j].setBounds(j*GAME_SCREEN_WIDTH/(this.card_col),i*GAME_SCREEN_HEIGHT/(this.card_row) ,GAME_SCREEN_HEIGHT/(this.card_row)*2/3 , GAME_SCREEN_HEIGHT/(this.card_row));
				board_panel.add(this.card_deck[i][j]);
			}
		}	
	}
	
	void printCardDeck() {
		for(int i = 0; i < this.card_row; i++) {
			for(int j = 0; j < this.card_col; j++) {
				if(this.card_deck[i][j].getState() == 0) { //secret
					this.card_deck[i][j].setText("0");
				}
				else if(this.card_deck[i][j].getState() == 1) { //show
					this.card_deck[i][j].setText(String.valueOf(this.card_deck[i][j].getValue()));
				}
				else {
					continue;
				}
				
			}
		}	
	}
	void secretCard(int row, int col ) {
		this.card_deck[row][col].setState(0);
		this.card_deck[row][col].setCardBack();
	}
	
	void secretAllCard() {
		for(int i = 0; i < this.card_row; i++) {
			for(int j = 0; j < this.card_col; j++) {
				if(this.card_deck[i][j].getState() != 2) { // 사라진 카드 아니면 
					secretCard(i,j);
				}
			}
		}	
	}
	
	
	void discardCard(int row, int col) {
		this.card_deck[row][col].setState(2);
		board_panel.remove(this.card_deck[row][col]);
//		this.card_deck[row][col].setVisible(false);
	}
	
	void discardAllCard() {
		for(int i = 0; i < this.card_row; i++) {
			for(int j = 0; j < this.card_col; j++) {
				if(this.card_deck[i][j].getState() != 2) { // 사라진 카드 아니면 
					discardCard(i,j);
				}
			}
		}	
	}
	
	class GamePlay extends Thread{
		
		public void run() {
			//카드 댁 초기화
			initCardDeck(game_board.getCard());
			//일정 시간동안 카드 공개
//			float cur_time = Main.time;
//			while(true) {
//				if(cur_time+2 < Main.time) {
//					break;
//				}
//				else {
//					System.out.println(Main.time);
//				}
//			}
			try {
				this.sleep(2000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			// 모든 카드 비공개
			secretAllCard();
			//게임 시작
			ActionListener game_time_checker = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					game_board.setTimeLimit(game_board.getTimeLimit()-1);
					game_time.setText("시간 : "+ game_board.getTimeLimit());
				}
			};
			Timer game_timer = new Timer(1000, game_time_checker);
			game_timer.start();
			while(!game_board.isFinish()) {

				if(game_state == 2) {
					new Thread() {
						public void run() {
							try {
								this.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							synchronized(selected_card_queue) {
								int[] first_card = selected_card_queue.poll();
								int[] second_card = selected_card_queue.poll();
								game_board.selectCard(first_card[0], first_card[1]);
								game_board.selectCard(second_card[0], second_card[1]);
								if(game_board.checkOpenCard(first_card[0], first_card[1],second_card[0], second_card[1])) {
									Music correct_sound = new Music("correct", false);
									correct_sound.start();
									discardCard(first_card[0], first_card[1]);
									discardCard(second_card[0], second_card[1]);
									game_remain.setText("카드 : "+String.valueOf(game_board.getRamainCard()*2));
								}
								else {
									Music wrong_sound = new Music("wrong",false);
									wrong_sound.start();
									secretCard(first_card[0], first_card[1]);
									secretCard(second_card[0], second_card[1]);
									game_life.setText("life "+String.valueOf(game_board.getLife()));
								}
							}
							
							
						}
					}.start();
					game_state = 0;
				}
			}
			game_timer.stop();
			refresh_timer.stop();
			discardAllCard();
			
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new EndingPage(game_board.getResult(),game_board.getLife(),game_board.getTimeLimit(),game_board.getCardRow(),level,mode));
			Main.main_frame.repaint();
		}
	}

	
	
}

class Card extends JButton{
	private static ImageIcon card_back_image = new ImageIcon("./Image/card_deck_1/back.jpg");
	private ImageIcon card_front_image;
	int value;
	int row;
	int col;
	int state; //0 secret 1 show 2 discard 

	static {
		Image card_back_origin = card_back_image.getImage();
		Image card_back_resize = card_back_origin.getScaledInstance(PlayPage.GAME_SCREEN_WIDTH/PlayPage.card_col, PlayPage.GAME_SCREEN_HEIGHT/PlayPage.card_row, Image.SCALE_FAST);
		card_back_image = new ImageIcon(card_back_resize); 
	}
	
	Card(int value, int row, int col){
		this.value = value;
		this.state = 1;
		this.row = row;
		this.col = col;
		
		this.setContentAreaFilled(false);
		card_front_image = new ImageIcon("./Image/card_deck_1/("+String.valueOf(value)+").jpg");
		Image card_front_origin = card_front_image.getImage();
		Image card_front_resize = card_front_origin.getScaledInstance(PlayPage.GAME_SCREEN_WIDTH/PlayPage.card_col, PlayPage.GAME_SCREEN_HEIGHT/PlayPage.card_row, Image.SCALE_FAST);
		card_front_image = new ImageIcon(card_front_resize); 
		
//		this.addMouseListener(new CardMouseEv());
		this.addActionListener(new CardActionEv());
		this.setIcon(card_front_image);
		
//		this.setIcon(new ImageIcon(card_back_resize));
//		this.setContentAreaFilled(true);
//		this.setBorderPainted(false);
	}
	
	void setCardBack() {
		this.setIcon(card_back_image);
	}
	
	void setCardFront() {
		this.setIcon(card_front_image);
	}
	
	int getValue() {
		return this.value;
	}
	
	void setState(int state) {
		this.state = state;
	}
	
	int getState() {
		return this.state;
	}
	

	class CardActionEv implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Card cur_button = (Card)e.getSource();
			if(cur_button.getState() !=1) {
				Music click_sound = new Music("click",false);
				click_sound.start();
				cur_button.setIcon(card_front_image);
				cur_button.setState(1);
				PlayPage.game_state++;
				if(PlayPage.game_state == 1) {
					int[] cur_card = {cur_button.row, cur_button.col};
					PlayPage.selected_card_queue.add(cur_card);
				}
				else if(PlayPage.game_state == 2) {
					int[] cur_card = {cur_button.row, cur_button.col};
					PlayPage.selected_card_queue.add(cur_card);
				}
			}
		
		}
	}
	class CardMouseEv implements MouseListener{
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}

