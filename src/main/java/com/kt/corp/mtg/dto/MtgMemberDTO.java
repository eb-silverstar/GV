package com.kt.corp.mtg.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "회의 참석 멤버 DTO")
public class MtgMemberDTO {
	
	@Schema(description = "[C] 사용자 ID", example = "53SEF-7GF4D")
	private String userId;
	
	@Schema(description = "사용자 이름", example = "김밸리")
	private String nm;
	
	@Schema(description = "회사 이름", example = "KT")
	private String comNm;
	
	@Schema(description = "직위", example = "사원")
	private String jobTit;

}
