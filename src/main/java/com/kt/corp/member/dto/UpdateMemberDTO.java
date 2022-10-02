package com.kt.corp.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "멤버 업데이트 DTO")
public class UpdateMemberDTO {
	
	/**
	 * 사용자 ID
	 */
	@Schema(description = "사용자 ID", example = "USER0001")
	private String userId;
	
	/**
	 * 멤버 ID
	 */
	@Schema(description = "멤버 ID", example = "USER0002")
	private String memId;
	
	/**
	 * 프라이빗 그룹 ID
	 */
	@Schema(description = "프라이빗 그룹 ID (필요 시에만 전달)", example = "1")
	private Long grpId;

}
