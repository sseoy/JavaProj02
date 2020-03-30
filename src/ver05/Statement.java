package ver05;

import java.sql.SQLException;

public class Statement extends ConnectDB{
	public Statement() {
		super("kosmo","1234");
	}
	@Override
	public void dataCutomer() {
		try {
			
			String sql = "SELECT * FROM banking_tb "
						+ "WHERE account LIKE '%'||?||'%'";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, scanValue("검색할 계좌번호"));
			rs= psmt.executeQuery();
			while(rs.next()) {
				 String account = rs.getString(2);
				 String name = rs.getString(3);
				 int balance = rs.getInt(4);
					 
				 System.out.println("검색결과 ");
				 System.out.printf("[%s %s %s]\n",
						 account, name, balance);
			}
			if(psmt.executeUpdate()==0) {
				System.out.println("계좌번호를 검색할수 없습니다.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

	@Override
	public void dataAllshow() {
		try {
			
			String sql = "SELECT * FROM banking_tb ";
			psmt = con.prepareStatement(sql);
			//psmt.setString(1, scanValue("검색할 이름"));
			rs= psmt.executeQuery();
			System.out.println("[계좌번호, 이름, 잔액]");
			System.out.println("====================");
			while(rs.next()) {
				 String account = rs.getString(2);
				 String name = rs.getString(3);
				 int balance = rs.getInt(4);
					 
					  
				 System.out.printf("[%s %s %s]\n",
						 account, name, balance);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
}
