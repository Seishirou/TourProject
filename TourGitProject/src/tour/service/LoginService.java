package tour.service;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map.Entry;
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
				System.out.println("============================================");
				System.out.print("E-Mail 주소 입력 : ");
				this.email = sc.nextLine();
				if (RegexUtil.emailCheck(email)) {
					break;
				} else {
					System.out.println("이메일 형식이 틀립니다. 다시 입력하세요");
				}
			}
			while (true) {
				System.out.println("============================================");
				System.out.print("비밀번호 입력 : ");
				String pwd = sc.nextLine();
				try {
					Iterator<Entry<String, Boolean>> it = LoginDao.login(email, pwd).entrySet().iterator();
					Entry<String, Boolean> entry = it.next();
					if (!entry.getValue()) {
						System.out.println("등록된 ID가 없거나 비밀번호가 다릅니다");
						break;
					} else {
						System.out.println("============================================");
						System.out.println("         " + entry.getKey() + "님 환영합니다");
						return;
					}
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
