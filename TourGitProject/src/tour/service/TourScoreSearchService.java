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
      
      System.out.println("'"+sts.getName()+"'"+"의 회원평가");
      
      List<TourscoreDto> list = TourscoreDao.getInstance().getInfo(sts.getContentID());
      int size = list.size();
      for(TourscoreDto t : list){
         traffic += t.getTraffic();
         stay += t.getStay();
         sisul += t.getSisul();
         food += t.getFood();
         etc += t.getEtc();
      }
      
      if(size==0){
    	  System.out.println(" 회원 평가가 존재하지 않습니다. ");
      }else{
//    	  System.out.printf(
//    	            "-----------------------------------------------\n 교통 : %.1f\n 숙박 : %.1f\n 시설 : %.1f\n 음식 : %.1f\n 기타 : %.1f\n-----------------------------------------------\n",
//    	            traffic,stay,sisul,food,etc
//    	            );
    	  System.out.println("-----------------------------------------------");
    	  System.out.println(" 교통 : "+getStar(traffic/size));
    	  System.out.println(" 숙박 : "+getStar(stay/size));
    	  System.out.println(" 시설 : "+getStar(sisul/size));
    	  System.out.println(" 음식 : "+getStar(food/size));
    	  System.out.println(" 기타 : "+getStar(etc/size));
    	  System.out.println("-----------------------------------------------");
    	  for (int i = 0; i < list.size(); i++) {
              System.out.println(
                    list.get(i).getId().substring(0, 3)+"**** : "+list.get(i).getAssessment()
                    );
              
           }
      }
   }
   
   public String getStar(double d){
	   StringBuilder sb = new StringBuilder();
	   double temp = d;
	   
	   int frontDot = (int)temp;
	   double backDot = d-frontDot;
	   
	   for (int i = 0; i < frontDot; i++) {
		   sb.append("★");
	   }
	   if( backDot >= 0.5 ){
		   sb.append("☆");
	   }
	   return new String(sb);
   }
}





