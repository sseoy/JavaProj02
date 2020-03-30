package ver04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import ver04.MenuChoice;
import ver04.MenuSelectException;


//import java.util.HashSet;
import java.util.Iterator;


public class AccountManager {
	//멤버변수
	String accountNumber;
	String name;
	int balance;
	int interest;
	
	//set컬렉션 생성
	HashSet<Account> customer;
	
	//생성자
	public AccountManager() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/ver04/AccountCustomer.obj"));
			
			customer = (HashSet<Account>)in.readObject();
		}
		catch (Exception e) {
			customer = new HashSet<Account>();
	    }
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
		try {
				int choice01 = scan.nextInt();
				scan.nextLine(); //버퍼날림
				if(choice01<1 || choice01>5) {
					MenuSelectException selectEx = new MenuSelectException();
					throw selectEx;
				}
				
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
					saveAccountCustomer();
					return;
				default:
					System.out.println("다시입력해주세요");
					showMenu();
				}
			}catch(MenuSelectException e) {
				System.out.println(e.getMessage());
			}
			catch(InputMismatchException e) {
				System.out.println("문자형태로 입력하면 안되요.");
				scan.nextLine();
			}
		}
	}
	//계좌번호 중복확인
	public void OverlapAccountNumber(String accountNumber, String name, Account account) {
		Scanner scan = new Scanner(System.in);
		
		boolean a = customer.add(account);//true면 저장됨.
		
		if(a == false) {
			System.out.println("같은계좌번호가 있습니다. 새로운 정보로 갱신하시겠습니까? 네:1, 아니오:2");
			int choice03 = scan.nextInt();
			if(choice03 ==1) {
				customer.remove(account);
				customer.add(account);
			}else {
				makeAccount();
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
		try {
			int choice02 = scan.nextInt();
			scan.nextLine(); //버퍼날림
			
			if(choice02<1 || choice02>2) {
				MenuSelectException selectEx = new MenuSelectException();
				throw selectEx;
			}
			
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
				OverlapAccountNumber(inAccountNumber, inName, normalAccount);
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
				OverlapAccountNumber(inAccountNumber, inName, highCreditAccount);
				
			}
				
			System.out.println("계좌계설이 완료되었습니다.");
		}catch(MenuSelectException e) {
			System.out.println("[예외발생] 1~2사이의 숫자를 입력하세요");
			
		}
		catch(InputMismatchException e) {
			System.out.println("문자형태로 입력하면 안되요.");
			scan.nextLine();
		}	
	}
	
		
	//입금
	public void depositMoney() {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("***입금***");
			System.out.println("계좌번호와 입금할 금액을 입력하세요");
			
			System.out.print("계좌번호 : ");
			String inAccountNumber = scan.nextLine();
			System.out.print("입금액 : ");
			
			int deposit = scan.nextInt();
			if(deposit<0) {
				System.out.println("음수를 입력할수 없습니다!");
			}else if(!(deposit%500 == 0)) {
				System.out.println("입금액은 500원 단위로 가능합니당~");
			}
			
			boolean depositFlag = false;//검색결과 유무 확인	 
			
			Iterator<Account> ir = customer.iterator();
			while(ir.hasNext()) {
				Account account = ir.next();
				if(inAccountNumber.endsWith(account.accountNumber)) {
					depositFlag = true;
					account.balanceDeposit(deposit);
					
				}
			}
			System.out.println("입금이 완료되었습니다.");
			
		}catch(InputMismatchException e) {
			System.out.println("문자형태로 입력하면 안되요.");
			
		}
	}
		
	//출금
	public void withdrawMoney() {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("***출금***");
			System.out.println("계좌번호와 출금할 금액을 입력하세요");
				
			System.out.print("계좌번호 : ");
			String inAccountNumber = scan.nextLine();
			System.out.print("출금액 : ");
			int withdraw = scan.nextInt();

			scan.nextLine(); //버퍼날림
			
			if(withdraw<0) {
				System.out.println("음수를 입력할수 없습니다!");
				withdrawMoney();
				
			}else if(!(withdraw%1000 == 0)) {
				System.out.println("입금액은 1000원 단위로 가능합니당~");
				withdrawMoney();
			}
			
			boolean depositFlag = false;//검색결과 유무 확인	 
			
			Iterator<Account> ir = customer.iterator();
			while(ir.hasNext()) {
				Account account = ir.next();
				if(inAccountNumber.endsWith(account.accountNumber)) {
					depositFlag = true;
					
					if(account.balance<withdraw) {
						
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
						
						System.out.println("yes : 금액전체 출금처리, no : 출금요청취소");
						String choice03 = scan.nextLine();
						
						
						if(choice03.equals("yes")) {
							
							account.balance = 0;
							
						}else if(choice03.equals("no")) {
							
							System.out.println("출금요청을 취소합니다.");
						}else {
							
						}
						
					}else {
						account.balance -= withdraw;
						
						System.out.println("출금이 완료되었습니다.");
					}
					
					
				}
			}
			
		}catch(InputMismatchException e) {
			System.out.println("문자형태로 입력하면 안되요.");
		}
	}
		
	//전체계좌정보출력
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		System.out.println("----------------");
			
		for(Account info : customer) {
				
			info.showAccount();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	//파일형태로 저장하기
			public void saveAccountCustomer() {
				try {
					ObjectOutputStream out = new ObjectOutputStream(
							new FileOutputStream("src/ver04/AccountCustomer.obj"));
					
					out.writeObject(customer);
							
				}catch(Exception e) {
					System.out.println("예외발생");
					e.printStackTrace();
				}
			}
}
