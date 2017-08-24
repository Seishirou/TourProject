package tour.service;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import tour.dao.LoginDao;
import tour.util.RegexUtil;

public class LoginService implements Service {

	private static String email;

	public static String getEmail() {
		return email;
	}

	@Override
	public void execute(Scanner sc) {
		// ResultSet rs = null;
		while (true) {
			while (true) {
				System.out.println("===============================================");
				System.out.print("E-Mail 입력 : ");
				this.email = sc.nextLine();
				if (RegexUtil.emailCheck(email)) {
					break;
				} else {
					System.out.println("이메일 형식이 틀립니다. 다시 입력하세요");
				}
			}
			while (true) {
				System.out.println("===============================================");
				System.out.print("비밀번호 입력 : ");
				String pwd = sc.nextLine();
				try {
					if (!LoginDao.login(email, pwd)) {
						System.out.println("등록된 ID가 없거나 비밀번호가 다릅니다");
						break;
					} else {
						// String name = rs.getString("name");
						System.out.println("환영합니다");
						return;
					}
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
