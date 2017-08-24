package tour.dto;

public class MemHistoryDto {

	private int recordnum;
	private String email="";
	private String contentid;
	
	private String tourName;
	
	public MemHistoryDto(int recordnum, String email, String contentid) {
		super();
		this.recordnum = recordnum;
		this.email = email;
		this.contentid = contentid;
	}
	
	public MemHistoryDto(int recordnum, String email, String contentid, String tourName) {
		super();
		this.recordnum = recordnum;
		this.email = email;
		this.contentid = contentid;
		this.tourName = tourName;
	}
	public String getTourName(){
		return tourName;
	}
	public void setTourName(String tourName){
		this.tourName = tourName;
	}
	
	public int getRecordnum() {
		return recordnum;
	}
	public void setRecordnum(int recordnum) {
		this.recordnum = recordnum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContentid() {
		return contentid;
	}
	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	@Override
	public String toString() {
		return "[ "+email+"님의 여행정보 ]  = 번호 : " + recordnum + ", 여행정보번호" + contentid;
	}
	
	
	
}//end of class