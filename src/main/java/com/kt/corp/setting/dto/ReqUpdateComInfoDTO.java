package com.kt.corp.setting.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "회사 정보 update request DTO")
public class ReqUpdateComInfoDTO {
    /**
     * 사용자 아이디
     */
    @Schema(description = "사용자 아이디", example = "B64F2-57109")
    private String userId;

    /**
     * 비지니스 폼 목록
     */
    @Schema(description = "비지니스 분야 폼 목록", example = "[1,2,3]")
    private List<Long> bsnsFieldIdList;

    /**
     * 활용 기술 폼 목록
     */
    @Schema(description = "활용 기술 폼 목록", example = "[1,2,3]")
    private List<Long> utztnTechIdList;

    /**
     * 투자 단계 폼 목록
     */
    @Schema(description = "투자 단계 폼 목록", example = "[1,2,3]")
    private List<Long> investStageIdList;
}
