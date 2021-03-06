package tour.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tour.dto.AreaDto;
import tour.exception.MyException;
import tour.util.DBUtil;

public class AreaDao {

	public static Map<Integer,String> put() {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<Integer,String> map = new HashMap<>();
		String sql = "select area_code from area";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int num = 0;
			while(rs.next()) {
//				AreaDto a = new AreaDto(
				map.get(num);
				map.get(rs.getString("area_code"));
				num++;
//						);
//				list.add(a);
			}
		} catch (SQLException e) {
			throw new MyException("Error! 시스템 오류! 데이터를 읽어 올 수 없습니다.");
		}finally {
			DBUtil.close(rs, pstmt, con);
		}
		
		return map;

	}
	
}
