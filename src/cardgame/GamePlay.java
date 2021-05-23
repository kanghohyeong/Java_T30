package cardgame;

import java.util.Scanner;

public class GamePlay {
	public GamePlay() throws InterruptedException {
		Scanner scan = new Scanner(System.in);
		
		System.out.println(" (1)easy, (2)normal, (3)hard, (4)select difficulty, (5)exit");
			System.out.print("select:");
		int ans= scan.nextInt();
		switch(ans) {
		case 1:
			EasyBoard egame = new  EasyBoard();
			egame.randNumber();
			egame.printBoard();
			//delay
			Thread.sleep(2000);
			while(!egame.isFinish()) {
				egame.printSecret();
				egame.selectCard();
			}
			break;
		case 2:
			NormalBoard ngame = new  NormalBoard();
			ngame.randNumber();
			ngame.printBoard();
			//delay
			Thread.sleep(2000);
			while(!ngame.isFinish()) {
				ngame.printSecret();
				ngame.selectCard();
			}
			break;
		case 3:
			HardBoard hgame = new  HardBoard();
			hgame.randNumber();
			hgame.printBoard();
			//delay
			Thread.sleep(2000);
			while(!hgame.isFinish()) {
				hgame.printSecret();
				hgame.selectCard();
			}
			break;
		case 4:
			Board game = new  Board();
			game.randNumber();
			game.printBoard();
			//delay
			Thread.sleep(2000);
			while(!game.isFinish()) {
				game.printSecret();
				game.selectCard();
			}
			break;
		case 5:
			return;
		}
	}
	
	
}
