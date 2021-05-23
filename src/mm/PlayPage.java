package mm;

import cardgame.*;

import java.awt.event.*;
import javax.swing.*;


public class PlayPage extends JPanel{
	
	MainBoard game_board;
	JPanel board_panel = new JPanel();
	JLabel game_life;
	JLabel game_time;
	int card_row;
	int card_col;
	Card[][] card_deck;
	Card[][] secret_deck;
	public static final int GAME_SCREEN_WIDTH = 1000;
	public static final int GAME_SCREEN_HEIGHT = 500;
	public static int game_state = 0; //0 초기/ 1 카드선택1/ 2 카드선택2/
	public static int[] selected_card_tmp1 = new int[2]; //카드 선택 버퍼
	public static int[] selected_card_tmp2 = new int[2]; //카드 선택 버퍼
	
	int time=0;
	
	PlayPage(int row, int col, int life, int time_limit){
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		this.setLayout(null);
		
		board_panel.setBounds(100, 100, GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);
		board_panel.setLayout(null);
		this.add(board_panel);
		
		this.card_row = row;
		this.card_col = col;
		this.card_deck = new Card[row][col];
		
		//게임 초기화
		game_board = new MainBoard(row,col, life, time_limit);
		game_board.randNumber();
		
		game_life = new JLabel("life "+String.valueOf(game_board.getLife()));
		game_life.setBounds(100, 5, 100, 50);
		this.add(game_life);
		
		game_time = new JLabel();
		game_time.setBounds(300, 5, 100, 50);
		this.add(game_time);

		//게임 시작
		new GamePlay().start();
		
		
		
	}
	
	void initCardDeck(int[][] card) {
		for(int i = 0; i < this.card_row; i++) {
			for(int j = 0; j < this.card_col; j++) {
				this.card_deck[i][j] = new Card(card[i][j], i, j);
				this.card_deck[i][j].setBounds(i*GAME_SCREEN_WIDTH/(this.card_row+1),j*GAME_SCREEN_HEIGHT/(this.card_col+1) ,GAME_SCREEN_WIDTH/(this.card_row+1) , GAME_SCREEN_HEIGHT/(this.card_col+1));
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
	
	void secretAllCard() {
		for(int i = 0; i < this.card_row; i++) {
			for(int j = 0; j < this.card_col; j++) {
				if(this.card_deck[i][j].getState() != 2) { // 사라진 카드 아니면 
					this.card_deck[i][j].setState(0);
				}
			}
		}	
	}
	
	void discardCard(int row, int col) {
		this.card_deck[row][col].setState(2);
		board_panel.remove(this.card_deck[row][col]);
	}
	
	class GamePlay extends Thread{
//		public int game_state=0; //0 초기/ 1 카드선택1/ 2 카드선택2/

		
		public void run() {
			//카드 댁 초기화
			initCardDeck(game_board.getCard());
			//일정 시간동안 카드 공개
			printCardDeck();
			float cur_time = Main.time;
			while(true) {
				if(cur_time+2 < Main.time) {
					break;
				}
				else {
					System.out.println(Main.time);
				}
			}
			// 모든 카드 비공개
			secretAllCard();
			//게임 시작
			game_time.setText("Time "+ game_board.getTimeLimit());
			ActionListener game_time_checker = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					game_board.setTimeLimit(game_board.getTimeLimit()-1);
					game_time.setText("Time "+ game_board.getTimeLimit());
				}
			};
			Timer game_timer = new Timer(1000, game_time_checker);
			game_timer.start();
			while(!game_board.isFinish()) {
				if(game_state == 0) {
					printCardDeck();
				}
				else if(game_state == 1) {
					game_board.selectCard(selected_card_tmp1[0], selected_card_tmp1[1]);
					printCardDeck();
				}
				else if(game_state == 2) {
					printCardDeck();
					cur_time = Main.time;
					while(true) {
						if(cur_time+1 < Main.time) {
							break;
						}
						else {
							System.out.println(Main.time);
						}
					}
					game_board.selectCard(selected_card_tmp2[0], selected_card_tmp2[1]);
					if(game_board.checkOpenCard(selected_card_tmp1[0], selected_card_tmp1[1],selected_card_tmp2[0], selected_card_tmp2[1])) {
						discardCard(selected_card_tmp2[0], selected_card_tmp2[1]);
						discardCard(selected_card_tmp1[0], selected_card_tmp1[1]);
					}
					else {
						secretAllCard();
						game_life.setText("life "+String.valueOf(game_board.getLife()));
					}
					game_state = 0;
					
				}
//				printCardDeck();
			}
			game_timer.stop();
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new EndingPage(game_board.getResult()));
		}
	}

	
	
}

class Card extends JButton{
	int value;
	int row;
	int col;
	int state; //0 secret 1 show 2 discard 
	
	Card(int value, int row, int col){
		this.value = value;
		this.state = 1;
		this.row = row;
		this.col = col;
		this.addMouseListener(new CardMouseEv());
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
	
	
	class CardMouseEv implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			Card cur_button = (Card)e.getSource();
			if(cur_button.getState() !=1) {
				cur_button.setState(1);
				PlayPage.game_state++;
				if(PlayPage.game_state == 1) {
					PlayPage.selected_card_tmp1[0] = cur_button.row;
					PlayPage.selected_card_tmp1[1] = cur_button.col;
				}
				else if(PlayPage.game_state == 2) {
					PlayPage.selected_card_tmp2[0] = cur_button.row;
					PlayPage.selected_card_tmp2[1] = cur_button.col;
				}
				
				
			}
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

