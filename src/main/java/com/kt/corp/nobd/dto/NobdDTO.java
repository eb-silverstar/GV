package com.kt.corp.nobd.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "공지사항 DTO")
public class NobdDTO {
	
	/**
	 * 게시글 번호
	 */
	@Schema(description = "[U] 공지사항 Seq", example = "1")
	private int seq;
	
	/**
	 * 게시글 제목
	 */
	@Schema(description = "[CU] 공지사항 제목", example = "개인정보 처리 방침 개정 안내")
	private String bdTit;
	
	/**
	 * 게시글 내용
	 */
	@Schema(description = "[CU] 공지사항 내용", example = "개인정보 처리 방침 개정 안내\n하단 본문 참고")
	private String bdDtl;
	
	/**
	 * 게시글 발행일
	 */
	@Schema(description = "공지사항 발행일", example = "2022-07-19 00:00:00")
	private String pubDate;
	
}
