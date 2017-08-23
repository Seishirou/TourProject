package tour.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import tour.dao.TourDao;

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
//
//		int count = 0;
//		
//		while(count<3){
//
//			
//			System.out.print("  ��ȣ ���� > ");
//			selectNum = scan.nextLine();
//			switch (selectNum) {
//			case "1":
//				SearchAreaService sa = new SearchAreaService();
//				sa.excute(scan);
//				map = sa.getArea();
//				areaCode = map.get("areaCode");
//				sigunguCode = map.get("sigunguCode");
//				count++;
//				break;
//			case "2":
//				SearchTypeService st = new SearchTypeService();
//				st.excute(scan);
//				map = st.getTypeCode();
//				typeCode = map.get("typeCode");
//				count++;
//				break;
//			case "3":
//				SearchCategoryService sc = new SearchCategoryService();
//				sc.excute(scan);
//				map = sc.getCatCode();
//				cat1Code = map.get("cat1Code");
//				cat2Code = map.get("cat2Code");
//				count++;
//				break;
//			}
//		}
		
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
		
		System.out.println("선택항목 : "+areaName+"/"+sigunguName+"/"+typeName+"/"+cat1Name+"/"+cat2Name);
		
		TourDao.searchTour(areaCode, sigunguCode, typeCode, cat1Code, cat2Code).stream()
		.forEach(t -> {
			System.out.println(
					"------------------------------------------------\n"+
					"관광지명 : " + t.getTourName() +"\n"+
					"상세주소 : " + t.getAddr() +"\n"+
					"전화번호 : "+t.getTel() +"\n"+
					"------------------------------------------------\n"
					);
		});

	}

} // end of class
