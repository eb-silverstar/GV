package com.kt.corp.metaoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "메타 오피스 DTO")
public class MetaofficeDTO {
	
	@Schema(description = "회사 ID", example = "ABCDE")
	private String comId;
	
	@Schema(description = "회사 이름", example = "Meta X-Space")
	private String comNm;
	
//	@Schema(description = "회의 목록", example = "1")
//	private List<MtgDTO> mtgs;

}
