package tour.service;

import java.util.List;
import java.util.Scanner;

import tour.dao.TourscoreDao;
import tour.dto.TourscoreDto;
//주석
public class TourScoreSearchService implements Service{

   @Override
   public void execute(Scanner sc) {
      SearchTourService sts = new SearchTourService();
      double traffic = 0.0;;
      double stay = 0.0;
      double sisul = 0.0;
      double food = 0.0;
      double etc = 0.0;
      
      System.out.println("'"+sts.getName()+"'"+"의 상세정보");
      
      List<TourscoreDto> list = TourscoreDao.getInstance().getInfo(sts.getContentID());
      int size = list.size();
      for(TourscoreDto t : list){
         traffic += t.getTraffic();
         stay += t.getStay();
         sisul += t.getSisul();
         food += t.getFood();
         etc += t.getEtc();
      }
      
      System.out.printf(
            "------------------------------------\n 교통 : %.1f\n 숙박 : %.1f\n 시설 : %.1f\n 음식 : %.1f\n 기타 : %.1f\n------------------------------------\n",
            traffic,stay,sisul,food,etc
            );
      if(list.size() < 6){
         for (int i = 0; i < list.size(); i++) {
            System.out.println(
                  list.get(i).getId().substring(0, 3)+"**** : "+list.get(i).getAssessment()
                  );
         }
         
      }else{
         for (int i = 0; i < 5; i++) {
            System.out.println(
                  list.get(i).getId().substring(0, 3)+"**** : "+list.get(i).getAssessment()
                  );
         }
      }
   }
}