package cardgame;

import java.util.Random;
import java.util.Scanner;

public class MainBoard {
	int [] arr;
	int [][]card;
	int [][]secret;
	int life;
	int time_limit;
	int row,col;
	boolean result; //true win / false lose
	
	public MainBoard(int row, int col, int life, int time_limit){
		this.row = row;
		this.col = col;
		this.life = life;
		this.time_limit = time_limit;
		this.arr = new int[row*col];
		this.card = new int[row][col];
		this.secret = new int[row][col];
	}
	public void randNumber()
	{
		int check=0;
		int k=0;
		Random rand=new Random();
		for(int i=0;i<arr.length;i++)
		{
			arr[i]=rand.nextInt(row*col/2)+1;
			for(int x=0;x<i;x++)
			{
				if(arr[i]==arr[x])
					check++;
			}
			if(check==2)
			{
				check=0;
				i--;
				continue;
			}
			
			check=0;
		}
		for(int i=0;i<row;i++) {
			for (int j=0;j<col;j++) {
				card[i][j] = arr[k++];
			}
		}
	}
	public boolean isFinish(int time) {
//		boolean check = true;
		if (life <= 0 || time <= 0) {
			System.out.println("Dead");
			this.result = false;
			return true;
			
		}
		for(int i=0; i<row;i++) {
			for(int j=0; j<col;j++) {
				if(secret[i][j] == 0) return false; //0이 하나라도 있으면 안끝냄
			}
		}
		System.out.println("Success");
		this.result = true;
		return true;
	}
	
	public void selectCard(int row, int col) {
		secret[row][col] = card[row][col];
	}
	
	public boolean checkOpenCard(int r1, int c1, int r2, int c2) {
		if(secret[r1][c1] != secret[r2][c2]) {
			life--;
			secret[r1][c1] = 0;
			secret[r2][c2] = 0;
			return false;
		}
		return true;
	}
	
	public int[][] getCard() {
		return this.card;
	}
	public int getLife() {
		return this.life;
	}
	public int getTimeLimit() {
		return this.time_limit;
	}
	public boolean getResult() {
		return this.result;
	}
}
