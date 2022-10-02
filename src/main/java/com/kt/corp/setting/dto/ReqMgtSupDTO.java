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
@Schema(description = "경영 지원 request DTO")
public class ReqMgtSupDTO {
    /**
     * 경영 지원
     */
    @Schema(description = "경영 지원", example = "세무, 회계...")
    private String mgtSup;
}
