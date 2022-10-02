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
@Schema(description = "스케쥴 상세 DTO")
public class ResSchdlDetailDTO {
    /**
     * 일정 PK
     */
    @Schema(description = "일정 PK", example = "12")
    private Long schdlSeq;

    /**
     * 일정 날짜
     */
    @Schema(description = "일정 날짜", example = "2022-05-13 12:00:00")
    private String schdlDate;

    /**
     * 일정 제목
     */
    @Schema(description = "일정 제목", example = "회의/이벤트 제목")
    private String schdlTit;

    /**
     * 일정까지 남은 일수
     */
    @Schema(description = "일정까지 남은 일수", example = "3")
    private int schdlLeftOver;

    /**
     * 일정 지났는지 여부
     * 일정이 지났으면 true, 일정이 지나지 않았으면 false
     */
    @Schema(description = "일정이 지났는지 여부, 지났으면 true 아직 안지났으면 false")
    private String schdlOver;

    /**
     * 일정 종류
     * 회의, 이벤트
     */
    @Schema(description = "일정 종류", example = "회의, 이벤트")
    private String fl;

}
