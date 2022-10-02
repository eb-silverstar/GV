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
@Schema(description = "우리멤버 초대 response DTO")
public class ResInviteUserDTO {
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
}
