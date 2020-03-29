package ver02;

import java.util.Scanner;
import ver02.MenuChoice;


public class AccountManager {
	//멤버변수
	String accountNumber;
	String name;
	int balance;
	int interest;
	
	private Account [] accountCustomer;
	private int numOfaccount;
	
	//생성자
	public AccountManager(int num) {
		accountCustomer = new Account[num];
		numOfaccount = 0;
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
			int choice01 = scan.nextInt();
			scan.nextLine(); //버퍼날림
			
			switch (choice01) {
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
		String inAccountNumber, inName, CreditRating;
		int inBalance, interset, CreditInterest;
		
		System.out.println("***신규계좌개설***");
		System.out.println("---계좌선택---");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print("선택 : ");
		int choice02 = scan.nextInt();
		scan.nextLine(); //버퍼날림
		
		System.out.print("계좌번호 : ");
		inAccountNumber = scan.nextLine();
		System.out.print("고객이름 : ");
		inName = scan.nextLine();
		//scan.nextLine(); //버퍼날림
		System.out.print("잔고 : ");
		inBalance = scan.nextInt();
		
		//보통계좌
		if(choice02==MenuChoice.Normal) {
			System.out.print("기본이자%(정수형태로 입력) : ");
			interset = scan.nextInt();
			
			NormalAccount normalAccount = new NormalAccount
					(inAccountNumber, inName, inBalance, interset);
			accountCustomer[numOfaccount++] = 
										normalAccount;
		}
		
		//신용계좌
		if(choice02==MenuChoice.HighCredit) {
			System.out.print("기본이자%(정수형태로 입력) : ");
			interset = scan.nextInt();
			scan.nextLine(); //버퍼날림
			System.out.print("신용등급(A,B,C등급) : ");
			CreditRating = scan.nextLine();
			//scan.nextLine(); //버퍼날림
			
			HighCreditAccount highCreditAccount = new HighCreditAccount
					(inAccountNumber, inName, inBalance, interset, CreditRating);
			accountCustomer[numOfaccount++] = 
									highCreditAccount;
			
		}
			
		System.out.println("계좌계설이 완료되었습니다.");
	}
	
	//HighCredit
	public void MakeHighCreaditAccount(String accountNumber, String name, int balance, int interest, String CreditRating) {

		HighCreditAccount highCreditAccount = new HighCreditAccount
				(accountNumber, name, balance, interest, CreditRating);
		accountCustomer[numOfaccount++] = 
								highCreditAccount;
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
				
				accountCustomer[i].balanceDeposit(deposit);
			}

				
		}
		System.out.println("입금이 완료되었습니다.");
			
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
