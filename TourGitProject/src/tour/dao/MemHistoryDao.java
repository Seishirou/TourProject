package tour.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import tour.dto.MemHistoryDto;
import tour.exception.MyException;
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
		} catch (SQLException e) {
			throw new MyException("Error! 시스템 오류! 데이터를 읽어 올 수 없습니다.");
		}finally {
			DBUtil.close(rs, pstmt, conn);
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
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getContentid());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MyException("Error! 시스템 오류!\n데이터를 잘못 입력하셨습니다.");
		}finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return result;

	}
	
	public List<MemHistoryDto> getHistory(String id){
		List<MemHistoryDto> list = new LinkedList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select m.recordnum, m.id, m.contentid, t.tour_name"+
				" from memberhistory m, tour t"+ 
				" where m.id='"+id+"' and m.contentid=t.contentid"+
				" order by recordnum";
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				list.add(new MemHistoryDto(
						rs.getInt("recordnum"),
						rs.getString("id"),
						rs.getString("contentid"),
						rs.getString("tour_name")));
			}
		} catch (SQLException e) {
			throw new MyException("Error! 시스템 오류! 데이터를 읽어 올 수 없습니다.");
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	}
}










