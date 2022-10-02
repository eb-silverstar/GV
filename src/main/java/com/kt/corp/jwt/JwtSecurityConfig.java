package com.kt.corp.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

	private final TokenProvider tokenProvider;

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		// TODO Auto-generated method stub
		JwtAuthFilter jwtFilter = new JwtAuthFilter(tokenProvider);
		builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
}
