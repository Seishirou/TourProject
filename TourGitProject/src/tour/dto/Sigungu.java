package tour.dto;

public class Sigungu {
	private String sigunguCode;
	private String sigunguName;
	private String areaCode;
	public String getSigunguCode() {
		return sigunguCode;
	}
	public void setSigunguCode(String sigunguCode) {
		this.sigunguCode = sigunguCode;
	}
	public String getSigunguName() {
		return sigunguName;
	}
	public void setSigunguName(String sigunguName) {
		this.sigunguName = sigunguName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Sigungu(String sigunguCode, String sigunguName, String areaCode) {
		super();
		this.sigunguCode = sigunguCode;
		this.sigunguName = sigunguName;
		this.areaCode = areaCode;
	}
	
	

}
