package com.kt.corp.help.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "헬프데스크 DTO")
public class HelpDTO {
	
	/**
	 * 문의글 번호
	 */
	@Schema(description = "문의글 번호", example = "1")
	private Long seq;
	
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
	 * 사용자 이메일
	 */
	@Schema(description = "사용자 이메일", example = "xxx@genie.com")
	private String mail;
	
	/**
	 * 문의글 제목
	 */
	@Schema(description = "문의글 제목", example = "사용 문의")
	private String tit;
	
	/**
	 * 문의글 내용
	 */
	@Schema(description = "문의글 내용", example = "지니밸리 이용 문이 드립니다.")
	private String dtl;
	
	/**
	 * 문의글 등록 일시
	 */
	@Schema(description = "문의글 등록 일시", example = "2022-08-09 09:00:00")
	private String regDate;
	
}