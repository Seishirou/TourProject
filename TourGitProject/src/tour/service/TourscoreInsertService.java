package tour.service;

import java.util.Scanner;

import tour.dao.TourscoreDao;
import tour.dto.TourscoreDto;

public class TourscoreInsertService implements Service {

	@Override
	public void execute(Scanner sc) {
		SearchHistoryService shs = new SearchHistoryService();
		int score_num = TourscoreDao.getInstance().selectNum()+1;
		String contentid = shs.getContentID();
		String email = LoginService.getEmail();
		
		System.out.println("'"+shs.getTourName()+"'"+" 평가하기");
		System.out.print("교통 > ");
		double traffic = sc.nextDouble(); sc.nextLine();
		System.out.print("숙박 > ");
		double stay = sc.nextDouble(); sc.nextLine();
		System.out.print("시설 > ");
		double sisul = sc.nextDouble(); sc.nextLine();
		System.out.print("음식 > ");
		double food = sc.nextDouble(); sc.nextLine();
		System.out.print("기타 > ");
		double etc = sc.nextDouble(); sc.nextLine();
		System.out.print("한줄 평 > ");
		String assessment = sc.nextLine();
		
		int result=TourscoreDao.getInstance().insert(new TourscoreDto(score_num, contentid, email, traffic, stay, sisul, food, etc, assessment));
		if(result != 0) {
			System.out.println("입력 성공");
		}else {
			System.out.println("실패");
		}
	}
}
