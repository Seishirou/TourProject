package tour.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import tour.dao.LoginDAO;

public class LoginService implements Service{

	@Override
	public void execute(Scanner scan) {
		ResultSet rs = null;
		while(true){
			System.out.println("===============================================");
			System.out.print("E-Mail 입력 : ");
			String email = scan.nextLine();
			System.out.println("===============================================");
			System.out.print("비밀번호 입력 : ");
			String pwd = scan.nextLine();
			rs = LoginDAO.login(email, pwd);
			try {
				if(!rs.next()){
					System.out.println("등록된 ID가 없거나 비밀번호가 다릅니다");
				} else {
					System.out.println(rs.getString("name") + "님 환영합니다");
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
