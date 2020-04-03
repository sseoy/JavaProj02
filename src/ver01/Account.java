package ver01;

import java.util.Scanner;
import ver01.MenuChoice;

public class Account {
	//멤버변수
	String accountNumber;
	String name;
	int balance;
	
	
	//배열   
	private Account [] accountCustomer;
	private int numOfaccount;
	
	//생성자
	public Account(int num) {
		accountCustomer = new Account[num];
		numOfaccount = 0;
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
			System.out.println("5.프로그램 종료");
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
		Scanner scan = new Scanner(System.in);
		System.out.println("***신규계좌개설***");
		
		System.out.print("계좌번호 : ");
		String inAccountNumber = scan.nextLine();
		System.out.print("고객이름 : ");
		String inName = scan.nextLine();
		//scan.nextLine(); //버퍼날림
		System.out.print("잔고 : ");
		int inBalance = scan.nextInt();
		accountCustomer[numOfaccount++] = 
				new Account(inAccountNumber, inName, inBalance);
		
		System.out.println("계좌계설이 완료되었습니다.");
	}
	
	//입금
	public void depositMoney() {
		Scanner scan = new Scanner(System.in);
		System.out.println("***입금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		
		System.out.print("계좌번호 : ");
		String inAccountNumber = scan.nextLine();
		System.out.print("입금액 : ");
		int deposit = scan.nextInt();
		
		for(int i=0; i<numOfaccount; i++) {
			if(inAccountNumber.compareTo(accountCustomer[i].accountNumber)==0) {
				
				accountCustomer[i].balance += deposit;
				
			}
			System.out.println("입금이 완료되었습니다.");
		}
		
	}
	
	
	//출금
	public void withdrawMoney() {
			Scanner scan = new Scanner(System.in);
			System.out.println("***출금***");
			System.out.println("계좌번호와 출금할 금액을 입력하세요");
			
			System.out.print("계좌번호 : ");
			String inAccountNumber = scan.nextLine();
			System.out.print("출금액 : ");
			int withdraw = scan.nextInt();
			
			for(int i=0; i<numOfaccount; i++) {
				if(inAccountNumber.compareTo(accountCustomer[i].accountNumber)==0) {
					if(accountCustomer[i].balance<withdraw) {
						
						System.out.println("잔고가 부족합니다.");
					}else {
						
						accountCustomer[i].balance -= withdraw;
					}
					
				}
				System.out.println("출금이 완료되었습니다.");
				
			}
	}
	
	//전체계좌정보출력
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		System.out.println("----------------");
		
		for(int i=0; i<numOfaccount; i++) {
			
			accountCustomer[i].showAccount();
			System.out.println("----------------");
	
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
}
