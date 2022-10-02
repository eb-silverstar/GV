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
@Schema(description = "사용자 알림 response DTO")
public class ResNtcnDTO {

    /**
     * 미리보기 on/Off
     */
    @Schema(description = "미리보기 on/Off", example = "0: off, 1: on")
    private int ntcnPreviewYn;

    /**
     * 사운드 알림 on/Off
     */
    @Schema(description = "사운드 알림 on/Off", example = "0: off, 1: on")
    private int ntcnSoundYn;

    /**
     * 관심기업 on/Off
     */
    @Schema(description = "관심기업 on/Off", example = "0: off, 1: on")
    private int favComNtcnYn;

    /**
     * 호출팝업 on/Off
     */
    @Schema(description = "호출 팝업 on/Off", example = "0: off, 1: on")
    private int callPopupYn;

    /**
     * 일정 알림 on/Off
     */
    @Schema(description = "일정 알림 on/Off", example = "0: off, 1: on")
    private int schdlNtcnYn;

    /**
     * 이벤트 팝업 on/Off
     */
    @Schema(description = "이벤트 팝업 on/Off", example = "0: off, 1: on")
    private int mtgPopupNtcnYn;

    /**
     * 이밴트 on/Off
     */
    @Schema(description = "이벤트 on/Off", example = "0: off, 1: on")
    private int eventNtcnYn;
}
