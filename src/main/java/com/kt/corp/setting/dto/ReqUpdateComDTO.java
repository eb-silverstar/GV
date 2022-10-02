package com.kt.corp.setting.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "회사 정보 업데이트 request DTO")
public class ReqUpdateComDTO {
    /**
     * 회사 타입
     */
    @Schema(description = "회사 타입", example = "입주사")
    private String comType;

    /**
     * 빌팅 타입
     */
    @Schema(description = "빌딩 템플릿", example = "TYPE A")
    private String buldTemp;

    /**
     * 빌딩 컬러
     */
    @Schema(description = "빌딩 컬러 코드", example = "빌딩 색상")
    private String buldColTemp;

    /**
     * 오피스 템플릿
     */
    @Schema(description = "오피스 템플릿", example = "TYPE C")
    private String officeTemp;

    /**
     * 사용자 아이디
     */
    @Schema(description = "사용자 아이디", example = "B64F2-57109")
    private String userId;
}
