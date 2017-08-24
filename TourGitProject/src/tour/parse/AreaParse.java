package tour.parse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tour.exception.MyException;
import tour.util.ConnectURL;
import tour.util.DBUtil;

public class AreaParse {
	
	public int parse() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		StringBuilder urlBuilder = new StringBuilder();

		try {
			urlBuilder.append("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode");
			urlBuilder.append("?"+URLEncoder.encode("ServiceKey","UTF-8")+"=DSWe82R9Pl7t5tzBtO949RNUX%2Fa4%2BPfuC6hNRN6BkRBo3DiRTFxPnZfrYIkMYdk%2BvAEkU8vjx3GsAheWxpHgJA%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("50", "UTF-8"));
			urlBuilder.append("&"+URLEncoder.encode("MobileOS","UTF-8")+"="+URLEncoder.encode("ETC","UTF-8"));
			urlBuilder.append("&"+URLEncoder.encode("MobileApp","UTF-8")+"="+URLEncoder.encode("AppTesting","UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new MyException("데이터 Parsing 실패");
		}
		Document document = new ConnectURL().run(urlBuilder.toString());
		String sql = "insert into area values(?,?)";
		
		try {
			connection = DBUtil.getConnection();
			pstmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			throw new MyException("데이터 Parsing 실패");
		}
			
		Element root = document.getDocumentElement();
		NodeList nodelist = root.getElementsByTagName("item");
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
			if(node.getNodeType()==Node.ELEMENT_NODE) {
				Element e = (Element) node;
				
				String[] getArr = {"code","name"};
				Node temp = null;
				for (int j = 0; j < getArr.length; j++) {
					if((temp = e.getElementsByTagName(getArr[j]).item(0)) != null) {
						try {
							pstmt.setString(j+1, temp.getFirstChild().getTextContent());
						} catch (DOMException e1) {
							throw new MyException("데이터 Parsing 실패 --- DOMException");
						} catch (SQLException e1) {
							throw new MyException("데이터 Parsing 실패 --- SQLException");
						}
					}else {
						try {
							pstmt.setString(j+1, "x");
						} catch (SQLException e1) {
							throw new MyException("데이터 Parsing 실패 --- SQLException");
						}
					}
				}
				try {
					result = pstmt.executeUpdate();
				} catch (SQLException e1) {
					throw new MyException("데이터 Parsing 실패 --- SQLException");
				}
			}
		}
		DBUtil.close(pstmt, connection);
		return result;
			
	}
	
}//end of class
