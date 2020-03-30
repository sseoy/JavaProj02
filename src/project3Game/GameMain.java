package project3Game;

import java.util.Scanner;




public class GameMain {
	
	static String[][] answerArr = new String[][] {
		{"1","2","3"},
		{"4","5","6"},
		{"7","8","X"}
	};
	static String[][] gameArr = new String[][] {
		{"1","2","3"},
		{"4","5","6"},
		{"7","8","X"}
	};
	
	int col=2, row=2;//사용자 추적

	public GameMain() {
		
	}
	

	//x추적해서 섞기
	public void shuffle(int num) {
		int randomNum=0;
		
		for(int i = 0; i<=num; i++ ) {
			//1~4까지 나오게
			String temp;
			randomNum = ((int)(Math.random()*100)%5)+1;
			
			switch (randomNum) {
			case GameInterface.UP:
				if(row!=0) {
					temp = gameArr[col][row];
					gameArr[col][row] = gameArr[col][row-1];
					gameArr[col][row-1]=temp;
					row--;
					
					
				}
				break;
			case GameInterface.DOWN:
				if(row!=2) {
					temp = gameArr[col][row];
					gameArr[col][row] = gameArr[col][row+1];
					gameArr[col][row+1]=temp;
					row++;
				}
				break;
			case GameInterface.LEFT:
				if(!(col==0)) {
					temp = gameArr[col][row];
					gameArr[col][row] = gameArr[col-1][row];
					gameArr[col-1][row]=temp;
					col--;
				}
				break;
			case GameInterface.RIGHT:
				if(!(col==2)) {
					temp = gameArr[col][row];
					gameArr[col][row] = gameArr[col+1][row];
					gameArr[col+1][row] = temp;
					col++;
				}
				break;
			default:
				break;
			}
			
		}
		for(int i=0;i<gameArr.length;i++) {
			for(int j=0;j<gameArr[i].length;j++) {
				System.out.print(gameArr[i][j]+" ");

			}
			System.out.println();
		}
		
	}
	//X 움직이기
	public void keyboardMove(int col, int row) {
		Scanner scan = new Scanner(System.in);
		String user = scan.nextLine();
		String temp;
		
		for(int i=0; i<gameArr.length; i++) {
			for(int j=0; j<gameArr[i].length; j++) {
				
			}
		}
		
//		switch (user) {
//		case "w":
//			if(row!=0) {
//				temp = gameArr[col][row];
//				gameArr[col][row] = gameArr[col][row-1];
//				gameArr[col][row-1]=temp;
//				row--;
//			}
//			break;
//		case "s":
//			if(row!=2) {
//				temp = gameArr[col][row];
//				gameArr[col][row] = gameArr[col][row+1];
//				gameArr[col][row+1]=temp;
//				row++;
//			}
//			break;
//		case "d":
//			if(col!=0) {
//				temp = gameArr[col][row];
//				gameArr[col][row] = gameArr[col-1][row];
//				gameArr[col-1][row]=temp;
//				col--;
//			}
//			break;
//		case "a":
//			if(col!=2) {
//				temp = gameArr[col][row];
//				gameArr[col][row] = gameArr[col+1][row];
//				gameArr[col+1][row] = temp;
//				col++;
//			}
//			break;
//
//		default:
//			break;
//		}
		for(int i=0;i<gameArr.length;i++) {
			for(int j=0;j<gameArr[i].length;j++) {
				System.out.print(gameArr[i][j]+" ");

			}
			System.out.println();
		}
	}
	
	
	//퍼즐 완성한 모습
	public void gameAnswer() {
		
		for(int i=0;i<answerArr.length;i++) {
			for(int j=0;j<answerArr[i].length;j++) {
				System.out.print(answerArr[i][j]+" ");
				
			}
			System.out.println();
		}
		
	}
	
	public static void main(String[] args) {
	    
		GameMain gameMain = new GameMain();
		
		System.out.println("이렇게 맞춰주세요~");
		gameMain.gameAnswer();
		System.out.println("==========");
		gameMain.shuffle(10);
		System.out.println("==========");
		
		
		System.out.println("[이동] a:Left d:Right w:Up, s:Down");
		System.out.println("[종료] x:Exit");
		while(true){
			System.out.print("키를 입력해주세요>>");
			//gameMain.keyboardMove();
			if(answerArr==gameArr) {
				System.out.println("정답입니다!!!!★★★");
				break;
			}
		}
		
		
		
	}
	
		
}
