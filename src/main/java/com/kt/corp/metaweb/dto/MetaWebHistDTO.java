package com.kt.corp.metaweb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "메타웹 히스토리 DTO")
public class MetaWebHistDTO {

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
	 * 연혁연도
	 */
	@Schema(description = "연혁연도", example = "2022")
	private String histYear;
	
	/**
	 * 연혁월
	 */
	@Schema(description = "연혁월", example = "01")
	private String histMm;
	
	/**
	 * 연혁일
	 */
	@Schema(description = "연혁일", example = "01")
	private String histDd;
	
	/**
	 * 상세내용
	 */
	@Schema(description = "상세내용", example = "내용입니다.")
	private String histDtl;
	
	
}
