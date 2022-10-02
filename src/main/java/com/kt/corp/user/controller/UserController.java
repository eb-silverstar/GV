package com.kt.corp.user.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.corp.Constant;
import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.jwt.TokenDTO;
import com.kt.corp.message.manage.DbMessageManager;
import com.kt.corp.user.dto.GuestDTO;
import com.kt.corp.user.dto.UserDTO;
import com.kt.corp.user.service.UserService;

import io.jsonwebtoken.io.IOException;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "User", description = "사용자 API")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Value("${capcha.server-key}")
	String capchaServerKey;
	
	@Autowired
	RestTemplateBuilder builder;
	
	@Autowired
	UserService userService;
	
	/**
	 * 이메일 유효성 체크
	 * 
	 * @param mail
	 * @return
	 */
	@Operation(summary = "이메일 유효성 체크", description = "이메일 유효성을 체크합니다.")
	@GetMapping("/chkMail")
	public ResponseEntity<ApiResponseEntity> chkUserMail(@RequestParam String mail) {
		String result = userService.chkUserMail(mail);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 메일 주소로 사용자 검색 (명함 보내기 위한 검색용)
	 * 
	 * @param mail
	 * @return
	 */
	@Operation(summary = "메일 주소로 사용자 검색 (명함 보내기 위한 검색용)", description = "메일 주소로 사용자를 검색합니다.")
	@GetMapping("/search")
	public ResponseEntity<ApiResponseEntity> getUserByMail(@RequestParam String mail, @RequestParam String senderId) {
		UserDTO user = userService.getUserByMail(mail, senderId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), user, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사용자 등록 (회원 가입)
	 * @param userDto
	 * @return
	 */
	@Operation(summary = "사용자 등록 (회원가입)", description = "신규 사용자를 등록합니다.")
	@PostMapping
	public ResponseEntity<ApiResponseEntity> postUser(@RequestBody UserDTO userDto) {
		Map<String, String> user = userService.insertUser(userDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), user, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 메일 인증 처리
	 * 
	 * @param mail
	 * @return
	 */
	@Operation(summary = "메일 인증 처리", description = "메일 인증 처리를 합니다.")
	@PostMapping("/certMail")
	public ResponseEntity<ApiResponseEntity> certMail(@RequestBody Map param) {
		UserDTO userDTO = this.userService.certificationMail(param.get("userMail").toString());
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), userDTO, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사용자 정보 조회
	 * 
	 * @param mail
	 * @return
	 */
	@Operation(summary = "사용자 조회", description = "사용자를 조회합니다.")
	@GetMapping("/findUser")
	public ResponseEntity<ApiResponseEntity> getUser(@RequestParam String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		
		UserDTO userDTO = userService.getUser(params);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), userDTO, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 인증 메일 재발송
	 * 
	 * @param mail
	 * @return
	 */
	@Operation(summary = "인증 메일 재발송", description = "인증 메일 재발송 합니다.")
	@GetMapping("/resendMail")
	public ResponseEntity<ApiResponseEntity> resendMail(@RequestParam String userMail) {
		UserDTO userDto = this.userService.sendMail(userMail, Constant.MAIL_SEND_CERT, null);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), userDto, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 비밀번호 찾기
	 * 
	 * @param mail
	 * @return
	 */
	@Operation(summary = "비밀번호 찾기", description = "사용자 비밀번호 찾기 입니다.")
	@GetMapping("/pw")
	public ResponseEntity<ApiResponseEntity> findUserPwd(@RequestParam String userMail) {
		UserDTO userDto = this.userService.findUserPwd(userMail);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), userDto, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 로그인
	 * 
	 * @param mail
	 * @return
	 */
	@Operation(summary = "로그인 처리", description = "사용자 로그인 처리를 합니다.")
	@PostMapping("/login")
	public ResponseEntity<ApiResponseEntity> userLogIn(@RequestBody UserDTO userDto) {
		Map<String, Object> result = this.userService.userLogin(userDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 토큰 재발급
	 * 
	 * @param TokenDTO
	 * @return
	 */
	@Operation(summary = "토큰 재발급", description = "사용자 토큰을 재발급 합니다.")
	@PostMapping("/reissue")
	public ResponseEntity<ApiResponseEntity> reissue(@RequestBody TokenDTO tokenDTO) {
		TokenDTO token = this.userService.reissue(tokenDTO);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), token, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사용자 로그아웃
	 * 
	 * @param mail
	 * @return
	 */
	@Operation(summary = "로그아웃", description = "사용자 로그아웃 처리를 합니다.")
	@GetMapping("/logout")
	public ResponseEntity<ApiResponseEntity> userLogOut(@RequestParam String userId) {
		int result = this.userService.deleteUserToken(userId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 비밀번호 변경
	 * 
	 * @param TokenDTO
	 * @return
	 */
	@Operation(summary = "사용자 비밀번호 변경", description = "사용자 비밀번호 변경을 합니다.")
	@PostMapping("/modify-pwd")
	public ResponseEntity<ApiResponseEntity> changePwd(@RequestBody HashMap<String, Object> map) {
		UserDTO userDTO = this.userService.changePwd(map);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), userDTO, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 게스트 정보 입력
	 * 
	 * @param nickNm
	 * @return int
	 */
	@Operation(summary = "게스트 정보 입력", description = "게스트 정보를 입력합니다.")
	@PostMapping("/guest")
	public ResponseEntity<ApiResponseEntity> insGuest(@RequestBody GuestDTO guestDTO) {
		GuestDTO result = this.userService.insertGuest(guestDTO);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
//	/**
//	 * 게스트 정보 조회
//	 * 
//	 * @param guestNickNm
//	 * @return GuestDTO
//	 */
//	@Operation(summary = "게스트 정보 조회", description = "게스트 정보를 조회 합니다.")
//	@GetMapping("/guest")
//	public ResponseEntity<ApiResponseEntity> searchGuest(@RequestParam String guestNickNm) {
//		GuestDTO result = this.userService.getGuest(guestNickNm);
//		
//		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
//		
//		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
//	}
	
	/**
	 * 게스트 정보 삭제
	 * 
	 * @param guestNickNm
	 * @return int
	 */
	@Operation(summary = "게스트 정보 삭제", description = "게스트 정보를 삭제 합니다.")
	@PostMapping("/remove-guest")
	public ResponseEntity<ApiResponseEntity> removeGuest(@RequestParam String guestId) {
		int result = this.userService.deleteGuest(guestId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
//	/**
//	 * 게스트 정보 변경
//	 * 
//	 * @param guestNickNm
//	 * @return int
//	 */
//	@Operation(summary = "게스트 정보 변경", description = "게스트 정보를 변경 합니다.")
//	@PostMapping("/modifyGuest")
//	public ResponseEntity<ApiResponseEntity> modifyGuest(@RequestBody GuestDTO guestDTO) {
//		int result = this.userService.updateGuest(guestDTO);
//		
//		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
//		
//		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
//	}

	/**
	 * 사용자 상태 조회
	 * 
	 * @param userId
	 * @return UserDTO
	 */
	@Operation(summary = "사용자 상태 조회", description = "사용자 상태를 조회 합니다.")
	@GetMapping("/userStat")
	public ResponseEntity<ApiResponseEntity> searchUserStat(@RequestParam String userId) {
		UserDTO result = this.userService.getUserStat(userId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사용자 상태 변경
	 * 
	 * @param userId
	 * @return int
	 */
	@Operation(summary = "사용자 상태 변경", description = "사용자 상태 변경 합니다.")
	@PostMapping("/modify-userStat")
	public ResponseEntity<ApiResponseEntity> modifyGuest(@RequestBody UserDTO userDTO) {
		int result = this.userService.modifyUserStat(userDTO);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * google 캡차
	 * 
	 * @param token
	 * @return JSONObject
	 * @throws Exception 
	 */
	@Operation(summary = "google 캡차", description = "google 캡차 요청합니다.")
	@PostMapping("/captcha")
	public ResponseEntity<ApiResponseEntity> captcha(@RequestParam String token) throws Exception {
		JSONObject json = this.userService.captcha(token);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), json, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
}
