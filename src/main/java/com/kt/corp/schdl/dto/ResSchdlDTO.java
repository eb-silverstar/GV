package com.kt.corp.schdl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "Response Schdl DTO")
public class ResSchdlDTO {
    /**
     * 해당 월의 일자별 총 일정 개수 목록
     */
    @Schema(description = "월의 첫날 부터 마지막 날짜 까지 각각 총 일정의 개수 목록")
    private List<ResSchdlMonthlyQtyDTO> schdlMonthlyQtyList;

    /**
     * 조회 당일 일정 상세 목록
     */
    @Schema(description = "조회 당일 일정 상세 목록")
    private List<ResSchdlDetailDTO> schdlTodayList;

    /**
     * 지나지 않은 일정 중 가장 날짜가 적게 남은 상세 목록 3개
     */
    @Schema(description = "지나지 않은 일정 중 가장 날짜가 적게 남은 상세 목록 3개")
    private List<ResSchdlDetailDTO> schdlCloseList;

}
