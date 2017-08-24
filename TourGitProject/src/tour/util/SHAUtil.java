package tour.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class SHAUtil {
	public static List<String> encodePwd(String pwd) throws NoSuchAlgorithmException {
		List<String> list = new ArrayList<>();
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte [] random = new byte[10];
		sr.nextBytes(random);
		list.add(new String(random));
		MessageDigest sha1Sender = MessageDigest.getInstance("SHA-256");
//		sha1Sender.update(pwd.getBytes());
		sha1Sender.update(random);
		list.add(new String(sha1Sender.digest(pwd.getBytes())));
		return list;
	}

	public static boolean decodePwd(ResultSet rs, String pwd) throws NoSuchAlgorithmException, SQLException {
		boolean result = false;
		byte[] pwdd = null;
		MessageDigest sha1Receiver = MessageDigest.getInstance("SHA-256");
		if (rs.next()) {
//			sha1Receiver.update(pwd.getBytes());
			sha1Receiver.update(rs.getString("salt").getBytes());
			pwdd = rs.getString("pwd").getBytes();
		}
		System.out.println();
		byte[] sha1ReceiverText = sha1Receiver.digest(pwd.getBytes());
		result = MessageDigest.isEqual(pwdd, sha1ReceiverText);
		System.out.println(result);
		return result;
	}
}
