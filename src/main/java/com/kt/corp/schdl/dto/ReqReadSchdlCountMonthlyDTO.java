package com.kt.corp.schdl.dto;

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
@Schema(description = "월별 일정 개수 request DTO")
public class ReqReadSchdlCountMonthlyDTO {

    /**
     * 사용자 ID (Foreign key)
     */
    @Schema(description = "사용자 ID (Foreign key)", example = "CD24A-8E300")
    private String userId;

    /**
     * 조회 연도
     */
    @Schema(description = "조회 연도", example = "2022")
    private String year;

    /**
     * 조회 월
     */
    @Schema(description = "조회 월", example = "9")
    private String month;
}
