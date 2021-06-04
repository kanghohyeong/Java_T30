package mm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import gamesocket.EchoClient;
import gamesocket.InsertClient;
import gamesocket.PrintClient;

public class EndingPage extends JPanel{
	private static Image successpage_background = new ImageIcon("./Image/successpage.png").getImage();
	private static Image failpage_background = new ImageIcon("./Image/failpage.png").getImage();
	private ImageIcon home_button_image = new ImageIcon("./Image/homebutton.png");
	private ImageIcon home_button_f_image = new ImageIcon("./Image/homebutton_f.png");
	JLabel background_label;
	
	JLabel label_result = new JLabel();
	JLabel label_connection = new JLabel("서버 연결중");
//	JTextArea score_area = new JTextArea();
	JTable score_table;
	JScrollPane scroll = new JScrollPane();
	int mode;
	Timer refresh_timer;
	
	EndingPage(boolean result,int life, int time, int card, int level, int mode){
		this.mode = mode; // 0 랭킹 1 커스텀
		this.setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		this.setLayout(null);
		JButton home_button = new JButton(home_button_image);
		home_button.setContentAreaFilled(false);
		home_button.setRolloverIcon(home_button_f_image);
		home_button.addActionListener(new HomeButtonClickListener());
		
		if(result) { // win
			label_result.setText("승리!");
			background_label = new JLabel() {
				public void paintComponent(Graphics g) {
					g.drawImage(EndingPage.successpage_background,0,0,null);
					setOpaque(false);
					super.paintComponent(g);
				}
			};
			background_label.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
			this.add(background_label);
		}
		else {
			label_result.setText("패배!");
			background_label = new JLabel() {
				public void paintComponent(Graphics g) {
					g.drawImage(EndingPage.failpage_background,0,0,null);
					setOpaque(false);
					super.paintComponent(g);
				}
			};
			background_label.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
			this.add(background_label);
		}
		
		label_connection.setBounds(100, 100, 400, 100);
		label_result.setBounds(100,200,400,100);
		scroll.setBounds(300, 100, 800, 400);
		home_button.setBounds(560, 510, 270,100);
		
		scroll.setOpaque(true);
		scroll.setBackground(new Color(0,0,0,0));
		
		Font score_font = new Font("맑은 고딕", Font.ITALIC, 30);
		
		scroll.setVisible(false);
		label_connection.setVisible(false);
		this.add(label_connection);
		this.add(scroll);
		this.add(label_result);
		this.add(home_button);
		
		this.setComponentZOrder(background_label, this.getComponentCount()-1);
		
		ActionListener refresh_game = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Main.main_frame.revalidate();
				Main.main_frame.repaint();
//				System.out.println(time);
			}
		};
		refresh_timer = new Timer(100, refresh_game );
		refresh_timer.start();
		
		if(mode == 0) { // 랭킹모드
			label_connection.setVisible(true);
			if(result) { // 승리시
				insertScoreBoard(card, level, ModePage.player_name, time, life);
			}
			else { //패배시
				printScoreBoard(card,level);
			}
		}
		
		
	}
	
	void insertScoreBoard(int card, int level, String player, int time, int life) {
		InsertClient ic = new InsertClient(card,level,player,time,life);
		ic.start();
		new Thread() {
			public void run() {
				try {
					ic.join();
					if(ic.getConnectionState() != 2) { //연결 에러
						label_connection.setText("서버에 연결할 수 없습니다.");
					}
					else {
						label_connection.setVisible(false);
						String score_board = ic.getResponseMessage();
						String table_header[] = {"랭킹","플레이어", "남은 시간", "남은 목숨"};
						String[] score_list = score_board.split("\n");
						Object[][] table_data = new Object[score_list.length][];
						int idx=0;
						for(String score : score_list) {
							table_data[idx] = new Object[4];
							table_data[idx][0] = idx+1;
							table_data[idx][1] = score.split(" ")[0];
							table_data[idx][2] = score.split(" ")[1];
							table_data[idx][3] = score.split(" ")[2];
							idx++;
						}
						score_table = new JTable(table_data, table_header){
							public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
								JComponent component = (JComponent) super.prepareRenderer(renderer, row, column);
								
								if(row == ic.getRank()) {
									component.setBackground(Color.RED);
								}
								else {
									component.setBackground(new Color(0,0,0,0));
//									component.setOpaque(false);
								}
								
								return component;
							
							}
						};
						
						scroll.setViewportView(score_table);
						score_table.setFillsViewportHeight(true);
						score_table.setRowHeight(20);
						score_table.setBackground(new Color(0,0,0,0));
						score_table.setOpaque(true);
						score_table.setEnabled(false);
						score_table.setForeground(Color.white);
						Font table_font = new Font("HY견명조",Font.PLAIN,20);
						score_table.setFont(table_font);
						
						scroll.setVisible(true);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
	void printScoreBoard(int card, int level) {
		PrintClient pc = new PrintClient(card,level);
		pc.start();
		new Thread() {
			public void run() {
				try {
					pc.join();
					if(pc.getConnectionState() != 2) { // 연결 에러
						label_connection.setText("서버에 연결할 수 없습니다.");
					}
					else {
						label_connection.setVisible(false);
						String score_board = pc.getResponseMessage();
						String table_header[] = {"랭킹","플레이어", "남은 시간", "남은 목숨"};
						String[] score_list = score_board.split("\n");
						Object[][] table_data;
						int idx=0;
						if(score_board.length() > 2) {
							table_data = new Object[score_list.length][];
							for(String score : score_list) {
								table_data[idx] = new Object[4];
								table_data[idx][0] = idx+1;
								table_data[idx][1] = score.split(" ")[0];
								table_data[idx][2] = score.split(" ")[1];
								table_data[idx][3] = score.split(" ")[2];
								idx++;
							}
						}
						else {
							table_data = new Object[1][];
							table_data[0] = new Object[4];
							table_data[0][0] = "";
							table_data[0][1] = "";
							table_data[0][2] = "";
							table_data[0][3] = "";
							
						}
						score_table = new JTable(table_data, table_header);
						scroll.setViewportView(score_table);
						score_table.setFillsViewportHeight(true);
						score_table.setRowHeight(20);
						score_table.setBackground(new Color(0,0,0,0));
						score_table.setEnabled(false);
						score_table.setOpaque(true);
						Font table_font = new Font("HY견명조",Font.PLAIN,20);
						score_table.setFont(table_font);
						score_table.setForeground(Color.white);
						
						scroll.setVisible(true);
						
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}.start();
	}
	
	class HomeButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			refresh_timer.stop();
			
			Main.background_music.changeMusic("silence_01", true);
			Main.main_frame.getContentPane().removeAll();
			Main.main_frame.getContentPane().add(new HomePage());
			Main.main_frame.repaint();
			
		}
		
	}
	

}
