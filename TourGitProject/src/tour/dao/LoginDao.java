package tour.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import tour.util.DBUtil;
import tour.util.SHAUtil;

public class LoginDao {
	public static Map<String, Boolean> login(String email, String pwd) throws NoSuchAlgorithmException {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select name, pwd, salt from member where id = ?";
		boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			// pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			String name = null;
			if (rs.next()) {
				name= rs.getString("name");
				result = SHAUtil.decodePwd(rs, pwd);
			}
			map.put(name, result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
}
