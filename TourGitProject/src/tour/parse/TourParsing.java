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

import tour.dto.TourDto;
import tour.util.DBUtil;
import tour.util.URLUtil;

public class TourParsing {
	
	public static void parsing(){
		
		List<TourDto> list = new LinkedList<>();
		
		try {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream("tour.properties");
			prop.load(fis);
			
			StringBuilder urlBuilder = new StringBuilder(prop.getProperty("tourURL")); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")+"="+prop.getProperty("serviceKey")); 
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTesting", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("50000", "UTF-8"));
			System.out.println(urlBuilder);
			
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
						Node contentID = e.getElementsByTagName("contentid").item(0); // ���� : �ش� �±׸� ���� ������Ʈ�� ��ȯ
						Node tourName = e.getElementsByTagName("title").item(0);
						Node addr = e.getElementsByTagName("addr1").item(0);
						Node tel = e.getElementsByTagName("tel").item(0);
						Node areaCode = e.getElementsByTagName("areacode").item(0);
						Node sigunguCode = e.getElementsByTagName("sigungucode").item(0);
						Node cat1Code = e.getElementsByTagName("cat1").item(0);
						Node cat2Code = e.getElementsByTagName("cat2").item(0);
						Node typeCode = e.getElementsByTagName("contenttypeid").item(0);
						
						String strContentID = contentID !=null ?contentID.getFirstChild().getTextContent():"x";
						String strtourName = tourName !=null ? tourName.getFirstChild().getTextContent():"x";
						String strAddr = addr !=null ? addr.getFirstChild().getTextContent():"x";
						String strTel =	tel !=null ? tel.getFirstChild().getTextContent():"x";
						String strAreaCode = areaCode !=null ? areaCode.getFirstChild().getTextContent():"x ";
						String strSigunguCode = sigunguCode !=null ? sigunguCode.getFirstChild().getTextContent():"x   ";
						String strCat1Code = cat1Code !=null ? cat1Code.getFirstChild().getTextContent():"x    ";
						String strCat2Code = cat2Code !=null ? cat2Code.getFirstChild().getTextContent():"x         ";
						String strTypeCode = typeCode !=null ? typeCode.getFirstChild().getTextContent():"x ";
						
						System.out.println(
								strContentID+"  "+
								strtourName+"  "+
								strAddr+"  "+
								strTel+"  "+
								strAreaCode+"  "+
								strSigunguCode+"  "+
								strCat1Code+"  "+
								strCat2Code+"  "+
								strTypeCode
								);
						
						try{
							new Thread().sleep(5);
						}catch(Exception err){
							err.printStackTrace();
						}
						
						
						list.add( new TourDto(
								strContentID,
								strtourName,
								strAddr,
								strTel,
								strAreaCode,
								strSigunguCode,
								strCat1Code,
								strCat2Code,
								strTypeCode
						));
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
	
	public static void insert(List<TourDto> list){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into tour2 values(?,?,?,?,?,?,?,?,?)";
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
//			private String contentID;
//			private String tourName;
//			private String addr;
//			private String tel;
//			private String areaCode;
//			private String sigunguCode;
//			private String cat1Code;
//			private String cat2Code;
//			private String typeCode;
			for(TourDto t : list){
				pstmt.setString(1, t.getContentID());
				pstmt.setString(2, t.getTourName());
				pstmt.setString(3, t.getAddr());
				pstmt.setString(4, t.getTel());
				pstmt.setString(5, t.getAreaCode());
				pstmt.setString(6, t.getSigunguCode());
				pstmt.setString(7, t.getCat1Code());
				pstmt.setString(8, t.getCat2Code());
				pstmt.setString(9, t.getTypeCode());
				
				result = pstmt.executeUpdate();
			}
			
			System.out.println(result+"�� �Ϸ�");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void start(){
		parsing();
	}
	
}// end of class
