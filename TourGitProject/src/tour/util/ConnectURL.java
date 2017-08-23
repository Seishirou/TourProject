package tour.util;

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class ConnectURL {
	public Document run(String uri) {
		Document document = null;
		try {
			URL url = new URL(uri);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// DocumentBuilder 생성해주는 팩토리
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

				// DocumentBuilder 생성
				DocumentBuilder builder = factory.newDocumentBuilder();

				document = builder.parse(new DataInputStream(conn.getInputStream())); // DOM 파싱 -> 메모리에 문서 트리
																								// 구축
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return document;
	}
}
