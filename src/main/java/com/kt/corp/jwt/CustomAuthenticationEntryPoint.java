package com.kt.corp.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.kt.corp.Constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String exception = (String)request.getAttribute("exception");

        if(exception == null) 												setResponse(response, Constant.ERR_0001, "일반 오류");
    	else if(exception.equals(Constant.ERR_1001))		setResponse(response, Constant.ERR_1001, "잘못된 JWT 서명입니다.");
    	else if(exception.equals(Constant.ERR_1002))		setResponse(response, Constant.ERR_1002, "만료된 JWT 토큰입니다.");
    	else if(exception.equals(Constant.ERR_1003))		setResponse(response, Constant.ERR_1003, "지원되지 않는 JWT 토큰입니다.");
    	else if(exception.equals(Constant.ERR_1004))		setResponse(response, Constant.ERR_1004, "JWT 토큰이 잘못되었습니다.");
    	else if(exception.equals(Constant.ERR_1005))		setResponse(response, Constant.ERR_1005, "토큰 사용자 정보가 불일치입니다.");
    	
    	else if(exception.equals(Constant.ERR_1101))		setResponse(response, Constant.ERR_1101, "로그아웃된 사용자입니다.");
    	else if(exception.equals(Constant.ERR_1102))		setResponse(response, Constant.ERR_1102, "잘못된 비밀번호입니다.");
    	else if(exception.equals(Constant.ERR_1103))		setResponse(response, Constant.ERR_1103, "없는 사용자 정보입니다.");
    	else if(exception.equals(Constant.ERR_1104))		setResponse(response, Constant.ERR_1104, "미승인된 사용자 정보입니다.");
        
	}
	
	private void setResponse(HttpServletResponse response, String exceptionCode, String msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        JSONObject responseJson = new JSONObject();
        responseJson.put("status", "fail");
        responseJson.put("response", "");
        responseJson.put("errorCode", exceptionCode);
        responseJson.put("errorMessage", msg);

        response.getWriter().print(responseJson);
    }

}
