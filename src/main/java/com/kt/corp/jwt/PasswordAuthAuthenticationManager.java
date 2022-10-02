package com.kt.corp.jwt;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.kt.corp.Constant;
import com.kt.corp.comm.ApiException;
import com.kt.corp.user.dto.UserDTO;
import com.kt.corp.user.mapper.UserMapper;
import com.kt.corp.user.service.UserService;
import com.kt.corp.util.DeEncrypter;

import lombok.RequiredArgsConstructor;

/*
 * 로그인시 사용자 인증 처리
 * 
 * */
@Component
@RequiredArgsConstructor
public class PasswordAuthAuthenticationManager implements AuthenticationProvider{
	
	@Autowired	UserMapper userMapper;
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		try {
			String userMail = authentication.getName();
			String userPw = (String)authentication.getCredentials();
			String encMail = DeEncrypter.encryptAES(userMail);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userMail", encMail);
			
			PasswordAuthenticationToken token = null;
			UserDTO userDTO = this.userMapper.selectUser(param);
			
			if(userDTO == null || userDTO.getNm().equals(""))																// 사용자 정보 없음.
				return new PasswordAuthenticationToken(new ApiException(Constant.ERR_1103, "없는 사용자 정보"), "없는 사용자 정보");
			
			param.put("pw", DeEncrypter.encSHA256(userPw + userDTO.getSalt()) );
			param.put("salt", userDTO.getSalt());
			
			userDTO = this.userMapper.selectUser(param);
			
			if(userDTO == null || userDTO.getNm().equals(""))																// 사용자 패스워드 틀림.
				return new PasswordAuthenticationToken(new ApiException(Constant.ERR_1102, "잘못된 비밀번호"), "잘못된 비밀번호");
			
			if(userDTO.getWrongPw() != null && Integer.parseInt(userDTO.getWrongPw()) >= 5)		// 비번 5회 이상 누적 
				return new PasswordAuthenticationToken(new ApiException(Constant.ERR_1105, "비밀번호 5회 이상 틀림"), "비밀번호 5회 이상 틀림");
			
			Map<String, Object> upParam = new HashMap<String, Object>();
			upParam.put("userMail", userMail);
			upParam.put("wrongPw", "0");
			
			this.userMapper.updateUserInfo(param);																			// 비밀번호 횟수 초기화
			
			token = new PasswordAuthenticationToken(userDTO.getMail(), userDTO.getPw(), Collections.singleton(new SimpleGrantedAuthority("role")));
			token.setRole("USER_ROLE");
			token.setName(userDTO.getUserId());
			
			return token;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ApiException(Constant.ERR_0001, e.getMessage());
		}
		
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		//return authentication.equals(PasswordAuthAuthenticationManager.class);
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
