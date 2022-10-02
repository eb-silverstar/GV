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
@Schema(description = "회사 정보 update response DTO")
public class ResUpdateComInfoDTO {
    /**
     * 비지니스 분야 목록
     */
    @Schema(description = "비지니스 분야 목록", example = "IT, 교육, 보안...")
    private List<BsnsFieldDTO> bsnsFieldDTOList;

    /**
     * 활용 기술 목록
     */
    @Schema(description = "활용 기술 목록", example = "AI, XR...")
    private List<UtztnTechDTO> utztnTechDTOList;

    /**
     * 투자 단계
     */
    @Schema(description = "투자 단계", example = "시드, 시리즈A...")
    private List<InvestStageDTO> investStageDTOList;
}
