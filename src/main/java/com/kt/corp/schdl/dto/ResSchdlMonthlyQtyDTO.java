package com.kt.corp.schdl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "월별 일자 일정 개수 DTO")
public class ResSchdlMonthlyQtyDTO {
    /**
     * 일정 총 개수
     */
    @Schema(description = "일정 개수", example = "2")
    private int schdlTotalQty;

    /**
     * CLD_EVT 개수
     */
    @Schema(description = "CLD_EVT 개수", example = "1")
    private int schdlCldEvtQty;

    /**
     * EVT 개수
     */
    @Schema(description = "EVT 개수", example = "3")
    private int schdlEvtQty;

    /**
     * CLD_MTG 개수
     */
    @Schema(description = "CLD_MTG 개수", example = "3")
    private int schdlCldMtgQty;

    /**
     * 회의/이벤트 일시
     */
    @Schema(description = "회의/이벤트 일시", example = "2022-08-05 15:00:00")
    private String schdlDate;
}
