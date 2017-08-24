package tour.dto;

public class TourscoreDto {
private int score_num;
private String contentid;
private String id="";
private double traffic;
private double stay;
private double sisul;
private double food;
private double etc;
private String assessment="";

public TourscoreDto(int score_num, String contentid, String id, double traffic, double stay, double sisul, double food,
		double etc, String assessment) {
	super();
	this.score_num = score_num;
	this.contentid = contentid;
	this.id = id;
	this.traffic = traffic;
	this.stay = stay;
	this.sisul = sisul;
	this.food = food;
	this.etc = etc;
	this.assessment = assessment;
}
public int getScore_num() {
	return score_num;
}
public void setScore_num(int score_num) {
	this.score_num = score_num;
}
public String getContentid() {
	return contentid;
}
public void setContentid(String contentid) {
	this.contentid = contentid;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public double getTraffic() {
	return traffic;
}
public void setTraffic(double traffic) {
	this.traffic = traffic;
}
public double getStay() {
	return stay;
}
public void setStay(double stay) {
	this.stay = stay;
}
public double getSisul() {
	return sisul;
}
public void setSisul(double sisul) {
	this.sisul = sisul;
}
public double getFood() {
	return food;
}
public void setFood(double food) {
	this.food = food;
}
public double getEtc() {
	return etc;
}
public void setEtc(double etc) {
	this.etc = etc;
}
public String getAssessment() {
	return assessment;
}
public void setAssessment(String assessment) {
	this.assessment = assessment;
}

@Override
public String toString() {
	return "TourscoreDto [score_num=" + score_num + ", contentid=" + contentid + ", id=" + id + ", traffic=" + traffic
			+ ", stay=" + stay + ", sisul=" + sisul + ", food=" + food + ", etc=" + etc + ", assessment=" + assessment
			+ "]";
}

}//end of class
