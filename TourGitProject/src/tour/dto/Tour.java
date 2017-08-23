package tour.dto;

public class Tour {
	private String contentID;
	private String tourName;
	private String addr;
	private String tel;
	private String areaCode;
	private String sigunguCode;
	private String cat1Code;
	private String cat2Code;
	private String typeCode;
	public String getContentID() {
		return contentID;
	}
	public void setContentID(String contentID) {
		this.contentID = contentID;
	}
	public String getTourName() {
		return tourName;
	}
	public void setTourName(String tourName) {
		this.tourName = tourName;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getSigunguCode() {
		return sigunguCode;
	}
	public void setSigunguCode(String sigunguCode) {
		this.sigunguCode = sigunguCode;
	}
	public String getCat1Code() {
		return cat1Code;
	}
	public void setCat1Code(String cat1Code) {
		this.cat1Code = cat1Code;
	}
	public String getCat2Code() {
		return cat2Code;
	}
	public void setCat2Code(String cat2Code) {
		this.cat2Code = cat2Code;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public Tour(String contentID, String tourName, String addr, String tel, String areaCode, String sigunguCode,
			String cat1Code, String cat2Code, String typeCode) {
		super();
		this.contentID = contentID;
		this.tourName = tourName;
		this.addr = addr;
		this.tel = tel;
		this.areaCode = areaCode;
		this.sigunguCode = sigunguCode;
		this.cat1Code = cat1Code;
		this.cat2Code = cat2Code;
		this.typeCode = typeCode;
	}
	
	
	
	
}
