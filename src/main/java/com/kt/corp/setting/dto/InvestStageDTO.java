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
@Schema(description = "회사 투자단계 DTO")
public class InvestStageDTO {
    /**
     * 투자단계 아이디
     */
    @Schema(description = "투자 단계 아이디", example = "12")
    private Long investStageId;

    /**
     * 투자단계
     */
    @Schema(description = "투자 단계", example = "시드, 시리즈A, 시리즈B, IPO..")
    private String investStage;

    /**
     * 회사 아이디
     */
    @Schema(description = "회사 아이디", example = "ABCDE")
    private String comId;
}
