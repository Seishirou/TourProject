package tour.service;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import tour.dao.JoinDao;
import tour.dto.MemDto;
import tour.exception.MyException;

public class JoinService implements Service {

	@Override
	public void execute(Scanner sc) {
		while (true) {
			System.out.println("==========================================================");
			System.out.println("|이용약관, 개인정보 수집 및 이용, 위치정보 이용약관(선택)|");
			System.out.println("|, 프로모션 안내, 메일 수신(선택)에 모두 동의합니까?(Y/N)|");
			System.out.println("==========================================================");
			System.out.print("입력 : ");
			char yn = sc.nextLine().charAt(0);
			switch (yn) {
			case 'Y':
			case 'y':
				System.out.println("회원가입을 위한 필수입력 항목을 입력해 주십시오");
				System.out.println("===============================================");
				String email;
				while (true) {
					System.out.print("E-Mail 주소 입력 : ");
					email = sc.nextLine();
					if (JoinDao.emailCheck(email)) {
						break;
					} else {
						System.out.println("이메일 형식이 틀립니다. 다시 입력하세요");
					}
				}
				System.out.println("===============================================");
				String pwd;
				while (true) {
					System.out.print("패스워드 입력(영문+숫자, 6~10자리) : ");
					pwd = sc.nextLine();
					if (JoinDao.pwdCheck(pwd)) {
						break;
					} else {
						System.out.println("영문 + 숫자 또는 6~10자리로 입력하세요");
					}
				}
				System.out.println("===============================================");
				String name;
				while (true) {
					System.out.print("이름 입력 : ");
					name = sc.nextLine();
					if (JoinDao.nameAndAddrCheck(name)) {
						break;
					} else {
						System.out.println("한글만 입력하세요");
					}
				}
				System.out.println("===============================================");
				String birth;
				while (true) {
					System.out.print("생년월일 입력(ex20170822) : ");
					birth = sc.nextLine();
					if (JoinDao.birthCheck(birth)) {
						break;
					} else {
						System.out.println("잘못입력하셨습니다. 다시입력하세요");
					}
				}
				System.out.println("===============================================");
				String addr;
				while (true) {
					System.out.print("주소 입력(시까지 입력) : ");
					addr = sc.nextLine();
					if (JoinDao.nameAndAddrCheck(addr)) {
						break;
					} else {
						System.out.println("한글만 입력하세요");
					}
				}
				System.out.println("===============================================");
				String cellNum;
				while (true) {
					System.out.print("핸드폰 번호 입력(-제외) : ");
					cellNum = sc.nextLine();
					if (JoinDao.cellNumCheck(cellNum)) {
						break;
					} else {
						System.out.println("핸드폰번호 형식이 다릅니다. 다시입력하세요");
					}
				}
				
				try {
					int result = JoinDao.insertMem(new MemDto(email, pwd, name, birth, addr, cellNum));
					if (result != 0) {
						System.out.println("회원가입에 성공했습니다");
						return;
					}
				} catch (MyException e) {
					System.out.println(e.getMessage());
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
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
	} // end of execute
} // end of class