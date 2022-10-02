package com.kt.corp.metaoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "메타 오피스 멤버 DTO")
public class MetaofficeMemberDTO {
	
	@Schema(description = "사용자 타입", example = "user or guest")
	private String userType;
	
	@Schema(description = "사용자 ID or 게스트 ID", example = "ABCDE-FJGIE")
	private String userId;
	
	@Schema(description = "사용자 이름 or 게스트 닉네임", example = "김밸리")
	private String nm;
	
	@Schema(description = "상태 코드", example = "CODE0001")
	private String userStateCode;
	
	@Schema(description = "회사 이름", example = "GNEW")
	private String comNm;
	
	@Schema(description = "직책", example = "대리")
	private String jobTit;
	
	@Schema(description = "공간(현재 위치) 타입", example = "MO")
	private String spaceType;
	
	@Schema(description = "공간(현재 위치) ID", example = "ABDFE")
	private String spaceId;
	
}
