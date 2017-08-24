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
		byte[] random = new byte[10];
		sr.nextBytes(random);
		MessageDigest sha1Sender = MessageDigest.getInstance("SHA-256");
		// sha1Sender.update(pwd.getBytes());
		sha1Sender.update(random);
		StringBuilder sbran = new StringBuilder();
		for (int i = 0; i < random.length; i++) {
			sbran.append(Integer.toString((random[i] & 0xff) + 0x100, 16).substring(1));
		}
		String ran = sbran.toString();
		list.add(ran);
		byte[] pwdd = sha1Sender.digest(pwd.getBytes());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pwdd.length; i++) {
			sb.append(Integer.toString((pwdd[i] & 0xff) + 0x100, 16).substring(1));
		}
		String generatedPassword = sb.toString();
		list.add(generatedPassword);
		return list;
	}

	public static boolean decodePwd(ResultSet rs, String pwd) throws NoSuchAlgorithmException, SQLException {
		boolean result = false;
		String dbpw = "";
		byte[] dbInpw = null;
		MessageDigest sha1Receiver = MessageDigest.getInstance("SHA-256");
		if (rs.next()) {
			String salt = rs.getString("salt");
			byte [] bytes = new byte[salt.length()/2];
			for (int i = 0; i < bytes.length; i++) {
				bytes[i] = (byte)Integer.parseInt(salt.substring(2 * i, 2 * i + 2), 16);
			}
			sha1Receiver.update(bytes);
			dbpw = rs.getString("pwd");
			dbInpw = new byte[dbpw.length()/2];
			for (int i = 0; i < dbInpw.length; i++) {
				dbInpw[i] = (byte)Integer.parseInt(dbpw.substring(2 * i, 2 * i + 2), 16);
			}
		}
		byte[] sha1ReceiverText = sha1Receiver.digest(pwd.getBytes());
		result = MessageDigest.isEqual(dbInpw, sha1ReceiverText);
		return result;
	}
}
