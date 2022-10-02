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
@Schema(description = "직책 목록 조회 response DTO")
public class ResJobListDTO {
    /**
     * 직책 목록
     */
    @Schema(description = "직책 목록", example = "jobTit: 직책 이름, jobTitId: PK, comId: 회사 아이디")
    private List<JobDTO> jobList;
}
