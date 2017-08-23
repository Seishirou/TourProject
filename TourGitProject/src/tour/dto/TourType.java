package tour.dto;

public class TourType {
	private String typeCode;
	private String typeName;
	public TourType(String typeCode, String typeName) {
		super();
		this.typeCode = typeCode;
		this.typeName = typeName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	

}
