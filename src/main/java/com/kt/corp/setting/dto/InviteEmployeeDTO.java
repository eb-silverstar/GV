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
@Schema(description = "우리멤버 초대 DTO")
public class InviteEmployeeDTO {
    /**
     * 회사 아이디
     */
    @Schema(description = "회사 아이디", example = "ABCDF")
    private String comId;

    /**
     * 사용자 아이디
     */
    @Schema(description = "사용자 아이디", example = "B64F2-57109")
    private String userId;

    /**
     * 상태
     */
    @Schema(description = "상태", example = "초대대기, 초대요청, 초대거절, 초대완료")
    private String sttus;

}
