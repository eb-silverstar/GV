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
@Schema(description = "관심기업 update request DTO")
public class ReqFavDTO {
    /**
     * 관심기업 PK
     */
    @Schema(description = "관심기업 PK", example = "12")
    private Long Seq;

    /**
     * 사용자 아이디
     */
    @Schema(description = "사용자 아이디", example = "B64F2-57109")
    private String userId;

    /**
     * 알림 on/off
     */
    @Schema(description = "알림 on/off", example = "0: 알림 off, 1: 알림 on")
    private  int onOff;
}
