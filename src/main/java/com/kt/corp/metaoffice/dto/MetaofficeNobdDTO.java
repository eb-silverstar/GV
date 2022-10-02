package com.kt.corp.metaoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "메타 오피스 공지사항 DTO")
public class MetaofficeNobdDTO {
	
	@Schema(description = "[UD] 공지사항 Seq", example = "1")
	private Long seq;
	
	@Schema(description = "[CU] 공지사항 제목", example = "사내 연락망")
	private String nobdTit;
	
	@Schema(description = "[CU] 공지사항 내용", example = "사내 연락망입니다.")
	private String nobdDtl;
	
	@Schema(description = "[U] 공지사항 첨부파일 수정 여부", example = "true or false")
	private Boolean updateAtt;
	
	@Schema(description = "공지사항 첨부파일 제목", example = "사내_연락망.xlsx")
	private String nobdAttTit;
	
	@Schema(description = "공지사항 첨부파일 경로", example = "file/ser4gdWER")
	private String nobdAttPath;
	
	@Schema(description = "공지사항 등록일시", example = "2022-09-27 09:00:00")
	private String regDate;
	
	@Schema(description = "[CUD] 회사 ID", example = "ABCDE")
	private String comId;
	
	@Schema(description = "[CUD] 등록자 ID", example = "ABCDE-ABCDE")
	private String userId;

}
