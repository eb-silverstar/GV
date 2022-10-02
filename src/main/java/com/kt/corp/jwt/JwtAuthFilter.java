package com.kt.corp.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kt.corp.Constant;
import com.kt.corp.comm.ApiException;
import com.kt.corp.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter{
	
	@Autowired private UserMapper userMapper;
	
    private final TokenProvider tokenProvider;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jwt = resolveToken(request);
		
		try {
			// 사용자의 토큰 유효성 검사 진행 후 Authentication 가져와서 SecurityContextHolder 저장
			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				Authentication authentication = tokenProvider.getAuthentication(jwt);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (ApiException e) {
			// TODO: handle exception
			request.setAttribute("exception", e.getMsgCode());
		}

        filterChain.doFilter(request, response);
	}

	private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constant.AUTHORIZATION_HEADER);
        if (bearerToken!=null && !"".equals(bearerToken)) {
        	if(StringUtils.hasText(bearerToken.toUpperCase()) && bearerToken.toUpperCase().startsWith(Constant.AUTH_PREFIX))
        		return bearerToken.substring(7);
        }
        return null;
    }
}
