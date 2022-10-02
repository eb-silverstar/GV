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
@Schema(description = "일정 추가 request DTO")

public class ReqAddSchdlDTO {

    /**
     * 사용자 ID (Foreign key)
     */
    @Schema(description = "사용자 ID (Foreign key)", example = "CD24A-8E300")
    private String userId;

    /**
     * 알림 PK
     */
    @Schema(description = "알림 PK", example = "1")
    private Long notiSeq;

}
