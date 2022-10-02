package com.kt.corp.member.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "멤버 리스트 DTO")
public class MemberListDTO {
	
	/**
	 * 내 프로필
	 */
	@Schema(description = "내 프로필")
	private MemberDTO myPf;
	
	/**
	 * 즐겨찾기 리스트
	 */
	@Schema(description = "즐겨찾기 리스트")
	private List<MemberDTO> favMembers;
	
	/**
	 * 우리 멤버 리스트
	 */
	@Schema(description = "우리 멤버 리스트")
	private List<ComTeamDTO> comMembers;
	
	/**
	 * 프라이빗 멤버 리스트
	 */
	@Schema(description = "프라이빗 멤버 리스트")
	private List<MemberDTO> pteMembers;
	
	/**
	 * 프라이빗 그룹 리스트
	 */
	@Schema(description = "프라이빗 그룹 리스트")
	private List<PteGroupDTO> pteGroups;

}
