package tour.parse;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import tour.dto.Cat2Dto;
import tour.exception.MyException;
import tour.util.DBUtil;

public class Cat2Parse {

	public static void parsing(String cat1Code) {

		List<Cat2Dto> list = new LinkedList<>();

		FileInputStream fis = null;
		try {
			Properties prop = new Properties();
			fis = new FileInputStream("tour.properties");
			prop.load(fis);

			StringBuilder urlBuilder = new StringBuilder(prop.getProperty("catURL")); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "="
					+ prop.getProperty("serviceKey")); /* Service Key */
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
			urlBuilder.append(
					"&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTesting", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("30", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("cat1", "UTF-8") + "=" + URLEncoder.encode(cat1Code, "UTF-8"));

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

				DocumentBuilder builder = factory.newDocumentBuilder();

				Document document = builder.parse(new DataInputStream(conn.getInputStream()));
				Element root = document.getDocumentElement();
				NodeList nodeList = root.getElementsByTagName("item");
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element e = (Element) node;
						Node code = e.getElementsByTagName("code").item(0);
						Node name = e.getElementsByTagName("name").item(0);

						list.add(new Cat2Dto(code.getFirstChild().getTextContent(),
								name.getFirstChild().getTextContent(), cat1Code));
					}
				}
				insert(list);
			}

		} catch (UnsupportedEncodingException e) {
			throw new MyException("데이터 Parsing 실패 --- UnsupportedEncodingException");
		} catch (MalformedURLException e) {
			throw new MyException("데이터 Parsing 실패 --- MalformedURLException");
		} catch (FileNotFoundException e1) {
			throw new MyException("데이터 Parsing 실패 --- FileNotFoundException");
		} catch (IOException e1) {
			throw new MyException("데이터 Parsing 실패 --- IOException");
		} catch (ParserConfigurationException e) {
			throw new MyException("데이터 Parsing 실패 --- ParserConfigurationException");
		} catch (SAXException e) {
			throw new MyException("데이터 Parsing 실패 --- SAXException");
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				throw new MyException("Error!");
			}
		}

	}

	public static void insert(List<Cat2Dto> list) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into cat2 values(?,?,?)";
		int result = 0;

		try {

			pstmt = conn.prepareStatement(sql);

			for (Cat2Dto c : list) {
				pstmt.setString(1, c.getCat1Code());
				pstmt.setString(2, c.getCode());
				pstmt.setString(3, c.getName());

				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new MyException("Parsing 데이터 Insert 실패 --- SQLException");
		} finally {
			DBUtil.close(pstmt, conn);
		}
	}

	public static void start() {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select cat1_code from cat1";

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String cat1Code = rs.getString("cat1_code").trim();
				System.out.println(cat1Code);
				parsing(cat1Code);
			}

		} catch (SQLException e) {
			throw new MyException("Error --- SQLException");
		} finally {
			DBUtil.close(pstmt, conn);
		}

	}
}
