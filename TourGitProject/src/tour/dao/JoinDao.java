package tour.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import tour.exception.MyException;
import tour.dto.MemDto;
import tour.util.DBUtil;
import tour.util.SHAUtil;

public class JoinDao {
	
	public static int insertMem(MemDto m) throws NoSuchAlgorithmException {
		Connection con = DBUtil.getConnection();
		CallableStatement cstmt = null;
		String sql = "{call proc_mem_insert(?, ?, ?, ?, ?, ?, ?)";
		int result = 0;
		try {
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, m.getEmail());
			List<String> list = SHAUtil.encodePwd(m.getPwd());
			
			cstmt.setString(2, list.get(1));
			cstmt.setString(3, m.getName());
			cstmt.setString(4, m.getBirth());
			cstmt.setString(5, m.getAddr());
			cstmt.setString(6, m.getCellNum());
			cstmt.setString(7, list.get(0));
			result = cstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MyException("회원 가입에 실패했습니다");
		} finally {
			DBUtil.close(cstmt, con);
		}
		return result;
	}
	


}
