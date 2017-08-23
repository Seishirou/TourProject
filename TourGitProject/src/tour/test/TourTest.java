package tour.test;

import java.util.Scanner;

import tour.service.AreaInsertService;
import tour.service.SearchTourService;

public class TourTest {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String select = sc.nextLine();
		
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
