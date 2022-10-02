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
@Schema(description = "우리멤버 response DTO")
public class ResEmployeeDTO {
    /**
     * 우리멤버 목록
     */
    @Schema(description = "우리멤버 목록")
    private List<EmployeeDTO> employeeDTOList;

}
