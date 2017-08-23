package tour.test;

import java.util.Scanner;

import tour.service.AreaInsertService;

public class TourTest {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String select = sc.nextLine();
		switch (select) {
		case "1":
			new AreaInsertService().start(sc);
			break;
		case "2":
//			new TourscoreInsertDao().selectNum();
			break;

		default:
			break;
		}
		
	}// end of main
}// end of class
