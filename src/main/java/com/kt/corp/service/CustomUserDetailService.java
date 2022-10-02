package com.kt.corp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kt.corp.user.dto.UserDTO;
import com.kt.corp.user.mapper.UserMapper;


@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userMail", username);
		
		UserDTO userDto = this.userMapper.selectUser(params);
		
		User user = null;
		
		if(userDto == null)  throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		else return user;
	}

}
