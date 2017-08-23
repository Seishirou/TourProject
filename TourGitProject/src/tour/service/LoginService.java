package tour.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import tour.dao.LoginDao;

public class LoginService implements Service{
	
	private static String email;

	public static String getEmail() {
		return email;
	}

	@Override
	public void execute(Scanner scan) {
		ResultSet rs = null;
		while(true){
			System.out.println("===============================================");
			System.out.print("E-Mail 입력 : ");
			this.email = scan.nextLine();
			System.out.println("===============================================");
			System.out.print("비밀번호 입력 : ");
			String pwd = scan.nextLine();
			rs = LoginDao.login(email, pwd);
			try {
				if(!rs.next()){
					System.out.println("등록된 ID가 없거나 비밀번호가 다릅니다");
				} else {
					this.email = rs.getString("name");
					System.out.println(email + "님 환영합니다");
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
