package ver04;

public class NormalAccount extends Account{
	
	int interest;
	
	public NormalAccount(String accountNumber, String name, int balance, int interest) {
		super(accountNumber, name, balance);
		this.interest =interest;
	}
	@Override
	public void balanceDeposit(int deposit) {
		balance = balance + (balance * interest)/100 + deposit;
	}

	@Override
	public void showAccount() {
		super.showAccount();
		System.out.println("기본이자>"+interest+"%");
	}
}
