package com.kt.corp.metaweb.dto;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "메타웹 팀 DTO")
public class MetaWebTeamDTO {

	/**
	 * 메타웹 아이디
	 */
	@Schema(description = "메타웹 아이디", example = "1")
	private int metaWebId;
	
	/**
	 * 제목1
	 */
	@Schema(description = "제목1", example = "제목")
	private String teamTit1; 
	
	/**
	 * 내용1
	 */
	@Schema(description = "내용1", example = "내용")
	private String teamDtl1;
	
	/**
	 * 제목2
	 */
	@Schema(description = "제목2", example = "제목")
	private String teamTit2 ;
	
	/**
	 * 내용2
	 */
	@Schema(description = "내용2", example = "내용")
	private String teamDtl2;
	
	/**
	 * 제목3
	 */
	@Schema(description = "제목3", example = "제목")
	private String teamTit3;
	
	/**
	 * 내용3
	 */
	@Schema(description = "내용3", example = "내용")
	private String teamDtl3;
	
	/**
	 * 제목4
	 */
	@Schema(description = "제목4", example = "제목")
	private String teamTit4;
	
	/**
	 * 내용4
	 */
	@Schema(description = "내용4", example = "내용")
	private String teamDtl4;	
}
