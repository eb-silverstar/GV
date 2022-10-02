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
@Schema(description = "사용자 정보 업데이트 request DTO")
public class ReqUpdateUserDTO {
    /**
     * 사용자 아이디
     */
    @Schema(description = "사용자 아이디", example = "B64F2-57109")
    private String userId;

    /**
     * 사용자 이름
     */
    @Schema(description = "사용자 이름", example = "김밸리")
    private String nm;

    /**
     * 자기 소개
     */
    @Schema(description = "자기 소개", example = "Meta-X-space 과장 김밸리 입니다.")
    private String intro;

    /**
     * 전화번호
     */
    @Schema(description = "전화번호", example = "010-1234-5678")
    private String PhoneNum;

}
