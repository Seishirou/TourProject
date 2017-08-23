package tour.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import tour.dao.TourDAO;
import tour.dto.Cat1;
import tour.dto.Cat2;

public class SearchCategoryService implements Service{
	
	private String cat1Code;
	private String cat1Name;
	private String cat2Code;
	private String cat2Name;

	@Override
	public void execute(Scanner scan) {
		
		int index = 0;
		
		System.out.println(" ================================");
		System.out.println("\t�з�1 ����");
		System.out.println(" ================================");
		
		List<Cat1> cat1List = TourDAO.searchCat1();
		int size = cat1List.size();
		for (int i = 0; i < cat1List.size(); i++) {
			if(!"x".equals( cat1List.get(i).getCode().trim() ))
			System.out.println((i+1)+" : "+cat1List.get(i).getName());
			
		}
		System.out.println(size+" : ���þ���");
		System.out.print("�з�1�� �����ϼ��� > ");
		this.cat1Code = scan.nextLine();
		System.out.println();
		index = Integer.parseInt(cat1Code) - 1;
		if( (index+1)==size ){
			this.cat1Code = "���þ���";
			this.cat1Name = "���þ���";
		}else{
			this.cat1Code = cat1List.get(index).getCode();
			this.cat1Name = cat1List.get(index).getName();
		}

		
		System.out.println(" ================================");
		System.out.println("\t�з�2 �˻�");
		System.out.println(" ================================");
		
		List<Cat2> cat2List = TourDAO.searchCat2(cat1Code);
		size = cat2List.size();
		for (int i = 0; i < cat2List.size() ; i++) {
			if(!"x".equals( cat2List.get(i).getCode().trim() ))
			System.out.println((i+1)+" : "+cat2List.get(i).getName());
		}
		System.out.println(size+" : ���þ���");
		System.out.print("�з�2�� �����ϼ��� > ");
		this.cat2Code = scan.nextLine();
		System.out.println();
		index = Integer.parseInt(cat2Code) - 1;
		if( (index+1)==size){
			this.cat2Code = "���þ���";
			this.cat2Name = "���þ���";
		}else{
			this.cat2Code = cat2List.get(index).getCode();
			this.cat2Name = cat2List.get(index).getName();
		}
	}
	
	public Map<String, String> getCatCode(){
		Map<String, String> map = new HashMap<>();
		map.put("cat1Code", cat1Code);
		map.put("cat1Name", cat1Name);
		map.put("cat2Code", cat2Code);
		map.put("cat2Name", cat2Name);
		return map;
	}

}











