package com.kt.corp.metaweb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "메타웹 채용 DTO")
public class MetaWebEmpmnDTO {
	
	/**
	 * 메타웹 아이디
	 */
	@Schema(description = "메타웹 아이디", example = "1")
	private int metaWebId;
	
	@Schema(description = "메타웹 시퀀스", example = "1")
	private int seq;
	
	@Schema(description = "직무", example = "운영")
	private String dty;
	
	@Schema(description = "고용형태", example = "정규직")
	private String emplym;
	
	@Schema(description = "경력", example = "경력")
	private String career;
	
	@Schema(description = "연차", example = "5")
	private String empmnYear;
	
	@Schema(description = "기준", example = "이상")
	private String stdr; 		
	
	@Schema(description = "상시채여 여부", example = "0")
	private int ordtmEmpmn;
	
	@Schema(description = "채용 시작일", example = "2022-09-07")
	private String startDate;
	
	@Schema(description = "채용 마감일", example = "2092-09-07")
	private String endDate;
	
	@Schema(description = "체용공고 URL", example = "http://test.com")
	private String empmnUrl;
	
	@Schema(description = "생성일자", example = "2022-09-07")
	private String regDate;
	
	@Schema(description = "수정일자", example = "2022-09-07")
	private String chgDate;

}
