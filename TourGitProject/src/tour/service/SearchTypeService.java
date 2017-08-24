package tour.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import tour.dao.TourDao;
import tour.dto.TourTypeDto;

public class SearchTypeService implements Service{
	
	private String typeCode;
	private String typeName;
	
	@Override
	public void execute(Scanner scan) {
		int index = 0;
		System.out.println("===============================================");
		System.out.println(" 관광 타입 선택");
		System.out.println("===============================================");
		
		List<TourTypeDto> typeList = TourDao.searchTourType();
		int size = typeList.size();
		System.out.println(" 0 : 선택안함");
		for (int i = 0; i < typeList.size(); i++) {
			if(!"x".equals( typeList.get(i).getTypeCode().trim() ))
			System.out.println(" "+(i+1)+" : "+typeList.get(i).getTypeName());
		}
		
		System.out.print(" 관광 타입을 선택하세요 > ");
		this.typeCode = scan.nextLine();
		System.out.println();
		index = Integer.parseInt(typeCode) -1;
		if( index == -1 ){
			this.typeCode = "선택안함";
			this.typeName = "선택안함";
		}else {
			this.typeCode = typeList.get(index).getTypeCode();
			this.typeName = typeList.get(index).getTypeName();
		}
	}
	public Map<String, String> getTypeCode(){
		Map<String, String> map = new HashMap<>();
		map.put("typeCode", typeCode);
		map.put("typeName", typeName);
		return map;
	}
}
