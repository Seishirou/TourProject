package tour.test;

import java.util.Scanner;

import tour.service.AreaInsertService;
import tour.service.JoinService;
import tour.service.LoginService;
import tour.service.SearchHistoryService;
import tour.service.SearchTourService;
import tour.service.SelectScanService;
import tour.service.TourscoreInsertService;

public class TourTest {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		exit : while (true) {
			System.out.println("1. 회원가입\n" + "2. 로그인\n" + "0. 종료\n");
			System.out.print("메뉴를 선택하세요 > ");
			System.out.println();
			String select = sc.nextLine();
			switch (select) {
			case "1":
				new JoinService().execute(sc);
				break;
			case "2":
				new LoginService().execute(sc);
				switch (new SelectScanService().select(sc)) {
				case "1":
					new SearchTourService().execute(sc);
					break;
				case "2":
					new SearchHistoryService().execute(sc);
					break;

				default:
					System.out.println("바른 숫자를 입력하여 주세요.");
					break;
				}
				break;

			default:
				if("0".equals(select))
					System.exit(0);
				System.out.println("바른 숫자를 입력하여 주세요.");
				break;
			}
			System.out.println();
		}//while
		
	}// end of main
}// end of class
