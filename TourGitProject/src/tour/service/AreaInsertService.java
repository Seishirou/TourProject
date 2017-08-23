package tour.service;

import java.util.Scanner;

import tour.parse.AreaParse;

public class AreaInsertService implements Service {

	@Override
	public void execute(Scanner sc) {
		// TODO Auto-generated method stub
		int result = new AreaParse().parse();
		if(result != 0) {
			System.out.println("Area Data Insert 성공");
		}else {
			System.out.println("실패");
		}
	}

}
