package ver04;

import ver04.CustomSpecialRate;

public class HighCreditAccount extends Account {
	
	int interest;
	String CreditRating;
	
	public HighCreditAccount(String accountNumber, String name, int balance, int interest, String CreditRating) {
		super(accountNumber, name, balance);
		this.interest = interest;
		this.CreditRating = CreditRating;
	}
	@Override
	public void balanceDeposit(int deposit) {
		int CreditInterest=0;
		
		if(CreditRating.equals("A")) {
			CreditInterest = CustomSpecialRate.A;
		}else if(CreditRating.equals("B")) {
			CreditInterest = CustomSpecialRate.B;
		}else if(CreditRating.equals("C")) {
			CreditInterest = CustomSpecialRate.C;
		}
		
		balance = balance + (balance * interest)/100 
				+(balance * CreditInterest)/100 + deposit;
	}
	
	@Override
	public void showAccount() {
		super.showAccount();
		System.out.println("기본이자>"+interest+"%");
		System.out.println("신용등급>"+CreditRating);
	}

}
