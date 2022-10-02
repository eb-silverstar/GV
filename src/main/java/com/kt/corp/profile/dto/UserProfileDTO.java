package com.kt.corp.profile.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "회원 프로필 DTO")
public class UserProfileDTO {
	
	/**
	 * 사용자 ID
	 */
	@Schema(description = "사용자 ID", example = "USER001")
	private String userId;
	
	/**
	 * 사용자 이름
	 */
	@Schema(description = "사용자 이름", example = "김밸리")
	private String nm;
	
	/**
	 * 사용자 소개
	 */
	@Schema(description = "사용자 소개", example = "지니밸리 운영팀 사원입니다.")
	private String intro;
	
	/**
	 * 사용자 연락처
	 */
	@Schema(description = "사용자 연락처", example = "010-000-0000")
	private String phoneNum;
	
	/**
	 * 사용자 이메일
	 */
	@Schema(description = "사용자 이메일", example = "kim@valley.com")
	private String mail;
	
	/**
	 * 전문분야 목록
	 */
	@Schema(description = "전문분야 목록", example = "[\"메타버스\", \"인공지능\"]")
	private List<String> specialtyList;
	
	/**
	 * 회사 이름
	 */
	@Schema(description = "회사 이름", example = "지니밸리")
	private String comNm;
	
	/**
	 * 소속팀
	 */
	@Schema(description = "소속팀", example = "운영팀")
	private String team;
	
	/**
	 * 직책
	 */
	@Schema(description = "직책", example = "사원")
	private String jobTit;
	
	/**
	 * 사용자 상태 코드
	 */
	@Schema(description = "사용자 상태 코드", example = "CODE001")
	private String userStateCode;
	
	/**
	 * 프로필 공개 여부
	 */
	@Schema(description = "프로필 공개 여부", example = "true or false")
	private boolean pfOnOff;
	
	/**
	 * 회사 관리자 권한 여부
	 */
	@Schema(description = "회사 관리자 권한 여부", example = "true or false")
	private boolean auth;

}
