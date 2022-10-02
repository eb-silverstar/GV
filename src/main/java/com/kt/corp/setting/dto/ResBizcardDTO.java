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
@Schema(description = "명함 response DTO")
public class ResBizcardDTO {
    /**
     * 회사 이름
     */
    @Schema(description = "회사 이름", example = "Meta-X-company")
    private String comNm;

    /**
     *  사용자 이름
     */
    @Schema(description = "사용자 이름", example = "김밸리")
    private String nm;

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
     * 자기소개
     */
    @Schema(description = "자기 소개", example = "안녕하세요. 저는 Meta-X-Space 홍보를 담당하고 있는 김밸리 입니다.")
    private String intro;

    /**
     * 전화번호 앞자리
     */
    @Schema(description = "사용자 전화번호", example = "010-1234-5678")
    private String phoneNum;

    /**
     * 이메일 주소
     */
    @Schema(description = "이메일 주소", example = "Gvalley@mail.com")
    private String mail;

    /**
     * 개인프로필 공개 여부
     */
    @Schema(description = "개인 프로필 공개 여부", example = "0: 비공개, 1: 공개")
    private int ntcnPreview;
}
