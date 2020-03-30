package ver05;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Scanner;

public class PreparedStatement  extends ConnectDB{
	
	public PreparedStatement() {
		super("kosmo", "1234");
	}
	@Override
	public void dataInput() {
	      try {
	    	  //값의 세팅이 필요한 부분을 ?로 대체한다.
	         String query = "INSERT into banking_tb values (seq_banking.nextval, ?, ?, ?)";
	         
	         //쿼리문을 인자로 전달
	         psmt = con.prepareStatement(query);
	         
	         //DB에 입력할 값을 사용자로부터 입력받음.
	         Scanner scan = new Scanner(System.in);
	         System.out.print("계좌번호 : ");
	         String account = scan.nextLine();
	         System.out.print("이름 : ");
	         String name = scan.nextLine();
	         System.out.print("잔액 : ");
	         int balance = scan.nextInt();
	         
	         //1부터 시작.
	         psmt.setString(1, account);
	         psmt.setString(2, name);
	         psmt.setInt(3, balance);
	         
	      
	         //쿼리실행을 위해  prepared객체를 실행한다.
	         int affected = psmt.executeUpdate();
	         System.out.println(affected + "행이 입력되었습니다.");
	         
	      } 
	      catch (Exception e) {
	         e.printStackTrace();
	      }
	      finally {
	         close();
	      }
	}
	//출금
	@Override
	public void dataWithdraw() {
		Scanner scan = new Scanner(System.in);
		String sql = "update banking_tb "
	            + "Set balance = balance - ? "
	            + "where account = ? ";
	      try {
	         psmt = con.prepareStatement(sql);
	         /*
	          	사용자 입력시 exit를 입력할때까지는 계속 실행되는 형태로 구성
	          */
	        
	        //인파라미터 설정시 해당 인덱스만 맞으면 순서는 상관없다.
	        psmt.setString(2, scanValue("계좌번호"));
	        System.out.print("출금할 금액 : ");
		    int withdraw = scan.nextInt();
	        psmt.setInt(1, withdraw);
	            
	        int affected = psmt.executeUpdate();
	        System.out.println(affected + "행이 업데이트 되었습니다.");
	         
	         
	      } 
	      catch (SQLException e) {
	         e.printStackTrace();
	      }
	      finally {
	         close();
	      }
		
	}
	//입금
	@Override
	public void dataDeposit() {
		Scanner scan = new Scanner(System.in);
		String sql = "update banking_tb "
	            + "Set balance = balance + ? "
	            + "where account = ? ";
	      try {
	         psmt = con.prepareStatement(sql);
	         /*
	          	사용자 입력시 exit를 입력할때까지는 계속 실행되는 형태로 구성
	          */
	        
	        //인파라미터 설정시 해당 인덱스만 맞으면 순서는 상관없다.
	        psmt.setString(2, scanValue("계좌번호"));
	        System.out.print("입금할 금액 : ");
		    int deposit = scan.nextInt();
	        psmt.setInt(1, deposit);
	            
	        int affected = psmt.executeUpdate();
	        System.out.println(affected + "행이 업데이트 되었습니다.");
	         
	         
	      } 
	      catch (SQLException e) {
	         e.printStackTrace();
	      }
	      finally {
	         close();
	      }
	}
}
