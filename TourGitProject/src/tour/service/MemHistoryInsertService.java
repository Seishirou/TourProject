package tour.service;

import java.util.Scanner;

import tour.dao.MemHistoryDao;
import tour.dto.MemHistoryDto;

public class MemHistoryInsertService implements Service{

	@Override
	public void execute(Scanner sc) {
		
		int recordnum = MemHistoryDao.getInstance().selectNum();
		String email=new LoginService().getEmail();
		String contentid = new SearchTourService().getContentID();
		
		int result = MemHistoryDao.getInstance().insert(new MemHistoryDto(recordnum, email, contentid));
		if(result != 0) {
			System.out.println("회원History Data 추가 성공");
		}else {
			System.out.println("실패");
		}
	}

}
