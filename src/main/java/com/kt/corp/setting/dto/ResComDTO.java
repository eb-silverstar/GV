package com.kt.corp.setting.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DatabindException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "사용자 명함 정보 response DTO")
public class ResComDTO {

    /**
     * 회사 이름
     */
    @Schema(description = "회사 이름", example = "Meta-X-space")
    private String comNm;

    /**
     * 메타 웹 URL
     */
    @Schema(description = "메타 웹 URL", example = "http://metaweb.com/meta-x-space")
    private String metaWebUrl;

    /**
     * 회사 타입
     */
    @Schema(description = "회사 타입", example = "입주사")
    private String comType;

    /**
     * 회사 사업자 등록 번호
     */
    @Schema(description = "사업자 등록 번호", example = "123-45-67899")
    private String comUk;

    /**
     * 회사 건물 템플릿
     */
    @Schema(description = "회사 건물 템플릿 타입", example = "TYPE A")
    private String buldTemp;

    /**
     * 회사 건물 컬러
     */
    @Schema(description = "회사 건물 템플릿 컬러", example = "컬러 값")
    private String buldColTemp;

    /**
     * 오피스 템플릿 타입
     */
    @Schema(description = "오피스 템플릿 타입", example = "오피스 템플릿 A")
    private String officeTemp;

}
