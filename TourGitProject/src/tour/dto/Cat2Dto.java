package tour.dto;

public class Cat2Dto {
	@Override
	public String toString() {
		return "Cat2 [code=" + code + ", name=" + name + ", cat1Code=" + cat1Code + "]";
	}

	String code;
	String name;
	String cat1Code;
	
	public Cat2Dto(String code, String name, String cat1Code) {
		super();
		this.code = code;
		this.name = name;
		this.cat1Code = cat1Code;
	}

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

	public String getCat1Code() {
		return cat1Code;
	}

	public void setCat1Code(String cat1Code) {
		this.cat1Code = cat1Code;
	}
	
	

}
