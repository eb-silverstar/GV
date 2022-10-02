package com.kt.corp.util;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoginManager implements HttpSessionBindingListener {
	
	private static LoginManager loginManager = null;
	
	@SuppressWarnings("rawtypes")
	private static Hashtable loginUsers = new Hashtable();
	
	private LoginManager() {
		super();
	}
	
	public static synchronized LoginManager getInstance() {
		if(loginManager == null) {
			loginManager = new LoginManager();
		}
		return loginManager;
	}
	
	// 해당 세션에 이미 로그인 되어있는지 체크
	@SuppressWarnings("rawtypes")
	public boolean isLogin(String sessionId) {
		boolean isLogin = false;
		Enumeration e = loginUsers.keys();
		String key = "";
		
		while(e.hasMoreElements()) {
			key = (String) e.nextElement();
			if(sessionId.equals(key)) {
				isLogin = true;
			}
		}
		return isLogin;
	}
	
	// 중복로그인 막기위해 아이디 사용 체크
	@SuppressWarnings("rawtypes")
	public boolean isUsing(String userId) {
		boolean isUsing = false;
		Enumeration e = loginUsers.keys();
		String key = "";
		
		while(e.hasMoreElements()) {
			key = (String) e.nextElement();
			if(userId.equals(loginUsers.get(key))) {
				isUsing = true;
			}
		}
		return isUsing;
	}
	
	// 세션 생성
	@SuppressWarnings({ "static-access", "unchecked" })
	public void setSession(HttpSession session, String userId) {
		loginUsers.put(session.getId(), userId);
		session.setAttribute("login", this.getInstance());
	}
	
	// 세션 성립될때
	public void valueBound(HttpSessionBindingEvent event) {
		
	}
	
	// 세션 끊길때
	public void valueUnbound(HttpSessionBindingEvent event) {
		loginUsers.remove(event.getSession().getId());
	}
	
	// 세션 ID로 로그인된 ID 구분
	public String getUserId(String sessionId) {
		return (String) loginUsers.get(sessionId);
	}
	
	// 현재 접속자수
	public int getUserCount() {
		return loginUsers.size();
	}

}
