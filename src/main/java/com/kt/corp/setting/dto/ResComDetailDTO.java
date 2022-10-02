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
@Schema(description = "회사 상세 response DTO")
public class ResComDetailDTO {
    /**
     * 회사 상세
     */
    @Schema(description = "회사 상제", example = "회사 소개, 회사 주소, 회사 전화번호 앞자리, 회사 전화 번호 뒷자리")
    private ComDetailDTO comDetailDTO;

    /**
     * 비지니스 분야 목록
     */
    @Schema(description = "비지니스 분야 목록", example = "IT, 교육, 보안...")
    private List<BsnsFieldDTO> bsnsFieldDTOList;

    /**
     * 비지니스 폼 목록
     */
    @Schema(description = "비지니스 분야 폼 목록", example = "IT, 교육, 보안...")
    private List<BsnsFieldFormDTO> bsnsFieldFormDTOList;

    /**
     * 활용 기술 목록
     */
    @Schema(description = "활용 기술 목록", example = "AI, XR...")
    private List<UtztnTechDTO> utztnTechDTOList;

    /**
     * 활용 기술 폼 목록
     */
    @Schema(description = "활용 기술 폼 목록", example = "AI, XR...")
    private List<UtztnTechFormDTO> utztnTechFormDTOList;

    /**
     * 투자 단계
     */
    @Schema(description = "투자 단계", example = "시드, 시리즈A...")
    private List<InvestStageDTO> investStageDTOList;

    /**
     * 투자 단계 폼 목록
     */
    @Schema(description = "투자 단계 폼 목록", example = "시드, 시리즈A, IPO...")
    private List<InvestStageFormDTO> investStageFormDTOList;


    /**
     * 관심사 해쉬 태그 목록
     */
    @Schema(description = "관심사 목록", example = "스타트업, 신기술, 메타")
    private List<HashTagDTO> hashTagDTOList;
}
