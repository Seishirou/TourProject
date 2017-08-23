package tour.dto;

public class SiGunGuDto {
	private String cityCode;
	private String sigunguCode;
	private String sigunguName;
	
	public SiGunGuDto() {
		// TODO Auto-generated constructor stub
	}

	public SiGunGuDto(String cityCode, String sigunguCode, String sigunguName) {
		this.cityCode = cityCode;
		this.sigunguCode = sigunguCode;
		this.sigunguName = sigunguName;
	}

	@Override
	public String toString() {
		return "SiGunGu [cityCode=" + cityCode + ", sigunguCode=" + sigunguCode + ", sigunguName=" + sigunguName + "]";
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

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

	
	
	
}
