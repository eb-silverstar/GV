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
@Schema(description = "관심 기업 알림 업데이트 request DTO")
public class ReqUpdateFavComOnOffDTO {

    /**
     * 관심 기업 아이디
     */
    @Schema(description = "관심 기업 PK", example = "12")
    private Long seq;

    /**
     * 관심 기업 알림 on/off
     */
    @Schema(description = "관심 기업 알림 on/off", example = "0: off, 1: on")
    private int favComOnOff;
}
