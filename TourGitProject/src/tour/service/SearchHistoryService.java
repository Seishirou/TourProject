package tour.service;

import java.util.List;
import java.util.Scanner;

import tour.dao.MemHistoryDao;
import tour.dto.MemHistoryDto;

public class SearchHistoryService implements Service{
	private static String contentID;
	private static String tourName;

	@Override
	public void execute(Scanner sc) {
		String id = LoginService.getEmail();
		int index = 1;
		
		List<MemHistoryDto> list = MemHistoryDao.getInstance().getHistory(id);
		
		System.out.println("관광지 평가");
		
		if(list.size() >5 ){
			for (int i = 0; i < 5 ; i++) {
				System.out.println((index++)+". "+list.get(i).getTourName());
			}
		}else {
			for (int i = 0; i < list.size() ; i++) {
				System.out.println((index++)+". "+list.get(i).getTourName());
			}
		}
		System.out.print("평가 항목 선택 > ");
		index = sc.nextInt(); sc.nextLine();
		
		id = list.get(index-1).getEmail();
		this.contentID = list.get(index-1).getContentid();
		this.tourName = list.get(index-1).getTourName();
		
		new TourscoreInsertService().execute(sc);
		
	}
	
	public String getContentID(){
		return this.contentID;
	}
	
	public String getTourName(){
		return this.tourName;
	}
}
