package tour.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import tour.dao.TourDao;
import tour.dto.TourDto;

public class SearchTourService implements Service {
	private String areaCode = null;
	private String areaName = null;
	private String sigunguCode = null;
	private String sigunguName = null;
	private String typeCode = null;
	private String typeName = null;
	private String cat1Code = null;
	private String cat1Name = null;
	private String cat2Code = null;
	private String cat2Name = null;

	@Override
	public void execute(Scanner scan) {
		String selectNum = "";
		Map<String, String> map = new HashMap<>();
		
		System.out.println(" ********************************");
		System.out.println("\t관광 조회 서비스");
		System.out.println(" ********************************");
		
		SearchAreaService sa = new SearchAreaService();
		sa.execute(scan);
		map = sa.getArea();
		areaCode = map.get("areaCode");
		areaName = map.get("areaName");
		sigunguCode = map.get("sigunguCode");
		sigunguName = map.get("sigunguName");
		
		SearchTypeService st = new SearchTypeService();
		st.execute(scan);
		map = st.getTypeCode();
		typeCode = map.get("typeCode");
		typeName = map.get("typeName");
		
		SearchCategoryService sc = new SearchCategoryService();
		sc.execute(scan);
		map = sc.getCatCode();
		cat1Code = map.get("cat1Code");
		cat1Name = map.get("cat1Name");
		cat2Code = map.get("cat2Code");
		cat2Name = map.get("cat2Name");
		
		System.out.println("  <선택항목>\n"+
							"  시/도      : "+areaName+"\n"+
							"  시/군/구 : "+sigunguName+"\n"+
							"  관광타입 : "+typeName+"\n"+
							"  분류 1   : "+cat1Name+"\n"+
							"  분류 2   : "+cat2Name);
		
		List<TourDto> list = TourDao.searchTour(areaCode, sigunguCode, typeCode, cat1Code, cat2Code);
		Iterator<TourDto> it = list.iterator();
		
		if(list.size()==0){
			System.out.println("검색 결과가 없습니다.");
		}else {
			while(it.hasNext()){
				TourDto t = it.next();
				System.out.println(
				"------------------------------------------------\n"+
				"관광지명 : " + t.getTourName() +"\n"+
				"상세주소 : " + t.getAddr() +"\n"+
				"전화번호 : "+t.getTel() +"\n"+
				"------------------------------------------------\n"
				);
			}
		}
		
		
		
//		TourDao.searchTour(areaCode, sigunguCode, typeCode, cat1Code, cat2Code).stream()
//		.forEach(t -> {
//			System.out.println(
//					"------------------------------------------------\n"+
//					"관광지명 : " + t.getTourName() +"\n"+
//					"상세주소 : " + t.getAddr() +"\n"+
//					"전화번호 : "+t.getTel() +"\n"+
//					"------------------------------------------------\n"
//					);
//		});

	}

} // end of class
