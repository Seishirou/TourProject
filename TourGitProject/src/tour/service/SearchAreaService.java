package tour.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import tour.dao.TourDao;
import tour.dto.AreaDto;
import tour.dto.SigunguDto;

public class SearchAreaService implements Service{
	
	private String areaCode;
	private String areaName;
	private String sigunguCode;
	private String sigunguName;

	@Override
	public void execute(Scanner scan) {
		System.out.println("===============================================");
		System.out.println(" 시/도 검색");
		System.out.println("===============================================");
		int index = 0;
		
		List<AreaDto> areaList = TourDao.searchArea();
		for (int i = 0; i < areaList.size(); i++) {
			if(!"x".equals( areaList.get(i).getAreaCode().trim() ))
			System.out.printf(" %2d : %s\n",(i+1),areaList.get(i).getAreaName());
			
		}
		System.out.print(" 시/도 를 선택하세요 > ");
		this.areaCode = scan.nextLine();
		System.out.println();
		index = Integer.parseInt(areaCode) - 1;
		this.areaCode = areaList.get(index).getAreaCode();
		this.areaName = areaList.get(index).getAreaName();
		
		System.out.println("===============================================");
		System.out.println(" 시/군/구 검색");
		System.out.println("===============================================");
		
		List<SigunguDto> sigunguList = TourDao.searchSigungu(areaCode);
		int size = sigunguList.size();
		for (int i = 0; i < sigunguList.size() ; i++) {
			if(!"x".equals( sigunguList.get(i).getSigunguCode().trim() ))
			System.out.printf(" %2d : %s\n",(i+1),sigunguList.get(i).getSigunguName());
		}
		System.out.printf(" %2d : %s\n",size,"선택안함");
		System.out.print(" 시/군/구 를 선택하세요 > ");
		this.sigunguCode = scan.nextLine();
		System.out.println();
				
		index = Integer.parseInt(sigunguCode) - 1;
		if( (index+1) == size ){
			this.sigunguCode = "선택안함";
			this.sigunguName = "선택안함";
		}else{
			this.sigunguCode = sigunguList.get(index).getSigunguCode();
			this.sigunguName = sigunguList.get(index).getSigunguName();
		}
	}
	public Map<String, String> getArea(){
		Map<String, String> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("areaName", areaName);
		map.put("sigunguCode", sigunguCode);
		map.put("sigunguName", sigunguName);
		return map;
	}
}
