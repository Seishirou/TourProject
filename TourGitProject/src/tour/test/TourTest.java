package tour.test;

import java.util.Scanner;

import tour.service.AreaInsertService;
import tour.service.SearchTourService;

public class TourTest {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"2. 관광지 평가\n" +
				"3. 관광지 조회\n");
		System.out.print("메뉴를 선택하세요 > ");
		String select = sc.nextLine();
		System.out.println();
		
		switch (select) {
		case "1":
			new AreaInsertService().execute(sc);
			break;
		case "2":
//			new TourscoreInsertDao().selectNum();
			break;
		case "3":
			new SearchTourService().execute(sc);
			break;

		default:
			break;
		}
		
	}// end of main
}// end of class
