package tour.parse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tour.exception.MyException;
import tour.util.ConnectURL;
import tour.util.DBUtil;

public class TourParse {

	public static void main(String[] args) {
		StringBuilder urlBuilder = new StringBuilder();
		
		try {
			urlBuilder.append("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
					+ "=DSWe82R9Pl7t5tzBtO949RNUX%2Fa4%2BPfuC6hNRN6BkRBo3DiRTFxPnZfrYIkMYdk%2BvAEkU8vjx3GsAheWxpHgJA%3D%3D");
//			urlBuilder.append("&" + URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("50000", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
			urlBuilder
			.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTesting", "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			throw new MyException("Error!");
		}
		Connection connection = null;
		PreparedStatement pstmt = null;
		Document document = new ConnectURL().run(urlBuilder.toString());
		String sql = "insert into tour values(?,?,?,?,?,?,?,?,?)";
		try {
			connection = DBUtil.getConnection();
			pstmt = connection.prepareStatement(sql);
		} catch (SQLException e2) {
			throw new MyException("Error!");
		}
		int result = 0;
		Element root = document.getDocumentElement(); // 루트
		NodeList nodeList = root.getElementsByTagName("item"); // 루트의 자식들
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) node;
				// 추출 : 해당 태그명 가진 엘리먼트를 반환
				String[] getArr = { "contentid","title","addr1","tel", "areacode","sigungucode","cat1", "cat2","contenttypeid"};
				Node temp = null;
				for (int j = 0; j < getArr.length; j++) {
					try {
						if ((temp = e.getElementsByTagName(getArr[j]).item(0)) != null) {
							pstmt.setString(j+1, temp.getFirstChild().getTextContent());
							//temp.getFirstChild().getTextContent());
						} else {
							pstmt.setString(j+1, "x");
						}
					} catch (SQLException e1) {
						throw new MyException("Error!");
					}
				}
				try {
					result = pstmt.executeUpdate();
				} catch (SQLException e1) {
					throw new MyException("Error!");
				}
			}
		}
	}// end of main
}// end of class
