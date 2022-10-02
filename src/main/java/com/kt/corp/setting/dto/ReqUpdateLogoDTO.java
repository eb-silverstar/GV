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
    @Schema(description = "회가 로고 update request DTO")
public class ReqUpdateLogoDTO {
    /**
     * 회사 로고
     */
    @Schema(description = "회사 로고", example = "logo.png")
    private String imgLogo;

    /**
     * 사용자 아이디
     */
    @Schema(description = "사용자 아이디", example = "B64F2-57109")
    private String userId;
}
