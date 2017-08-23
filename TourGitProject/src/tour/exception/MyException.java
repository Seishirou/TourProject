package tour.exception;

public class MyException extends RuntimeException {

	public MyException(String msg) {
		super(msg);
	}
	
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("=============================\n");
		sb.append("Error : "+super.getMessage());
		sb.append("\n=============================");
		return sb.toString();
	}
	
}//end of class
