package tour.dto;

public class Cat1 {
	private String code;
	private String name;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return "Cat1 [code=" + code + ", name=" + name + "]";
	}
	public Cat1(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	
	
	

}
