package tour.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tour.dto.TourscoreDto;
import tour.util.DBUtil;

public class TourscoreDao {

	//싱글톤
	private static TourscoreDao dao;
	
	private TourscoreDao() {
		super();
	}
	
	public static TourscoreDao getInstance() {
		if(dao == null)
			dao = new TourscoreDao();
		return dao;
	}
	//싱클톤 End
	
	public int selectNum() {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int temp=0;
		String sql = "select max(score_num) from tourscore";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				temp=rs.getInt("max(score_num)");
			}
			System.out.println(temp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	public int insert(TourscoreDto s) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "insert into tourscore values(?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s.getScore_num());
			pstmt.setInt(2, s.getContentid());
			pstmt.setString(3, s.getId());
			pstmt.setDouble(4, s.getTraffic());
			pstmt.setDouble(5, s.getStay());
			pstmt.setDouble(6, s.getSisul());
			pstmt.setDouble(7, s.getFood());
			pstmt.setDouble(8, s.getEtc());
			pstmt.setString(9, s.getAssessment());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(pstmt, conn);
		}
		return result;
	}
	
}//end of class
