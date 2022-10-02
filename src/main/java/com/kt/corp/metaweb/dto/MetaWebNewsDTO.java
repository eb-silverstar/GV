package com.kt.corp.metaweb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "메타웹 뉴스 DTO")
public class MetaWebNewsDTO {

	/**
	 * 시퀀스
	 */
	@Schema(description = "시퀀스", example = "1")
	private int seq;
	
	/**
	 * 메타웹 아이디
	 */
	@Schema(description = "메타웹 아이디", example = "1")
	private int metaWebId;
	
	/**
	 * 뉴스 url
	 */
	@Schema(description = "뉴스 url", example = "http://news.com")
	private String newsUrl;
	
	
}
