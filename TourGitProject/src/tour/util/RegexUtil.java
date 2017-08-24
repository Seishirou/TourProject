package tour.util;

public class RegexUtil {
	public static boolean emailCheck(String email) {
		String EMAIL_REGEX = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Boolean b = email.matches(EMAIL_REGEX);
		return b;
	}

	public static boolean cellNumCheck(String num) {
		String CELLNUM_REGEX = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$";
		Boolean b = num.matches(CELLNUM_REGEX);
		return b;
	}

	public static boolean pwdCheck(String pwd) {
		String PWD_REGEX = "[a-z0-9]{6,15}";
		Boolean b = pwd.matches(PWD_REGEX);
		return b;
	}

	public static boolean nameAndAddrCheck(String nameOrAddr) {
		String NAMEADDR_REGEX = "^[가-힣]*$";
		Boolean b = nameOrAddr.matches(NAMEADDR_REGEX);
		return b;
	}

	public static boolean birthCheck(String birth) {
		String BIRTH_REGEX = "^[1-2]{1}[0-9]{3}[0-1]{1}[0-9]{1}[0-3]{1}[0-9]{1}$";
		Boolean b = birth.matches(BIRTH_REGEX);
		return b;
	}
}
