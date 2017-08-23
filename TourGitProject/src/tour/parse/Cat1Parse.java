package tour.parse;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import tour.dto.Cat1Dto;
import tour.util.DBUtil;
import tour.util.URLUtil;



public class Cat1Parse {

	public static void parsing() {
		List<Cat1Dto> list = new LinkedList<>();
		try {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream("tour.properties");
			prop.load(fis);
			
			StringBuilder urlBuilder = new StringBuilder(prop.getProperty("catURL")); /*URL*/
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+prop.getProperty("serviceKey")); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); 
			urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTesting", "UTF-8")); 
			urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("30", "UTF-8")); 
			
			HttpURLConnection conn = URLUtil.getConnection(urlBuilder);
	        
	        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				
				// DocumentBuilder �������ִ� ���丮
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				
				// DocumentBuilder ����
				DocumentBuilder builder = factory.newDocumentBuilder();
				
				Document document = builder.parse(new DataInputStream(conn.getInputStream())); 	// DOM �Ľ� -> �޸𸮿� ���� Ʈ�� ����
				Element root = document.getDocumentElement(); 	// ��Ʈ
				NodeList nodeList = root.getElementsByTagName("item"); // ��Ʈ�� �ڽĵ�
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if( node.getNodeType() == Node.ELEMENT_NODE ){
						Element e = (Element)node;
						Node code = e.getElementsByTagName("code").item(0); // ���� : �ش� �±׸� ���� ������Ʈ�� ��ȯ
						Node name = e.getElementsByTagName("name").item(0);
						
						list.add( new Cat1Dto( code.getFirstChild().getTextContent(),
								name.getFirstChild().getTextContent() ));
					}
				}
				System.out.println(list);
				insert(list);
	        }
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void insert(List<Cat1Dto> list){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into cat1 values(?,?)";
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			for(Cat1Dto c : list){
				pstmt.setString(1, c.getCode());
				pstmt.setString(2, c.getName());
				System.out.println(sql);
				result += pstmt.executeUpdate();
			}
			System.out.println(result+"�� ���� �Ϸ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	}
	
	public static void start(){
		parsing();
	}

}

















