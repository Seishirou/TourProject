package tour.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import tour.exception.MyException;
import tour.dto.MemDto;
import tour.util.DBUtil;

public class JoinDao {
	
	public static int insertMem(MemDto m) throws NoSuchAlgorithmException {
		Connection con = DBUtil.getConnection();
		CallableStatement cstmt = null;
		String sql = "{call proc_mem_insert(?, ?, ?, ?, ?, ?, ?)";
		int result = 0;
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte [] random = new byte[10];
		sr.nextBytes(random);
		String salt = new String(random);
		try {
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, m.getEmail());
			cstmt.setString(2, encodePwd(m.getPwd(), random));
			cstmt.setString(3, m.getName());
			cstmt.setString(4, m.getBirth());
			cstmt.setString(5, m.getAddr());
			cstmt.setString(6, m.getCellNum());
			cstmt.setString(7, salt);
			result = cstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MyException("회원 가입에 실패했습니다");
		} finally {
			DBUtil.close(cstmt, con);
		}
		return result;
	}
	
	public static String encodePwd(String pwd, byte[] random) throws NoSuchAlgorithmException{
		MessageDigest sha1Sender = MessageDigest.getInstance("SHA-256");
		sha1Sender.update(pwd.getBytes());
		sha1Sender.update(random);
		String hashPwd = new String(sha1Sender.digest(pwd.getBytes()));
		return hashPwd;
	}

	public static boolean emailCheck(String email) {
		String EMAIL_REGEX = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Boolean b = email.matches(EMAIL_REGEX);
		return b;
	}

	public static boolean cellNumCheck(String num) {
		String EMAIL_REGEX = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$";
		Boolean b = num.matches(EMAIL_REGEX);
		return b;
	}

	public static boolean pwdCheck(String pwd) {
		String EMAIL_REGEX = "[a-z0-9]{6,15}";
		Boolean b = pwd.matches(EMAIL_REGEX);
		return b;
	}

	public static boolean nameAndAddrCheck(String nameOrAddr) {
		String EMAIL_REGEX = "^[가-힣]*$";
		Boolean b = nameOrAddr.matches(EMAIL_REGEX);
		return b;
	}

	public static boolean birthCheck(String birth) {
		String EMAIL_REGEX = "^[1-2]{1}[0-9]{3}[0-1]{1}[0-9]{1}[0-3]{1}[0-9]{1}$";
		Boolean b = birth.matches(EMAIL_REGEX);
		return b;
	}
}
