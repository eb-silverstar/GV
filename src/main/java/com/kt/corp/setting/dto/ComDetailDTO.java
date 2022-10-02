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
@Schema(description = "회사 상세 DTO")
public class ComDetailDTO {
    /**
     * 회사 소개
     */
    @Schema(description = "회사 소개", example = "회사 소개 내용...")
    private String comIntro;

    /**
     * 회사 주소
     */
    @Schema(description = "회사 주소", example = "회수 주소 값")
    private String comAdd;

    /**
     * 회사 전화 번호 앞자리
     */
    @Schema(description = "회사 전화 번호 앞자리", example = "02")
    private String comNum1;

    /**
     * 회사 전화 번호 뒷자리
     */
    @Schema(description = "회사 전화 번호 뒷자리", example = "1234-5678")
    private String comNum2;
}
