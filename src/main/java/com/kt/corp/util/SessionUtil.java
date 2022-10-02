package com.kt.corp.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

	/**
	 * 세션을 DTO단위로 생성함
	 * 
	 * @param request
	 * @param sessionName 세션명
	 * @param object      세션에 담을 MAP
	 */
	public static void setSession(HttpServletRequest request, String sessionName, Object object) {

		if (object != null) {
			HttpSession session = request.getSession();
			session = request.getSession(true);
			session.setAttribute(sessionName, object);
		}
	}

	/**
	 * 세션을 DTO단위로 반환함
	 * 
	 * @param request
	 * @param sessionName 세션명
	 * @param object      세션을 담을 MAP
	 * @return
	 */
	public static Map getSession(HttpServletRequest request, String sessionName, Object object) {

		HttpSession session = request.getSession();
		session = request.getSession(true);
		object = session.getAttribute(sessionName);

		return (Map) object;
	}

	/**
	 * 세션 초기화
	 * 
	 * @param request
	 * @param sessionName 세션명
	 */
	public static void clearSession(HttpServletRequest request, String sessionName) {

		HttpSession session = request.getSession();
		session = request.getSession(true);
		session.setAttribute("SESSIONDOMAIN", null);
		session.setAttribute(sessionName, null);
		session.invalidate();
	}

}
