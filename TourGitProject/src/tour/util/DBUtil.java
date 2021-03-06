package tour.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tour.exception.MyException;

public class DBUtil {
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String CON_URL = "jdbc:oracle:thin:@192.168.0.135:1521:xe";
	private static final String USER_NAME = "tour";
	private static final String USER_PWD = "1234";

	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new MyException("Error! Connection");
		}
	}

	// 1. JDBC 드라이버 로드, 2. DB 서버 연결 - 별도의 메소드나 생성자로 만들 것
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(CON_URL, USER_NAME, USER_PWD);
		} catch (SQLException e) {
			throw new MyException("Error! Connection");
		}
		return con;
	}

	// 6. 자원 사용 종료
	public static void close(PreparedStatement pstmt, Connection con) {
		try {
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			throw new MyException("Error! Connection");
		}

	}

	public static void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			throw new MyException("Error! Connection");
		}
	}

}// end of class
