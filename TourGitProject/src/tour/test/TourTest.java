package tour.test;

import java.util.Scanner;

import tour.service.AreaInsertService;
import tour.service.JoinService;
import tour.service.LoginService;
import tour.service.SearchHistoryService;
import tour.service.SearchTourService;

public class TourTest {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String select = "";
		while (true) {
			System.out.println("==================================");
			System.out.println("| 1.회원가입 | 2.로그인 | 0.종료 |");
			System.out.println("==================================");
			System.out.print("메뉴를 선택하세요 : ");
			select = sc.nextLine();
			switch (select) {
			case "1":
				new JoinService().execute(sc); // 회원가입 실행
				break;
			case "2":
				new LoginService().execute(sc); // 로그인 실행
				
				while(!"0".equals(select)){
					System.out.println("=============================================");
					System.out.println("| 1.관광지 조회 | 2.관광지 평가 | 0.로그아웃|");
					System.out.println("=============================================");
					System.out.print("메뉴를 선택하세요 : ");
					select = sc.nextLine();
					System.out.println();
					switch (select) {
					case "1":
						new SearchTourService().execute(sc);
						break;
					case "2":
						new SearchHistoryService().execute(sc);
						break;
					case "0" :
						System.out.println("로그 아웃 합니다.");
						break;
					default:
						System.out.println("바른 숫자를 입력하여 주세요.");
						break;
					}
				}
				break;
			case "0" :
				System.out.println("프로그램을 종료합니다");
				System.exit(0);
				break;
				
			default:
				System.out.println("바른 숫자를 입력하여 주세요.");
				break;
			}
			System.out.println();
		}//while
		
	}// end of main
}// end of class
