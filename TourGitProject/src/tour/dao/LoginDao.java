package tour.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tour.util.DBUtil;
import tour.util.SHAUtil;

public class LoginDao {
	public static Boolean login(String email, String pwd) throws NoSuchAlgorithmException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select name, pwd, salt from member where id = ?";
		boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
//			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			result = SHAUtil.decodePwd(rs, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}
}
