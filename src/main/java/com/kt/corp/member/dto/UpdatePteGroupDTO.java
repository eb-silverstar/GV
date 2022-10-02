package com.kt.corp.member.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "프라이빗 그룹 업데이트 DTO")
public class UpdatePteGroupDTO {
	
	/**
	 * 작업 타입
	 */
	@Schema(description = "작업 타입", example = "C(생성) or U(수정) or D(삭제) or null")
	private String type;
	
	/**
	 * 사용자 ID
	 */
	@Schema(description = "사용자 ID", example = "USER001")
	private String userId;
	
	/**
	 * 그룹 ID
	 */
	@Schema(description = "그룹 ID (필요시에만 전달)", example = "1")
	private Long grpId;
	
	/**
	 * 그룹 이름
	 */
	@Schema(description = "그룹 이름 (필요시에만 전달)", example = "프로젝트 A")
	private String grpNm;
	
	/**
	 * 그룹 멤버 리스트 (User ID)
	 */
	@Schema(description = "그룹 멤버 리스트 (필요시에만 전달)", example = "[\"USER001\", \"USER002\"]")
	private List<String> grpMembers;

}
