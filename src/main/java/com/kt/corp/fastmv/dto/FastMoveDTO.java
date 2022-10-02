package com.kt.corp.fastmv.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "빠른 이동 DTO")
public class FastMoveDTO {
	/**
	 * 사용자 ID
	 */
	@Schema(description = "사용자 ID", example = "SDDWF-DFE3F")
	private String userId;
	
	/**
	 * 회사 ID
	 */
	@Schema(description = "회사 ID", example = "ABCDE")
	private String comId;
	
	/**
	 * 회사명
	 */
	@Schema(description = "회사명", example = "지니벨리")
	private String comNm;
	
	/**
	 * 상단고정
	 */
	@Schema(description = "상단고정", example = "1")
	private String topFixed;
	
	/**
	 * 회사 유형
	 */
	@Schema(description = "회사 유형", example = "경영지원")
	private String comType;
	
	/**
	 * 회사 로고
	 */
	@Schema(description = "회사 로고", example = "")
	private String imgLog;
	
	/**
	 * 오피스 접근 권한
	 */
	@Schema(description = "오피스 접근 권한", example = "Y")
	private String officeAuth;
	
	/**
	 * 오피스 주소
	 */
	@Schema(description = "오피스 주소", example = "")
	private String officeUrlPath;
	
	/**
	 * 웹 접근 권한
	 */
	@Schema(description = "웹 접근 권한", example = "Y")
	private String webAuth;
	
	/**
	 * 웹 주소
	 */
	@Schema(description = "웹 주소", example = "")
	private String webUrlPath;
	
	
}













