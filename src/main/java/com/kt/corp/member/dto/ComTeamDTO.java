package com.kt.corp.member.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "회사 팀 DTO")
public class ComTeamDTO {
	
	@Schema(description = "팀 ID", example = "1")
	private Long teamId;

	@Schema(description = "소속 팀", example = "디자인팀")
	private String team;
	
	@Schema(description = "팀 멤버 리스트")
	private List<MemberDTO> teamMembers;
	
}
