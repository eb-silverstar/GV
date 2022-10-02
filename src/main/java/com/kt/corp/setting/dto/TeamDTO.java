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
@Schema(description = "팀 DTO")
public class TeamDTO {
    /**
     * 팀 PK
     */
    @Schema(description = "팀 아이디", example = "12")
    private Long teamId;

    /**
     * 팀 이름
     */
    @Schema(description = "팀 이름", example = "개발팀")
    private String team;

    /**
     * 회사 아이디
     */
    @Schema(description = "회사 아이디", example = "DFDS-FDSA")
    private String comId;
}
