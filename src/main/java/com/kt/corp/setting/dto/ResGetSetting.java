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
@Schema(description = "스케쥴 상세 response DTO")
public class ResGetSetting {
    /**
     * 프로필 공개 on/onff
     */
    @Schema(description = "프로필 공개 on/onff", example = "0: 비공개, 1: 공개")
    private int pfOnOff;

    /**
     * 이름
     */
    @Schema(description = "이름", example = "김밸리")
    private String nm;

    /**
     * 회사 이름
     */
    @Schema(description = "회사 이름", example = "Meta-x-space")
    private String comNm;

    /**
     * 소속 팀
     */
    @Schema(description = "소속 팀", example = "기획팀")
    private String team;

    /**
     * 직책 이름
     */
    @Schema(description = "직책 이름", example = "과장")
    private String jobTit;

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

    /**
     * 이메일
     */
    @Schema(description = "이메일 주소", example = "valley@eamil.com")
    private String mail;

    /**
     * 권한
     */
    @Schema(description = "권한", example = "admin")
    private String auth;
}
