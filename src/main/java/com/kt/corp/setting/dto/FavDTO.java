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
@Schema(description = "관심 기업 DTO")
public class FavDTO {

    /**
     * 관심 기업 PK
     */
    @Schema(description = "관심기업 PK", example = "12")
    private Long seq;

    /**
     * 사용자 아이디
     */
    @Schema(description = "사용자 아이디", example = "B64F2-57109")
    private String userId;

    /**
     * 관심 기업 이름
     */
    @Schema(description = "관심 기업 이름", example = "Meta-x-space")
    private String comNm;

    /**
     * 관심 기업 ID
     */
    @Schema(description = "기업 아이디", example = "EFDFSF")
    private String comId;

    /**
     * 관심 기업 피드 알림 여부
     */
    @Schema(description = "관심 기업 피드 알림 여부", example = "0: off, 1: on default")
    private int favComOnOff;

    /**
     * 관심 기업 이벤트 알림 여부
     */
    @Schema(description = "관심 기업 이벤트 알림 여부", example = "0: off, 1: on default")
    private int favComEvenOnOff;
}
