package tour.dto;

public class AreaDto {

	private String area_code="";
	private String area_name="";
	
	public AreaDto(String area_code,String area_name) {
		super();
		this.area_code = area_code;
		this.area_name = area_name;
	}

	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	@Override
	public String toString() {
		return "[Area] : area_code = " + area_code + "\t area_name = " + area_name;
	}
	
	
	
}// end of class
