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
		System.out.println("\t���� Ÿ�� ����");
		System.out.println(" ================================");
		
		List<TourType> typeList = TourDAO.searchTourType();
		int size = typeList.size();
		for (int i = 0; i < typeList.size(); i++) {
			if(!"x".equals( typeList.get(i).getTypeCode().trim() ))
			System.out.println((i+1)+" : "+typeList.get(i).getTypeName());
		}
		System.out.println(size+" : ���þ���");
		System.out.print("���� Ÿ���� �����ϼ��� > ");
		this.typeCode = scan.nextLine();
		System.out.println();
		index = Integer.parseInt(typeCode) -1;
		if((index+1)==size){
			this.typeCode = "���þ���";
			this.typeName = "���þ���";
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
