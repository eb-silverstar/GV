package com.kt.corp.member.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "프라이빗 그룹 DTO")
public class PteGroupDTO {
	
	/**
	 * 그룹 ID
	 */
	@Schema(description = "그룹 ID", example = "1")
	private Long grpId;
	
	/**
	 * 그룹 이름
	 */
	@Schema(description = "그룹 이름", example = "프로젝트 A")
	private String grpNm;
	
	/**
	 * 그룹 멤버 리스트
	 */
	@Schema(description = "그룹 멤버 리스트")
	private List<MemberDTO> grpMembers;

}
