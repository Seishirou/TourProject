package tour.parse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import tour.exception.MyException;

public class SigunguParse implements Parse {

	@Override
	public void parse() {
		StringBuilder urlBuilder = new StringBuilder();
		try {
			urlBuilder.append("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode");
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
			+ "=DSWe82R9Pl7t5tzBtO949RNUX%2Fa4%2BPfuC6hNRN6BkRBo3DiRTFxPnZfrYIkMYdk%2BvAEkU8vjx3GsAheWxpHgJA%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&"+URLEncoder.encode("areaCode","UTF-8") +"=");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new MyException("UnsupportedEncodingException Error");
		}
		//numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTesting
	}

}
