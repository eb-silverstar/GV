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
@Schema(description = "일정 삭제 request DTO")
public class ReqDeleteSchdlDTO {
    /**
     * 일정 PK
     */
    @Schema(description = "일정 PK", example = "1")
    private long schdlSeq;
}
