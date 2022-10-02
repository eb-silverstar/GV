package com.kt.corp.building.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "건물 홈 DTO")
public class BuildingHomeDTO {

	/**
	 * 사용자 ID
	 */
	@Schema(description = "사용자 ID", example = "SDDWF-DFE3F")
	private String userId;
	
	/**
	 * 건물 ID
	 */
	@Schema(description = "건물 ID", example = "ABCDE")
	private String comId;
	
	/**
	 * 건물 이름
	 */
	@Schema(description = "건물 이름", example = "지뉴소프트")
	private String comNm;
	
	/**
	 * 건물 타입
	 */
	@Schema(description = "건물 타입", example = "입주사")
	private String comType;
	
	/**
	 * 건물 관심 여부
	 */
	@Schema(description = "건물 관심 여부", example = "1")
	private Integer favComVal;
	
}
