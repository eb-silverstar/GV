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
@Schema(description = "직책 DTO")
public class JobDTO {
    /**
     * 직책
     */
    @Schema(description = "직책", example = "과장")
    private String jobTit;

    /**
     * 직책 아이디 PK
     */
    @Schema(description = "직책 아이디", example = "12")
    private Long jobTitId;

    /**
     * 회사 아이디
     */
    @Schema(description = "회사 아이디", example = "DFDS-FDSA")
    private String comId;
}
