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
@Schema(description = "관심사 해쉬태그 DTO")
public class HashTagDTO {
    /**
     * 회사 아이디
     */
    @Schema(description = "회사 아이디", example = "ABCDE")
    private String comId;

    /**
     * 회사 해쉬태그
     */
    @Schema(description = "관심사 태그", example = "")
    private String comHashtag;
}
