package cardgame;

import java.util.Random;
import java.util.Scanner;

public class EasyBoard {
	int [] arr;
	int [][]card;
	int [][]secret;
	int life = 10;
	int hint = 2;
	public EasyBoard() {
		arr=new int[16];
		card=new int[4][4];
		secret=new int[4][4];
	}
	public void randNumber()
	{
		int check=0;
		int k=0;
		Random rand=new Random();
		for(int i=0;i<arr.length;i++)
		{
			arr[i]=rand.nextInt(8)+1;
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
		for(int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				card[i][j] = arr[k++];
			}
		}
	}
	public void selectCard() throws InterruptedException {
		Scanner scan = new Scanner(System.in);
		String ans;
		
		
		System.out.printf("Select Card: ");
		ans = scan.nextLine();
		if(ans.contentEquals("hint")) {
			Hint();
			return;
		}
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
		System.out.printf("    a  b  c  d\n");
		 
		for(int i = 0; i < 4; i++) {
			System.out.printf(i + " ");
			for (int j = 0; j < 4; j++) {
//				System.out.print("*  ");
				System.out.printf("%3d",card[i][j]);
			}
			System.out.printf("\n");
			
		}
		System.out.printf("life: "+life);
		System.out.printf("\n");
		System.out.printf("hint: "+hint);
		System.out.printf("\n");
	}
	public void printSecret() {
		System.out.printf("    a  b  c  d\n");
		 
		for(int i = 0; i < 4; i++) {
			System.out.printf(i + " ");
			for (int j = 0; j < 4; j++) {
//				System.out.print("*  ");
				System.out.printf("%3d",secret[i][j]);
			}
			System.out.printf("\n");
			
		}
		System.out.printf("hint: "+hint);
		System.out.printf("\n");
	}
	
	public boolean isFinish() {
		boolean check = true;
		for(int i=0; i<4;i++) {
			for(int j=0; j<4;j++) {
				if(secret[i][j] == 0) check = false; //0이 하나라도 있으면 안끝냄
			}
		}
		if (life <= 0) {
			System.out.println("Dead");
			check = true;
			
		}
		return check;
	}
	
	public void Hint() {
		Scanner scan = new Scanner(System.in);
		String ans;
		int y2 = 0,x2 =0;
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
		
		for(int i=0; i<4;i++) {
			for(int j=0; j<4;j++) {
				if(!(i==y && j==x) && card[y][x] == card[i][j]) {
					y2=i;
					x2=j;
				}
			}
		}
		secret[y2][x2] = card[y2][x2];
		hint--;
	}
	
	
	
}
