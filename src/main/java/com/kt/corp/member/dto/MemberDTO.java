package com.kt.corp.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "멤버 DTO")
public class MemberDTO {
	
	/**
	 * 사용자 ID
	 */
	@Schema(description = "사용자 ID", example = "USER0001")
	private String userId;
	
	/**
	 * 사용자 이름
	 */
	@Schema(description = "사용자 이름", example = "김밸리")
	private String nm;
	
	/**
	 * 회사 이름
	 */
	@Schema(description = "회사 이름", example = "Meta-X")
	private String comNm;
	
	/**
	 * 직위
	 */
	@Schema(description = "직위", example = "대표")
	private String jobTit;
	
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
	 * 소속 팀
	 */
	@Schema(description = "소속 팀", example = "디자인팀")
	private String team;
	
	/**
	 * 등록일시
	 */
	@Schema(description = "등록일시", example = "2022-08-31 00:00:00")
	private String regDate;
	
	/**
	 * 회사 관리자
	 */
	@Schema(description = "회사 관리자", example = "true or false")
	private Boolean auth;

}
