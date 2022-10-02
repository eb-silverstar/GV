package com.kt.corp.metaoffice.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "메타 오피스 공지사항 DTO")
public class MetaofficeNobdListDTO {
	
	@Schema(description = "회사 관리자 권한", example = "true or false")
	private Boolean auth;
	
	@Schema(description = "공지사항 목록")
	private List<MetaofficeNobdDTO> officeNobds;

}
