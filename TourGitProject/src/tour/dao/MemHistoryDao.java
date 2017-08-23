package tour.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tour.dto.MemHistoryDto;
import tour.util.DBUtil;

public class MemHistoryDao {
	
	//싱글톤
	private static MemHistoryDao dao;
	
	private MemHistoryDao() {
		super();
	}
	public static MemHistoryDao getInstance() {
		if(dao==null)
			dao = new MemHistoryDao();
		return dao;
		
	}
//싱글톤 End
	
	public int selectNum() {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int temp=0;
		String sql = "select max(recordnum) from memberhistory";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				temp=rs.getInt("max(recordnum)");
			}else {
				temp=0;
			}
			System.out.println(temp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(pstmt, conn);
		}
		return temp;
	}
	
	public int insert(MemHistoryDto m) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "insert into memberhistory values(?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, m.getRecordnum());
			pstmt.setString(2, m.getContentid());
			pstmt.setInt(3, m.getRecordnum());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
}
