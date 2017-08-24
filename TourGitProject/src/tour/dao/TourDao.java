package tour.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import tour.dto.AreaDto;
import tour.dto.Cat1Dto;
import tour.dto.Cat2Dto;
import tour.dto.SigunguDto;
import tour.dto.TourDto;
import tour.dto.TourTypeDto;
import tour.exception.MyException;
import tour.util.DBUtil;

public class TourDao {
	
	public static List<AreaDto> searchArea() {
		List<AreaDto> list = new LinkedList<>();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from area";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(new AreaDto(rs.getString("area_code"),rs.getString("area_name")));
			}
		} catch (SQLException e) {
			throw new MyException("Error! 시스템 오류! 데이터를 읽어 올 수 없습니다.");
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	} // end of searchArea();
	
	public static List<SigunguDto> searchSigungu(String areaCode) {
		List<SigunguDto> list = new LinkedList<>();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from sigungu where area_code="+"'"+areaCode+"'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(new SigunguDto(rs.getString("sigungu_code"),
						rs.getString("sigungu_name"),
						rs.getString("area_code")));
			}
		} catch (SQLException e) {
			throw new MyException("Error! 시스템 오류! 데이터를 읽어 올 수 없습니다.");
			} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	} // end of searchSigungu();
	
	public static List<Cat1Dto> searchCat1(){
		List<Cat1Dto> list = new LinkedList<>();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from cat1";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(new Cat1Dto( rs.getString("cat1_code"),
						rs.getString("cat1_name") ) );
			}
		} catch (SQLException e) {
			throw new MyException("Error! 시스템 오류! 데이터를 읽어 올 수 없습니다.");
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	public static List<Cat2Dto> searchCat2(String cat1Code){
		List<Cat2Dto> list = new LinkedList<>();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from cat2 where cat1_code="+"'"+cat1Code+"'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(new Cat2Dto(rs.getString("cat2_code"),
						rs.getString("cat2_name"),
						rs.getString("cat1_code")));
			}
		} catch (SQLException e) {
			throw new MyException("Error! 시스템 오류! 데이터를 읽어 올 수 없습니다.");
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	public static List<TourTypeDto> searchTourType(){
		List<TourTypeDto> list = new LinkedList<>();
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from tourtype order by type_code";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(new TourTypeDto( rs.getString("type_code"),
						rs.getString("type_name") ) );
			}
		} catch (SQLException e) {
			throw new MyException("Error! 시스템 오류! 데이터를 읽어 올 수 없습니다.");
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	public static List<TourDto> searchTour(String areaCode, String sigunguCode, String typeCode, String cat1Code, String cat2Code){
		List<TourDto> list = new LinkedList<>();
		
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
				list.add(new TourDto(
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
			throw new MyException("Error! 시스템 오류! 데이터를 읽어 올 수 없습니다.");
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	}
} // end of class










