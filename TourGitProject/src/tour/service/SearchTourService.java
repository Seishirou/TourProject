package tour.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import tour.dao.TourDao;
import tour.dto.TourDto;
//주석
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
   
   private static String contentID = "";
   private static String tourName = "";

   public static String getContentID() {
      return contentID;
   }
   public static String getName(){
      return tourName;
   }

   @Override
   public void execute(Scanner scan) {
      String selectNum = "";
      Map<String, String> map = new HashMap<>();
      System.out.println("********************************************");
      System.out.println("              관광지 조회 서비스            ");
      System.out.println("********************************************");
      
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
      
      System.out.println("===============================================");
      System.out.println(" 선택하신 항목입니다.\n"+
                     "  시/도    : "+areaName+"\n"+
                     "  시/군/구 : "+sigunguName+"\n"+
                     "  관광타입 : "+typeName+"\n"+
                     "  분류 1   : "+cat1Name+"\n"+
                     "  분류 2   : "+cat2Name);
      System.out.println("===============================================");
      
      List<TourDto> list = TourDao.searchTour(areaCode, sigunguCode, typeCode, cat1Code, cat2Code);
      if(list.size()==0){
         System.out.println("              검색 결과가 없습니다.            ");
         System.out.println("===============================================");
      }else {
         int index = 1;
         for (int j = 0; j < list.size(); j++) {
            TourDto t = list.get(j);
            System.out.println(
                  " <"+(index++)+">\n"+
                  "-------------------------------------------------------------------\n"+
                  " 관광지명 : " + t.getTourName() +"\n"+
                  " 상세주소 : " + t.getAddr() +"\n"+
                  " 전화번호 : "+t.getTel() +"\n"+
                  "-------------------------------------------------------------------"
                  );
         }
      }
      System.out.print(" 회원 평가를 보기 위해 관광지 번호를 입력하세요 : ");
      String tourNum = scan.nextLine();
      System.out.println();
      this.contentID = list.get(Integer.parseInt(tourNum)-1).getContentID();
      this.tourName = list.get(Integer.parseInt(tourNum)-1).getTourName();
      new TourScoreSearchService().execute(scan);
      new MemHistoryInsertService().execute(scan);
      
   }
} // end of class