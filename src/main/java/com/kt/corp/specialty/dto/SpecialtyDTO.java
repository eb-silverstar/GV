package com.kt.corp.specialty.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "전문분야 DTO")
public class SpecialtyDTO {
	
	/**
	 * 사용자 ID
	 */
	@Schema(description = "사용자 ID", example = "SDDWF-DFE3F")
	private String userId;
	
	/**
	 * 전문분야 목록
	 */
	@Schema(description = "전문분야 목록", example = "[\"메타버스\", \"인공지능\"]")
	private List<String> specialtyList;

}
