package com.kt.corp.metaweb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "메타웹 오브젝트 DTO")
public class MetaWebObjDTO {

	/**
	 * 메타웹 아이디
	 */
	@Schema(description = "메타웹 아이디", example = "1")
	private int metaWebId;
	
	/**
	 * 뉴스 url
	 */
	@Schema(description = "뉴스 url", example = "http://news.com")
	private String objNm;
	
	/**
	 * 콘텐츠 생성 여부
	 */
	@Schema(description = "콘텐츠 생성 여부", example = "Y")
	private String cntntsYn;
	
	/**
	 * 콘텐츠 제목
	 */
	@Schema(description = "콘텐츠 제목", example = "제목 입니다.")
	private String cntntsTit;
	
	/**
	 * 콘텐츠 영상
	 */
	@Schema(description = "콘텐츠 영상", example = "http://news.com")
	private String cntntsVido;
	
	/**
	 * 콘텐츠 이미지
	 */
	@Schema(description = "콘텐츠 이미지", example = "img.png")
	private String cntntsImg;
	
	/**
	 * 콘텐츠 상세 내용
	 */
	@Schema(description = "콘텐츠 상세 내용", example = "콘텐츠 상세 내용입니다.")
	private String cntntsDtl;
	
	/**
	 * 콘텐츠 url
	 */
	@Schema(description = "콘텐츠 url", example = "http://object.com")
	private String cntntsUrl;
	
}
