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
@Schema(description = "우리멤버 DTO")
public class EmployeeDTO {
    /**
     *  사용자 이름
     */
    @Schema(description = "사용자 이름", example = "김밸리")
    private String nm;

    /**
     * 이메일 주소
     */
    @Schema(description = "이메일 주소", example = "Gvalley@mail.com")
    private String mail;

    /**
     * 소속 팀
     */
    @Schema(description = "소속 팀 이름", example = "기획팀")
    private String team;

    /**
     * 직책
     */
    @Schema(description = "직책", example = "과장")
    private String jobTit;

    /**
     * 관리자 권한 여부
     */
    @Schema(description = "관리자 권한", example = "0: 없음, 1: 관리자")
    private int auth;

    /**
     * 권한 목록
     */
    @Schema(description = "권한 목록", example = "응대, 컨텐츠, IR자료")
    private String authType;

    /**
     * 상태
     */
    @Schema(description = "상태", example = "초대대기, 초대요청, 초대거절, 초대완료")
    private String sttus;

}
