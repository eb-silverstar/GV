package com.kt.corp.metaweb.dto;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "메타웹 DTO")
public class MetaWebDTO {
	
	/**
	 * 시퀀스
	 */
	@Schema(description = "시퀀스", example = "1")
	private int seq;
	
	/**
	 * 유저 아이디
	 */
	@Schema(description = "유저 아이디", example = "BBC56-B917C")
	private String userId;
	
	/**
	 * 메타웹 아이디
	 */
	@Schema(description = "메타웹 아이디", example = "1")
	private int metaWebId;
	
	/**
	 * 회사 아이디
	 */
	@Schema(description = "회사 아이디", example = "ABCDI")
	private String comId;
	
	/**
	 * 회사 이름
	 */
	@Schema(description = "회사 이름", example = "Gnew Soft")
	private String comNm;
	
	/**
	 * 사업 소개 영상
	 */
	@Schema(description = "소개 영상", example = "https://www.youtube.com/watch?v=pOp8l6HJsxo")
	private String bsnsVido;
	
	/**
	 * 대표 이미지
	 */
	@Schema(description = "대표 이미지 경로", example = "img.png")
	private String bsnsImg;
	
	/**
	 * 대표 문구 한줄
	 */
	@Schema(description = "대표 문구", example = "지뉴소프트 사업소개")
	private String bsnsPhrase;
	
	/**
	 * 사업 상세 소개
	 */
	@Schema(description = "상세 소개", example = "상세 소개입니다.")
	private String bsnsIntro;
	
	/**
	 * 소개 페이지 링크
	 */
	@Schema(description = "페이지 링크", example = "http://www.gnewsoft.com/")
	private String bsnsUrl;
	
	/**
	 * 태그
	 */
	@Schema(description = "태그", example = "웹, 앱,기타")
	private String utztnTech;
	
	/**
	 * 태그
	 */
	@Schema(description = "태그", example = "프리 IPO,IPO,M&A")
	private String investStage;
	
	/**
	 * 인트로 이미지
	 */
	@Schema(description = "인트로 이미지", example = "")
	private List<Map<String, Object>> introImgs;
	
}
