package tour.dto;

public class MemHistoryDto {

	private int recordnum;
	private String email="";
	private int contentid;
	
	
	
	public MemHistoryDto(int recordnum, String email, int contentid) {
		super();
		this.recordnum = recordnum;
		this.email = email;
		this.contentid = contentid;
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
	public int getContentid() {
		return contentid;
	}
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}

	@Override
	public String toString() {
		return "[ "+email+"님의 여행정보 ]  = 번호 : " + recordnum + ", 여행정보번호" + contentid;
	}
	
	
	
}//end of class