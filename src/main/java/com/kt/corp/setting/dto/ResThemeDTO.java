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
@Schema(description = "관심기업 update request DTO")
public class ResThemeDTO {
    /**
     * 사용자 조회 결과
     */
    @Schema(description = "요청한 사용자 존재 여부", example = "0: 없음, 1:있음, 1이상 사용자가 중복으로 존재")
    private int countUser;

    /**
     * 테마 값
     */
    @Schema(description = "테마 값", example = "0: light, 1: dark")
    private int theme;
}
