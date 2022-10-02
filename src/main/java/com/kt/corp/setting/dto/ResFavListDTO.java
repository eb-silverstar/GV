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
@Schema(description = "관심 기업 목록 response DTO")
public class ResFavListDTO {

    /**
     * 관심 기업 목록
     */
    @Schema(description = "관심 기업 목록", example = "seq: 12, comNm: meta-x-company, favComOnOff: 0 or 1")
    private List<FavDTO> favList;
}
