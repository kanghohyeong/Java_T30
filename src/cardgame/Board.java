package cardgame;

import java.util.Random;
import java.util.Scanner;

public class Board {
	int [] arr;
	int [][]card;
	int [][]secret;
	int life = 10;
	int row,col;
	public Board() {
		Scanner scan = new Scanner(System.in);
		System.out.print("enter a row:"); //row 입력받기
		this.row = scan.nextInt();
		System.out.print("enter a column:");//column 입력받기
		this.col = scan.nextInt(); //row*column이 짝수여야함
		
		arr=new int[row*col];
		card=new int[row][col];
		secret=new int[row][col];
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
	public void selectCard() throws InterruptedException {
		Scanner scan = new Scanner(System.in);
		String ans;
		
		
		System.out.printf("Select Card: ");
		ans = scan.nextLine();
		int y = (ans.charAt(1)-'0');
		int x = ans.charAt(0)-'a';
		while(secret[y][x] != 0) {
			System.out.println("card is already open");
			System.out.print("Select Card: ");
			ans = scan.nextLine();
			y = (ans.charAt(1)-'0');
			x = ans.charAt(0)-'a';
		}
		secret[y][x] = card[y][x];
		printSecret();
		
		
		System.out.printf("Select Card: ");
		ans = scan.nextLine();
		int y2 = (ans.charAt(1)-'0');
		int x2 = ans.charAt(0)-'a';
		while(secret[y2][x2] != 0) {
			System.out.println("card is already open");
			System.out.print("Select Card: ");
			ans = scan.nextLine();
			y2 = (ans.charAt(1)-'0');
			x2 = ans.charAt(0)-'a';
		}
		secret[y2][x2] = card[y2][x2];
		
		if(secret[y][x] != secret[y2][x2]) {
			life--;
			if(life<=0) return;
			printSecret();
			Thread.sleep(2000);
			secret[y][x] =0;
			secret[y2][x2]=0;
		}
	}
	
	
	
	public void printBoard() {
		System.out.printf("    ");
		for(int i=0;i<col;i++) {
			System.out.print((char)(i+97)+"  ");
		}
		System.out.printf("\n");
		
		for(int i = 0; i < row; i++) {
			System.out.printf(i + " ");
			for (int j = 0; j < col; j++) {
				System.out.printf("%3d",card[i][j]);
			}
			System.out.printf("\n");
			
		}
		System.out.printf("life: "+life);
		System.out.printf("\n");
	}
	public void printSecret() {
		System.out.printf("    ");
		for(int i=0;i<col;i++) {
			System.out.print((char)(i+97)+"  ");
		}
		System.out.printf("\n");
		
		
		 
		for(int i = 0; i < row; i++) {
			System.out.printf(i + " ");
			for (int j = 0; j < col; j++) {
				System.out.printf("%3d",secret[i][j]);
			}
			System.out.printf("\n");
			
		}
		System.out.printf("life: "+life);
		System.out.printf("\n");
	}
	
	public boolean isFinish() {
		boolean check = true;
		for(int i=0; i<row;i++) {
			for(int j=0; j<col;j++) {
				if(secret[i][j] == 0) check = false; //0이 하나라도 있으면 안끝냄
			}
		}
		if (life <= 0) {
			System.out.println("Dead");
			check = true;
			
		}
		return check;
	}
	
	
	
	
	
}
