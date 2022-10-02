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
@Schema(description = "전문분야 리스트 response DTO")
public class ResSepcialtyListDTO {
    /**
     * 전문 분야 목록
     */
    @Schema(description = "전문 분야 목록", example = "IT, IT 전문가, IT 개발자...")
    private List<String> sepcialtyList;
}
