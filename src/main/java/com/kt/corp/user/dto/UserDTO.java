package com.kt.corp.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "사용자 DTO")
public class UserDTO {
	
	/**
	 * 사용자 ID
	 */
	@Schema(description = "사용자 ID", example = "SDDWF-DFE3F")
	private String userId;
	
	/**
	 * 이메일 주소
	 */
	@Schema(description = "이메일 주소", example = "kim@genievalley.com")
	private String mail;
	
	/**
	 * 이름
	 */
	@Schema(description = "이름", example = "김밸리")
	private String nm;
	
	/**
	 * 비밀번호
	 */
	@Schema(description = "비밀번호", example = "desdF33&dQ")
	private String pw;
	
	/**
	 * IP
	 */
	@Schema(description = "IP", example = "0.0.0.0")
	private String ip;
	
	/**
	 * SALT
	 */
	@Schema(description = "SALT", example = "dwredgb44323gred")
	private String salt;
	
	/**
	 * 비밀번호 누적 횟수
	 */
	@Schema(description = "비밀번호 누적 횟수", example = "0")
	private String wrongPw;
	
	/**
	 * 사용자 상태 코드
	 */
	@Schema(description = "사용자 상태 코드", example = "CODE01")
	private String userStateCode;
	
	/**
	 * 사용자 상태
	 */
	@Schema(description = "사용자 상태", example = "업무중")
	private String userState;
	
	/**
	 * 명함 보내기 가능 여부
	 */
	@Schema(description = "명함 보내기 가능 여부", example = "true or false")
	private Boolean availSendBizcard;
	
	/**
	 * 진입 타입
	 */
	@Schema(description = "진입 타입", example = "PC")
	private int entryType;

}
