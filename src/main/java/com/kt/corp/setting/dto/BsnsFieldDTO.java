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
@Schema(description = "비지니스 분야 DTO")
public class BsnsFieldDTO {
    /**
     * 비지니스분야
     */
    @Schema(description = "비지니스 분야", example = "IT, 교육, 농축수산...")
    private String bsnsField;

    /**
     * 비지니스 분야 ID
     */
    @Schema(description = "비지니스 분야 아이디", example = "12")
    private Long bsnsFieldId;

    /**
     * 회사 ID
     */
    @Schema(description = "회사 아이디", example = "ABCDF")
    private String comId;
}
