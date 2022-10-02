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
@Schema(description = "직책 update request DTO")
public class ReqUpdateJobDTO {
    /**
     * 사용자 아이디
     */
    @Schema(description = "사용자 아이디", example = "B64F2-57109")
    private String userId;

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

}
