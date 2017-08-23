package tour.service;

import java.util.Scanner;

import tour.dao.JoinDao;
import tour.dto.MemDto;
import tour.exception.MyException;

public class JoinService implements Service {

	@Override
	public void execute(Scanner scan) {
		while (true) {
			System.out.println("==========================================================");
			System.out.println("|이용약관, 개인정보 수집 및 이용, 위치정보 이용약관(선택)|");
			System.out.println("|, 프로모션 안내, 메일 수신(선택)에 모두 동의합니까?(Y/N)|");
			System.out.println("==========================================================");
			System.out.print("입력 : ");
			char yn = scan.nextLine().charAt(0);
			switch (yn) {
			case 'Y':
			case 'y':
				System.out.println("회원가입을 위한 필수입력 항목을 입력해 주십시오");
				System.out.println("===============================================");
				String email = "";
				while (true) {
					System.out.print("E-Mail 주소 입력 : ");
					email = scan.nextLine();

					 int at = 0;
					 int dot = 0;
					 for (int i = 0; i < email.length(); i++) {
					 if (email.charAt(i) == '@') {
					 at++;
					 }
					 if (email.charAt(i) == '.') {
					 dot++;
					 }
					 }
					 if (dot == 1 && at == 1) {
					 break;
					 } else {
					 System.out.println("다시 입력하세요");
					 }
				}

				System.out.println("===============================================");
				System.out.print("패스워드 입력 : ");
				String pwd = scan.nextLine();
				System.out.println("===============================================");
				System.out.print("이름 입력 : ");
				String name = scan.nextLine();
				System.out.println("===============================================");
				System.out.print("생년월일 입력(ex170822) : ");
				String birth = scan.nextLine();
				System.out.println("===============================================");
				System.out.print("주소 입력(시까지 입력) : ");
				String addr = scan.nextLine();
				System.out.println("===============================================");
				System.out.print("핸드폰 번호 입력(-제외) : ");
				String cellNum = scan.nextLine();
				try {
					int result = JoinDao.insertMem(new MemDto(email, pwd, name, birth, addr, cellNum));
					if (result != 0) {
						System.out.println("회원가입에 성공했습니다");
						return;
					}
				} catch (MyException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'N':
			case 'n':
				System.out.println("약관에 동의하지 않으면 가입할 수 없습니다");
				break;
			default:
				System.out.println("잘못입력하셨습니다. Y 또는 N을 입력하세요");
			}
		}
	}
}
