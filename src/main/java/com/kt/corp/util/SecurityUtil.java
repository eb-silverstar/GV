package com.kt.corp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class SecurityUtil {

	/**
	 * 인코딩
	 * @param inStr
	 * @return
	 */
	public static String encode(String inStr) {
		if (StringUtils.isBlank(inStr)) return inStr;
		DeEncrypter de=DeEncrypter.getInstance();
		String result=null;
		try {
			result=URLEncoder.encode(de.encrypt(inStr), "UTF-8").replaceAll("%", "_");
		} catch (UnsupportedEncodingException e) {
			 e.hashCode();
		}

		return result;
	}
	
	/**
	 * 디코딩
	 * @param inStr
	 * @return
	 */
	public static String decode(String inStr) {
		if (StringUtils.isBlank(inStr)) return inStr;
		DeEncrypter de=DeEncrypter.getInstance();
		String result=null;
		try {
			result=de.decrypt(URLDecoder.decode(inStr.replaceAll("_", "%"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.hashCode();
		}
		return result;
	}
	
	/**
	 * 인코딩URL
	 * @param inStr
	 * @return
	 */
	public static String encodeURL(HttpServletRequest request, String inStr) {
		if(StringUtils.isBlank(inStr)) return inStr;
		DeEncrypter de=DeEncrypter.getInstance();
		String result = null;
		try {
			result = URLEncoder.encode(de.encrypt(inStr), "UTF-8").replaceAll("%", "_");
		} catch (UnsupportedEncodingException e) {
			e.hashCode();
		}
		
		return result;
	}
	
//	public static void main(String[] args) throws Exception {
//		String seed="한글_abc 1234##";
//		String seed="_1!한글 # aby\\t";
//		String test = "kbk0908";
//		String enc=encode(test);
//		String dec=decode(enc); 
//		System.out.println(encode("kbk0908")); 
//		System.out.println(dec);
//		
//		System.out.println("테스트 입니다.");
//	}
}
