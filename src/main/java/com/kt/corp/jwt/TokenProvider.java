package com.kt.corp.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.kt.corp.Constant;
import com.kt.corp.comm.ApiException;
import com.kt.corp.user.mapper.UserMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

/*
 * 
 * JWT 토큰 관련 암호화, 복호화, 검증 클래스
 * 
 * 
 * */
@Slf4j
@Component
public class TokenProvider {
	
	private static final String AUTHORITIES_KEY = "auth";
	private static final String BEARER_TYPE = "bearer";
	private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 120;            		// 2시간
	private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 1;  // 1일

	private final Key key;
	
	@Autowired private UserMapper userMapper;
	
	
	public TokenProvider(@Value("${jwt.base64.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

	public TokenDTO generateTokenDto(Authentication authentication) {
		//  권한 리스트
		String authorities =  authentication.getAuthorities().stream()
										.map(GrantedAuthority::getAuthority)
										.collect(Collectors.joining(","));
		
		long now = (new Date()).getTime();
		
		// 엑세스 토큰 생성
		Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())       		// payload "sub": "name"
                .claim(AUTHORITIES_KEY, authorities)        		// payload "auth": "ROLE_USER"
                .setExpiration(accessTokenExpiresIn)        		// payload "exp": 1516239022 (예시)
                .signWith(key, SignatureAlgorithm.HS256)    	// header "alg": "HS512"
                .compact();
        
        // 리플레쉬 토큰 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        
        return TokenDTO.builder()
                //.grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .refreshToken(refreshToken)
                .build();
	}
	
	public Authentication getAuthentication(String accessToken) {
        Claims claims = this.parseClaims(accessToken);							// 토큰 복호화

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        
        Collection<? extends GrantedAuthority> authorities =				// 클레임에서 권한 정보 가져오기
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
	
	public Authentication getReflashAuthentication(String accessToken) {
		Claims claims = this.parseClaims(accessToken);							// 토큰 복호화
		
		Collection<? extends GrantedAuthority> authorities = 
				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
		        .map(SimpleGrantedAuthority::new)
		        .collect(Collectors.toList());
		
		UserDetails principal = new User(claims.getSubject(), "", authorities);
		
		return new UsernamePasswordAuthenticationToken(principal, "",  authorities);
	}
	
	public boolean validateToken(String token) {
		Authentication authentication = this.getAuthentication(token);
		Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", authentication.getName());
        
        Map<String, Object> tokenMap = this.userMapper.selectUserToken(param);
        if(tokenMap == null) 	throw new ApiException(Constant.ERR_1101, "로그아웃 된 사용자입니다.");
        
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw new ApiException(Constant.ERR_1001, e.getMessage());
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            throw new ApiException(Constant.ERR_1002, e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw new ApiException(Constant.ERR_1003, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
            throw new ApiException(Constant.ERR_1004, e.getMessage());
        }
    }
	
	public boolean reflashValidateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw new ApiException(Constant.ERR_1001, e.getMessage());
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            throw new ApiException(Constant.ERR_1002, e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw new ApiException(Constant.ERR_1003, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
            throw new ApiException(Constant.ERR_1004, e.getMessage());
        }
    }
	
	private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}





