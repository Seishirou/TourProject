package tour.dto;

public class CityDto {
	private String cityCode;
	private String cityName;
	
	public CityDto() {
		// TODO Auto-generated constructor stub
	}

	public CityDto(String cityNum, String cityName) {
		super();
		this.cityCode = cityNum;
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "City [cityNum=" + cityCode + ", cityName=" + cityName + "]";
	}

	public String getCityNum() {
		return cityCode;
	}

	public void setCityNum(String cityNum) {
		this.cityCode = cityNum;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	

	

	
	
	
}
