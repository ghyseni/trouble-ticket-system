package application.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author gresehyseni
 * 
 *         Final Project - 12/09/2017
 * 
 *         Intended to provides helpful methods in general to be used by other
 *         classes. Right now includes the password hashing method
 */
public class Util {

	/**
	 * Gets the plain text password, and coverts it into hash using SH-256
	 * algorithm.
	 * 
	 * @param plainPassword
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String hashPassword(String plainPassword) throws NoSuchAlgorithmException {

		String hash = null;

		MessageDigest messageDigest;
		messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(plainPassword.getBytes());

		byte byteData[] = messageDigest.digest();

		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			stringBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		hash = stringBuffer.toString();

		return hash;

	}
}
