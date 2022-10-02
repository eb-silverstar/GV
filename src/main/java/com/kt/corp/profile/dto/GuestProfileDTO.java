package com.kt.corp.profile.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "게스트 프로필 DTO")
public class GuestProfileDTO {
	
	/**
	 * 게스트 ID
	 */
	@Schema(description = "게스트 ID", example = "DDFES-VDREW")
	private String guestId;
	
	/**
	 * 게스트 닉네임
	 */
	@Schema(description = "게스트 닉네임", example = "밸리")
	private String guestNickNm;
	
	/**
	 * 게스트 상태 코드
	 */
	@Schema(description = "게스트 상태 코드", example = "CODE001")
	private String stateCode;

}