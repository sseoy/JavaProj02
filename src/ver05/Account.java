package ver05;

import java.util.Scanner;
import ver05.MenuChoice;
import project3Game.*;

public class Account {
	//멤버변수
	String accountNumber;
	String name;
	int balance;
	GameMain gameMain = new GameMain();
	String[] args = null;
	//생성자
	public Account() {
		
	}
	public Account(String accountNumber, String name, int balance) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance= balance;
	}
	//정보출력용 메소드
	public void showAccount() {
		System.out.println("계좌번호\t" + accountNumber);
		System.out.println("고객이름 \t " + name);
		System.out.println("잔고\t" + balance);
	}
	//메뉴출력
	public void showMenu() {
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("---Menu---");
			System.out.println("1.계좌개설");
			System.out.println("2.입금");
			System.out.println("3.출금");
			System.out.println("4.전체계좌정보 출력");
			System.out.println("5.계좌정보 출력");
			System.out.println("6.3by3게임하기");
			System.out.println("7.프로그램 종료");
			System.out.print("선택 : ");
			int choice = scan.nextInt();
			scan.nextLine(); //버퍼날림
		
			switch (choice) {
			case MenuChoice.MAKE:
				makeAccount();
				break;
			case MenuChoice.DEPOSIT:
				depositMoney();
				break;
			case MenuChoice.WITHDRAW:
				withdrawMoney();
				break;
			case MenuChoice.INQUIRE:
				showAccInfo();
				break;
			case MenuChoice.CUSTOMER:
				showCustomer();
				break;
			case MenuChoice.GAME:
				
				gameMain.main(args);
				break;
			case MenuChoice.EXIT:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("다시입력해주세요");
				showMenu();
			}
		}
	}
	
	//신규계좌개설을 위한 함수
	public void makeAccount() {
		new PreparedStatement().dataInput(); 
	}
	
	//입금
	public void depositMoney() {
		
		new PreparedStatement().dataDeposit(); 
	}
	
	
	//출금
	public void withdrawMoney() {
		new PreparedStatement().dataWithdraw(); 
	}
	
	//전체계좌정보출력
	public void showAccInfo() {
		new Statement().dataAllshow();
	}
	//개인계좌출력
	public void showCustomer() {
		new Statement().dataCutomer();
	}
}
