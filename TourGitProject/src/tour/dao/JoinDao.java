package tour.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import tour.exception.MyException;
import tour.dto.MemDto;
import tour.util.DBUtil;

public class JoinDao {
	
	public static int insertMem(MemDto m) {
		Connection con = DBUtil.getConnection();
		CallableStatement cstmt = null;
		String sql = "{call proc_mem_insert(?, ?, ?, ?, ?, ?)";
		int result = 0;
		try {
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, m.getEmail());
			cstmt.setString(2, m.getPwd());
			cstmt.setString(3, m.getName());
			cstmt.setString(4, m.getBirth());
			cstmt.setString(5, m.getAddr());
			cstmt.setString(6, m.getCellNum());
			result = cstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MyException("회원 가입에 실패했습니다");
		} finally {
			DBUtil.close(con, cstmt);
		}
		return result;
	}
}
