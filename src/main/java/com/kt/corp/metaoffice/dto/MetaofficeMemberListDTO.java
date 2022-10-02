package com.kt.corp.metaoffice.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "메타 오피스 멤버 목록 DTO")
public class MetaofficeMemberListDTO {
	
	@Schema(description = "활동 중인 멤버")
	private List<MetaofficeMemberDTO> inOfficeMembers;
	
	@Schema(description = "부재 중인 멤버")
	private List<MetaofficeMemberDTO> outOfficeMembers;
	
	@Schema(description = "게스트")
	private List<MetaofficeMemberDTO> officeGuests;

}
