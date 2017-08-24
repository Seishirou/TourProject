package tour.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import tour.dao.TourDao;
import tour.dto.Cat1Dto;
import tour.dto.Cat2Dto;

public class SearchCategoryService implements Service{
	
	private String cat1Code;
	private String cat1Name;
	private String cat2Code;
	private String cat2Name;

	@Override
	public void execute(Scanner scan) {
		
		int index = 0;
		
		System.out.println("============================================");
		System.out.println("                분류1 선택                  ");
		System.out.println("============================================");
		
		List<Cat1Dto> cat1List = TourDao.searchCat1();
		int size = cat1List.size();
		System.out.println(" 0 : 선택안함");
		for (int i = 0; i < cat1List.size(); i++) {
			if(!"x".equals( cat1List.get(i).getCode().trim() ))
			System.out.println(" "+(i+1)+" : "+cat1List.get(i).getName());
			
		}
		
		System.out.print(" 분류1 을 선택하세요 : ");
		this.cat1Code = scan.nextLine();
		System.out.println();
		index = Integer.parseInt(cat1Code) - 1;
		if( index == -1 ){
			this.cat1Code = "선택안함";
			this.cat1Name = "선택안함";
		}else{
			this.cat1Code = cat1List.get(index).getCode();
			this.cat1Name = cat1List.get(index).getName();
		}

		
		System.out.println("============================================");
		System.out.println("                분류2 선택                  ");
		System.out.println("============================================");
		
		List<Cat2Dto> cat2List = TourDao.searchCat2(cat1Code);
		size = cat2List.size();
		System.out.println(" 0 : 선택안함");
		for (int i = 0; i < cat2List.size() ; i++) {
			if(!"x".equals( cat2List.get(i).getCode().trim() ))
			System.out.println(" "+(i+1)+" : "+cat2List.get(i).getName());
		}
		
		System.out.print(" 분류2 를 선택하세요 : ");
		this.cat2Code = scan.nextLine();
		System.out.println();
		index = Integer.parseInt(cat2Code) - 1;
		if( index == -1 ){
			this.cat2Code = "선택안함";
			this.cat2Name = "선택안함";
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











