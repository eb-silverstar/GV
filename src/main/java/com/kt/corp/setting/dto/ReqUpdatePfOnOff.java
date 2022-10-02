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
@Schema(description = "프로필 공개 여부 업데이트 request DTO")
public class ReqUpdatePfOnOff {

    /**
     * 사용자 아이디
     */
    @Schema(description = "사용자 아이디", example = "B64F2-57109")
    private String userId;


    /**
     * 프로필 공개 여부
     */
    @Schema(description = "프로필 공개 여부", example = "0: 비공개, 1:공개")
    private int pfOnOff;
}
