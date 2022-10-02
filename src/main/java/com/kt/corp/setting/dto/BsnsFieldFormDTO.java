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
@Schema(description = "비지니스 필드 폼 DTO")
public class BsnsFieldFormDTO {
    /**
     * 비지니스 필드 폼 아이디
     */
    @Schema(description = "비니지스 필드 폼 아이디")
    private Long bsnsFieldId;

    /**
     * 비지니스 필드
     */
    @Schema(description = "비지니스 필드")
    private String bsnsField;
}
