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
@Schema(description = "활용기술 폼 DTO")
public class UtztnTechFormDTO {
    /**
     * 활용 기술 폼 아이디
     */
    @Schema(description = "활용 폼 기술 아이디", example = "12")
    private Long utztnTechId;

    /**
     * 활용 기술
     */
    @Schema(description = "활용 기술", example = "AI, 로보틱스, 빅데이터...")
    private String utztnTech;
}
