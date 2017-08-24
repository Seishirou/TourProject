package tour.service;

import java.util.Scanner;

public class SelectScanService implements Service {

	@Override
	public void execute(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("\n1. 관광지 조회\n"
						   + "2. 관광지 평가\n");
	}
	public String select(Scanner sc) {
		execute(sc);
		String select = sc.nextLine();
		return select;
	}

}
