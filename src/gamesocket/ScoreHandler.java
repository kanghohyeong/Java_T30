package gamesocket;

import java.io.*;
import java.util.ArrayList;

public class ScoreHandler {
	String score_file;
	InputStream fis;
	InputStreamReader isr;
	BufferedReader br;
	OutputStream fos;
	OutputStreamWriter osw;
	BufferedWriter bw;
	ArrayList<GameScore> score_board; 
	
	public ScoreHandler(int card, int level) {
		score_file = "./Score/"+String.valueOf(card)+String.valueOf(level)+".txt";
		score_board = new ArrayList<>();
		try {
			fis = new FileInputStream(score_file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			String line;
			while((line = br.readLine())!=null) {
				if(line.length() < 2) break;
				String cur_player = line.split(" ")[0];
				int cur_time = Integer.parseInt(line.split(" ")[1]);
				int cur_life = Integer.parseInt(line.split(" ")[2]);
				
				score_board.add(new GameScore(cur_player,cur_time, cur_life));
			}
			
			br.close(); isr.close(); fis.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public ScoreHandler(String game_level) {
		score_file = "./Score/"+game_level+".txt";
		score_board = new ArrayList<>();
		try {
			fis = new FileInputStream(score_file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			String line;
			while((line = br.readLine())!=null) {
				if(line.length() < 2) break;
				String cur_player = line.split(" ")[0];
				int cur_time = Integer.parseInt(line.split(" ")[1]);
				int cur_life = Integer.parseInt(line.split(" ")[2]);
				
				score_board.add(new GameScore(cur_player,cur_time, cur_life));
			}
			
			br.close(); isr.close(); fis.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<GameScore> getScoreBoard(){
		return this.score_board;
	}
	public String printScoreBoard() {
		String board = "";
		for(GameScore gs : score_board) {
			String cur_score = "";
			cur_score += gs.getPlayer() +" " +String.valueOf(gs.getTime())+ " " + String.valueOf(gs.getlife());
			board += cur_score;
			board += "\n";
		}
		return board;
	}
	public int insertNewScore(String player, int time, int life) {
		for(int i = 0; i < score_board.size(); i++) {
			if(score_board.get(i).getTime()<time) {
				score_board.add(i, new GameScore(player,time,life));
				return i;
			}
			else if(score_board.get(i).getTime()==time) {
				if(score_board.get(i).getlife()<=life) {
					score_board.add(i,new GameScore(player,time,life));
					return i;
				}
			}
		}
		
		score_board.add(new GameScore(player,time,life));
		return score_board.size()-1;
	}
	public void saveScoreBoard() {
		try {
			fos = new FileOutputStream(score_file);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			
			for(GameScore gs : score_board) {
				String cur_score = "";
				cur_score += gs.getPlayer() +" " +String.valueOf(gs.getTime())+ " " + String.valueOf(gs.getlife());
				bw.write(cur_score);
				bw.newLine();
//				System.out.println(cur_score);
			}
			bw.flush();
			fos.close(); osw.close(); bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

class GameScore {
	String player;
	int time;
	int life;
	
	GameScore(String player, int time, int life){
		this.player = player;
		this.time = time;
		this.life = life;
	}
	
	int getTime() {
		return this.time;
	}
	int getlife() {
		return this.life;
	}
	String getPlayer() {
		return this.player;
	}
}