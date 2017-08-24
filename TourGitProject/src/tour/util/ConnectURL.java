package tour.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import tour.exception.MyException;

public class ConnectURL {
	public Document run(String uri) {
		Document document = null;
		URL url;
		try {
			url = new URL(uri);
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
		} catch (MalformedURLException e) {
			throw new MyException("Error! --- MalformedURLException");
		} catch (ProtocolException e) {
			throw new MyException("Error! --- ProtocolException");
		} catch (IOException e) {
			throw new MyException("Error! --- IOException");
		} catch (ParserConfigurationException e) {
			throw new MyException("Error! --- ParserConfigurationException");
		} catch (SAXException e) {
			throw new MyException("Error! --- SAXException");
		}

		return document;
	}
}