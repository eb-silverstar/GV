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
@Schema(description = "일별 일정 상세 request DTO")
public class ReqReadSchdlDetailDailyDTO {
    /**
     * 사용자 ID (Foreign key)
     */
    @Schema(description = "사용자 ID (Foreign key)", example = "BCD24A-8E300")
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

    /**
     * 조회 일
     */
    @Schema(description = "조회 일", example = "26")
    private String day;

}
