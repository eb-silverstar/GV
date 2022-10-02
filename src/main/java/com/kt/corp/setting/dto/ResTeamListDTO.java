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
@Schema(description = "팀 DTO")
public class ResTeamListDTO {
    /**
     * 팀 리스트
     */
    @Schema(description = "팀 목록", example = "team: 개발팀, teamId: 12")
    private List<TeamDTO> teamList;
}
