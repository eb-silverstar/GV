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
@Schema(description = "경영지원 회사 정보 response DTO")
public class ResComDetailMgtDTO {
    /**
     * 회사 상세
     */
    @Schema(description = "회사 상제", example = "회사 소개, 회사 주소, 회사 전화번호 앞자리, 회사 전화 번호 뒷자리")
    private ComDetailDTO comDetailDTO;

    /**
     * 경영 지원 목록
     */
    @Schema(description = "경영지원 목록", example = "세무, 회계...")
    private List<MgtSupDTO> mgtSupDTOList;

    /**
     * 관심사 해쉬 태그 목록
     */
    @Schema(description = "관심사 목록", example = "스타트업, 신기술, 메타")
    private List<HashTagDTO> hashTagDTOList;
}
