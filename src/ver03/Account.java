package ver03;

public abstract class Account {
	//멤버변수
	String accountNumber;
	String name;
	int balance;

	public Account() {
		
	}
	
	public Account(String accountNumber, String name, int balance) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance= balance;
	}
	
	//잔고 
	public void balanceDeposit(int deposit) {
		
	}


	//정보출력용 메소드
	public void showAccount() {
		System.out.println("계좌번호>" + accountNumber);
		System.out.println("고객이름 >" + name);
		System.out.println("잔고>" + balance);
	}
	
}
