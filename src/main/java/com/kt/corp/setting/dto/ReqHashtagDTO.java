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
@Schema(description = "관심사 해시태그 request DTO")
public class ReqHashtagDTO {
    /**
     * 사용자 아이디
     */
    @Schema(description = "사용자 아이디", example = "B64F2-57109")
    private String userId;

    /**
     * 관심사 해시태그
     */
    @Schema(description = "관심사 해시태그", example = "스타트업, IR 스피치, ...")
    private String comHashtag;
}
