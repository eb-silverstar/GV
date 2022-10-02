package com.kt.corp.message.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "메시지 조회 DTO")
public class MsgInfoDTO {
	
	/**
	 * 메시지 코드(메시지 타입 1자리 + 숫자 4자리)
	 */
	@Schema(description = "메시지 코드(메시지 타입 1자리 + 숫자 4자리)", example = "I0001")
	private String msgCode;
	
	/**
	 * 메시지 타입(I: Information, E: Error, L: Label)
	 */
	@Schema(description = "메시지 타입(I: Information, E: Error, L: Label)", example = "I")
	private String msgType;
	
	/**
	 * 한글 메시지 내용
	 */
	@Schema(description = "한글 메시지 내용", example = "데이터가 존재하지 않습니다.")
	private String msgKoDesc;
	
	/**
	 * 영문 메시지 내용
	 */
	@Schema(description = "영문 메시지 내용", example = "The data does not exists.")
	private String msgEnDesc;

}
