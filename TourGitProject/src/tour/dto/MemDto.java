package tour.dto;

public class MemDto {
	private String email;
	private String pwd;
	private String name;
	private String birth;
	private String addr;
	private String cellNum;
	
	public MemDto() {
		// TODO Auto-generated constructor stub
	}

	public MemDto(String email, String pwd, String name, String birth, String addr, String cellNum) {
		this.email = email;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.addr = addr;
		this.cellNum = cellNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCellNum() {
		return cellNum;
	}

	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}
	
	
}
