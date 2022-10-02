package com.kt.corp.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
//import java.util.Base64;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

//import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
//import com.sun.org.apache.xml.internal.security.utils.Base64;

@SuppressWarnings("restriction")
public final class DeEncrypter {
	private static DeEncrypter instance = new DeEncrypter();

	private Cipher cipher;
	private Key key;
	
	private static final String AES_KEY = "a1s2d3f4g5h6j7k8l9!";

	public static DeEncrypter getInstance() {

		return DeEncrypter.instance;
	}

	private DeEncrypter() {

		try {
			this.cipher = Cipher.getInstance("AES");
			/*byte[] raw = { (byte) 0xA5, (byte) 0x01, (byte) 0x7B, (byte) 0xE5,
					(byte) 0x23, (byte) 0xCA, (byte) 0xD4, (byte) 0xD2,
					(byte) 0xC6, (byte) 0x5F, (byte) 0x7D, (byte) 0x8B,
					(byte) 0x0B, (byte) 0x9A, (byte) 0x3C, (byte) 0xF1 };
			*/
			SecureRandom random = new SecureRandom();
			byte[] keyData = new byte[16];
			random.nextBytes(keyData);
			
			this.key = new SecretKeySpec(keyData, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.hashCode();
		} catch (NoSuchPaddingException e) {
			e.hashCode();
		}
	}

	public String encrypt(String aData) {
		String result = "";
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] utf8 = aData.getBytes("UTF8");
			byte[] encryptedData = cipher.doFinal(utf8);
			//result = Base64.encode(encryptedData);//this.b64Encoder.encode(encryptedData);
		}
		catch (InvalidKeyException oException) { 			oException.hashCode(); }
		catch (IllegalBlockSizeException oException) { 		oException.hashCode(); }
		catch (BadPaddingException oException) { 			oException.hashCode(); }
		catch (IOException oException) { 					oException.hashCode(); }
		return result;
	}
	
	public String decrypt(String aData) {
		String result = "";
			try {
				cipher.init(Cipher.DECRYPT_MODE, key);
				//byte[] decodedData = null;
//				byte[] decodedData = Base64.decode(aData);//this.b64Decoder.decodeBuffer(aData);
				byte[] decodedData = null;
				byte[] utf8 = cipher.doFinal(decodedData);
				result = new String(utf8, "UTF8");
			}
			catch (InvalidKeyException oException) { 			oException.hashCode(); }
			//catch (Base64DecodingException oException) { 		oException.hashCode(); }
			catch (IllegalBlockSizeException oException) { 		oException.hashCode(); }
			catch (BadPaddingException oException) { 			oException.hashCode(); }
			catch (UnsupportedEncodingException oException) { 	oException.hashCode(); }
		return result;
	}
	
	public static String encSHA256(String planText) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(planText.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	/*
	 * public static String encryptAES256(String text) throws Exception { Cipher
	 * cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); SecretKeySpec keySpec =
	 * new SecretKeySpec(AES_KEY.getBytes(), "AES"); IvParameterSpec ivParamSpec =
	 * new IvParameterSpec(AES_KEY.substring(0, 16).getBytes());
	 * cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
	 * 
	 * byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8")); return
	 * Base64.getEncoder().encodeToString(encrypted); }
	 * 
	 * public String decryptAES256(String cipherText) throws Exception { Cipher
	 * cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); SecretKeySpec keySpec =
	 * new SecretKeySpec(AES_KEY.getBytes(), "AES"); IvParameterSpec ivParamSpec =
	 * new IvParameterSpec(AES_KEY.substring(0, 16).getBytes());
	 * cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
	 * 
	 * byte[] decodedBytes = Base64.getDecoder().decode(cipherText); byte[]
	 * decrypted = cipher.doFinal(decodedBytes); return new String(decrypted,
	 * "UTF-8"); }
	 */
    
  //MD5 암호화와 Hex(16진수) 인코딩
  	public static String shaAndHex(String plainText, String Algorithms) throws NoSuchAlgorithmException, UnsupportedEncodingException {
  		MessageDigest md = MessageDigest.getInstance(Algorithms);
  		md.update(plainText.getBytes("utf-8"));
  		return Hex.encodeHexString(md.digest());
  	}
  	
  	//MD5 암호화와 Base64 인코딩
  	public static String shaAndBase64(String plainText, String Algorithms) throws NoSuchAlgorithmException, UnsupportedEncodingException {
  		MessageDigest md = MessageDigest.getInstance(Algorithms);
  		md.update(plainText.getBytes("utf-8"));
  		return Base64.encodeBase64String(md.digest());
  	}
  	
  	public static String encryptAES(String str) throws Exception {
  		String mixPassword = "a1s2d3f4g5h6j7k8l9!";
  		MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
		messageDigest.reset();
		messageDigest.update(mixPassword.getBytes("utf8"));
		
		String sha = shaAndHex(mixPassword, "SHA-512");
		Cipher encryptCipher = Cipher.getInstance("AES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, generaKey(sha, "UTF-8"));
		
		return new String( Hex.encodeHex(encryptCipher.doFinal(str.getBytes("UTF-8")))).toUpperCase();
  	}
  	
	public static SecretKeySpec generaKey(String key, String enc) {
		byte[] finalKey = new byte[16];
		int i = 0;
		
		try {
			for(byte b : key.getBytes(enc)) {
				finalKey[i++%16] ^= b;
			}
			
			return new SecretKeySpec(finalKey, "AES");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/*
	public static void main(String[] args) throws Exception {
		String mail = "aaa@naver.ocm";
		String mixPassword = "a1s2d3f4g5h6j7k8l9!";
		
		String m = encSHA256("!qazxsw2amNplXISxkGXRpDuieXaUQ==");
		
		System.out.println(">> " + m);
	}
	*/

	
	
}










