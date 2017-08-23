package tour.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import tour.dto.Area;
import tour.dto.Cat1;
import tour.dto.Cat2;
import tour.dto.Sigungu;
import tour.dto.Tour;
import tour.dto.TourType;
import tour.util.DBUtil;

public class TourDAO {
	
	public static List<Area> searchArea() {
		List<Area> list = new LinkedList<>();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from area";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(new Area(rs.getString("area_code"),rs.getString("area_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	} // end of searchArea();
	
	public static List<Sigungu> searchSigungu(String areaCode) {
		List<Sigungu> list = new LinkedList<>();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from sigungu where area_code="+"'"+areaCode+"'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(new Sigungu(rs.getString("sigungu_code"),
						rs.getString("sigungu_name"),
						rs.getString("area_code")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	} // end of searchSigungu();
	
	public static List<Cat1> searchCat1(){
		List<Cat1> list = new LinkedList<>();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from cat1";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(new Cat1( rs.getString("cat1_code"),
						rs.getString("cat1_name") ) );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	public static List<Cat2> searchCat2(String cat1Code){
		List<Cat2> list = new LinkedList<>();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from cat2 where cat1_code="+"'"+cat1Code+"'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(new Cat2(rs.getString("cat2_code"),
						rs.getString("cat2_name"),
						rs.getString("cat1_code")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	public static List<TourType> searchTourType(){
		List<TourType> list = new LinkedList<>();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from tourtype order by type_code";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(new TourType( rs.getString("type_code"),
						rs.getString("type_name") ) );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	public static List<Tour> searchTour(String areaCode, String sigunguCode, String typeCode, String cat1Code, String cat2Code){
		List<Tour> list = new LinkedList<>();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select * from tour where");
		sqlBuilder.append(" area_code="+"'"+areaCode+"'");
		
		if( !"선택안함".equals(sigunguCode) ){
			sqlBuilder.append(" and sigungu_code="+"'"+sigunguCode+"'");
		}
		if( !"선택안함".equals(typeCode) ){
			sqlBuilder.append(" and type_code="+"'"+typeCode+"'");
		}
		if( !"선택안함".equals(cat1Code) ){
			sqlBuilder.append(" and cat1_code="+"'"+cat1Code+"'");
		}
		if( !"선택안함".equals(cat2Code) ){
			sqlBuilder.append(" and cat2_code="+"'"+cat2Code+"'");
		}
		
		String sql = new String(sqlBuilder);
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				list.add(new Tour(
						rs.getString("contentid"),
						rs.getString("tour_name"),
						rs.getString("addr"),
						rs.getString("tel"),
						rs.getString("area_code"),
						rs.getString("sigungu_code"),
						rs.getString("cat1_code"),
						rs.getString("cat2_code"),
						rs.getString("type_code")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	}
} // end of class










