package com.kt.corp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.kt.corp.jwt.CustomAuthenticationEntryPoint;
import com.kt.corp.jwt.JwtAccessDeniedHandler;
import com.kt.corp.jwt.JwtAuthenticationEntryPoint;
import com.kt.corp.jwt.JwtSecurityConfig;
import com.kt.corp.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig  {
	
	@Autowired private final TokenProvider tokenProvider;
	@Autowired private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
    	// Swagger 접근 설정
        return (web) -> web.ignoring().antMatchers(
        		"/v3/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger/**", "/swagger-ui/**", "/swagger-ui/index.html"
        		);
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    	return httpSecurity
    			.httpBasic().disable()						// security에서 기본으로 생성하는 login페이지 사용 안 함
    			.csrf().disable()								// Rest API 통신이므로 csrf 사용 안함.
    			.cors().configurationSource(this.coresConfigurationSource()).and()			// 크로스 도메인 등록

		        // exception handling 할 때 커스텀 클래스를 추가
		        .exceptionHandling()
		        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
		        .accessDeniedHandler(jwtAccessDeniedHandler)
		        .and()
		        .headers()
		        .frameOptions()
		        .sameOrigin()
		
		        // 시큐리티는 기본적으로 세션을 사용
		        // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
		        .and()
		        .sessionManagement()
		        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		
		        // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
		        .and()
		        .authorizeRequests()
		        .antMatchers("/user",  "/user/login",  "/user/reissue", "/user/chkMail", "/user/resendMail", "/user/pw", "/user/guest", "/user/modifyGuest","/user/delGuest", "/user/captcha"
		        		,"/profile/guest/**" , "/nobd/**",  "/help/**", "/file/guest", "/building/home", "/stomp", "/res/**", "/metaoffice/**").permitAll()	
		        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		        .anyRequest().authenticated()
		        
		        //.anyRequest().permitAll()
		        
		        // 예외처리
		        .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
		
		        // JwtAuthFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
		        .and()
		        .apply(new JwtSecurityConfig(tokenProvider))
		        .and().build();
    }
    
    @Bean
    public CorsConfigurationSource coresConfigurationSource() {
    	CorsConfiguration corsConfiguration = new CorsConfiguration();
    	
    	corsConfiguration.addAllowedOriginPattern("*");
    	corsConfiguration.addAllowedHeader("*");
    	corsConfiguration.addAllowedMethod("*");
    	corsConfiguration.setAllowCredentials(true);
    	corsConfiguration.setMaxAge(3600L);
    	
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", corsConfiguration);
    	return source;
    }
    
}











