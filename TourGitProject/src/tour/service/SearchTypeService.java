package tour.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import tour.dao.TourDAO;
import tour.dto.TourType;

public class SearchTypeService implements Service{
	
	private String typeCode;
	private String typeName;
	
	@Override
	public void execute(Scanner scan) {
		int index = 0;
		System.out.println(" ================================");
		System.out.println("\t관광 타입 선택");
		System.out.println(" ================================");
		
		List<TourType> typeList = TourDAO.searchTourType();
		int size = typeList.size();
		for (int i = 0; i < typeList.size(); i++) {
			if(!"x".equals( typeList.get(i).getTypeCode().trim() ))
			System.out.println((i+1)+" : "+typeList.get(i).getTypeName());
		}
		System.out.println(size+" : 선택안함");
		System.out.print(" 관광 타입을 선택하세요 > ");
		this.typeCode = scan.nextLine();
		System.out.println();
		index = Integer.parseInt(typeCode) -1;
		if((index+1)==size){
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
