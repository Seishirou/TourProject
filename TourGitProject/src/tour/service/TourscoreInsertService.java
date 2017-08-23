package tour.service;

import java.util.Scanner;

import tour.dao.TourscoreInsertDao;
import tour.dto.TourscoreDto;

public class TourscoreInsertService implements Service {

	@Override
	public void start(Scanner sc) {
		// TODO Auto-generated method stub
		int score_num = TourscoreInsertDao.getInstance().selectNum()+1;
		double traffic = sc.nextDouble();
		double stay = sc.nextDouble();
		double sisul = sc.nextDouble();
		double food = sc.nextDouble();
		double etc = sc.nextDouble();
		String assessment = sc.nextLine();
		
		int result=TourscoreInsertDao.getInstance().insert(new TourscoreDto(score_num, "contentid", "id", traffic, stay, sisul, food, etc, assessment));
		if(result != 0) {
			System.out.println("학생 데이터 추가 성공");
		}else {
			System.out.println("실패");
		}
	}
}
