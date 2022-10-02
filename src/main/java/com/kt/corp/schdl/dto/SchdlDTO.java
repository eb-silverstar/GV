package com.kt.corp.schdl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "일정 DTO")
public class SchdlDTO {
    /**
     * 일정 PK
     */
    @Schema(description = "일정 PK", example = "12")
    private Long schdlSeq;

    /**
     * 사용자 ID (Foreign key)
     */
    @Schema(description = "사용자 ID (Foreign key)", example = "USER001")
    private String userId;

    /**
     * 데이터 등록 시간
     */
    @Schema(description = "데이터 등록 시간", example = "2022-09-16 10:57:47")
    private String regDate;

    /**
     * 데이터 업데이트 시간
     */
    @Schema(description = "데이터 최종 업데이트 시간", example = "2022-09-16 10:57:47")
    private String upDate;

    /**
     * 일정 필터 리스트
     */
    @Schema(description = "일정 타입", example = "CLD_EVT, CLD_MTG")
    private String fl;

    /**
     * 일정 시작 시간
     */
    @Schema(description = "일정 시작 시간", example = "2022-09-16 10:57:47")
    private String schdlDate;

    /**
     * 일정 종료 시간
     */
    @Schema(description = "일정 종료 시간", example = "2022-09-16 10:57:47")
    private String schdlEndDate;

    /**
     * 일정 제목
     */
    @Schema(description = "일정 제목", example = "CN Company 협업 미팅")
    private String schdlTit;

    /**
     * 일정 상세
     */
    @Schema(description = "일정 상세", example = "협입 미팅 발표자: 김밸리")
    private String schdlDTL;

}
