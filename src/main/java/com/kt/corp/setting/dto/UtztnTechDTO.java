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
@Schema(description = "회사 활용 기술 DTO")
public class UtztnTechDTO {
    /**
     * 활용 기술 아이디
     */
    @Schema(description = "활용기술", example = "12")
    private Long utztnTechId;

    /**
     * 활용 기술
     */
    @Schema(description = "활용기술", example = "AI, XR, 빅데이터...")
    private String utztnTech;

    /**
     * 회사 아이디
     */
    @Schema(description = "회사 아이디", example = "ABCDE")
    private String comId;
}
