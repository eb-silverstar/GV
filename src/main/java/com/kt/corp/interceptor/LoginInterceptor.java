package com.kt.corp.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kt.corp.comm.BaseComm;
import com.kt.corp.util.LoginManager;
import com.kt.corp.util.SessionUtil;


@Component
public class LoginInterceptor extends BaseComm implements HandlerInterceptor{

	protected final Log log = LogFactory.getLog(getClass());
	
	@Override
    public boolean preHandle(HttpServletRequest request, 
                             HttpServletResponse response, 
                             Object handler) throws Exception {
		return true;
    }
 
    @SuppressWarnings("rawtypes")
	@Override
    public void postHandle(HttpServletRequest request, 
                           HttpServletResponse response, 
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
    	/*
    	if(request.getAttribute("interceptor") != null && StringUtils.equals(request.getAttribute("interceptor").toString(),"Y")) {
    		Map sessionMap = SessionUtil.getSession(request, "uvo", new HashMap());
    		LoginManager loginManager = LoginManager.getInstance();
    		HttpSession session = ((HttpServletRequest)request).getSession();
    		
    		if(sessionMap != null) {
    		    String eno = sessionMap.get("eno").toString();
    			if(!StringUtils.isEmpty(eno)) {
    				
    				//로그인 완료
    				if(!loginManager.isUsing(eno)) {
    					loginManager.setSession(session, eno);
    					
    					this.logger.debug("=============LoginInterceptor Start=============");
    					this.logger.debug("session data : "+ sessionMap);
    					this.logger.debug("user id : "+ eno);
    				}
    			}
    			
    			eno = null;
    		}
    	} 
    	*/   	
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, 
                                HttpServletResponse response, 
                                Object handler, 
                                Exception ex) throws Exception {
    }

}
