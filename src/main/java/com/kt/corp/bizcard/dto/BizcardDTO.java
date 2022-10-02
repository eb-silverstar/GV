package com.kt.corp.bizcard.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "받은 명함 DTO")
public class BizcardDTO {
	
	/**
	 * 받은 명함 Seq
	 */
	@Schema(description = "받은 명함 Seq", example = "1")
	private Long seq;
	
	/**
	 * 수신자 ID
	 */
	@Schema(description = "수신자 ID", example = "USER0001")
	private String receiverId;

	/**
	 * 송신자 ID
	 */
	@Schema(description = "송신자 ID", example = "USER0001")
	private String senderId;
	
	/**
	 * 송신자 이름
	 */
	@Schema(description = "송신자 이름", example = "김밸리")
	private String nm;
	
	/**
	 * 송신자 회사 ID
	 */
	@Schema(description = "송신자 회사 ID", example = "COM0001")
	private String comId;
	
	/**
	 * 송신자 회사 이름
	 */
	@Schema(description = "송신자 회사 이름", example = "지니밸리")
	private String comNm;
	
	/**
	 * 송신자 소속 팀
	 */
	@Schema(description = "송신자 소속 팀", example = "디자인팀")
	private String team;
	
	/**
	 * 송신자 직급
	 */
	@Schema(description = "송신자 직급", example = "대리")
	private String jobTit;
	
	/**
	 * 송신자 모바일
	 */
	@Schema(description = "송신자 모바일", example = "010-000-0000")
	private String phoneNum;
	
	/**
	 * 송신자 이메일
	 */
	@Schema(description = "송신자 이메일", example = "genie@valley.com")
	private String mail;
	
	/**
	 * 요청 일시
	 */
	@Schema(description = "요청 일시", example = "2022-09-07 00:00:00")
	private String reqDate;
	
	/**
	 * 요청 장소
	 */
	@Schema(description = "요청 장소", example = "이벤트 공간")
	private String reqSpace;
	
	/**
	 * 수락 여부
	 */
	@Schema(description = "수락 여부", example = "true or false")
	private Boolean acceptFlag;
	
	/**
	 * 프라이빗 그룹 ID
	 */
	@Schema(description = "프라이빗 그룹 ID (필요시에만 전달)", example = "1")
	private Long grpId;
	
}
