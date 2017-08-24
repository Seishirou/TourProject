package tour.service;

import java.util.Scanner;

import tour.dao.TourscoreDao;
import tour.dto.TourscoreDto;

public class TourscoreInsertService implements Service {

	@Override
	public void execute(Scanner sc) {
		// TODO Auto-generated method stub
		int score_num = TourscoreDao.getInstance().selectNum()+1;
		String contentid = SearchTourService.getContentID();
		String email = LoginService.getEmail();
		double traffic = sc.nextDouble();
		double stay = sc.nextDouble();
		double sisul = sc.nextDouble();
		double food = sc.nextDouble();
		double etc = sc.nextDouble();
		String assessment = sc.nextLine();
		
		int result=TourscoreDao.getInstance().insert(new TourscoreDto(score_num, contentid, email, traffic, stay, sisul, food, etc, assessment));
		if(result != 0) {
			System.out.println("입력 성공");
		}else {
			System.out.println("실패");
		}
	}
}
