package com.kt.corp.comm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "API 응답 DTO")
public class ApiResponseEntity {
	/**
	 * 처리 상태
	 */
	@Schema(description = "처리 상태", example = "success or fail")
	private String status;

	/**
	 * 응답 내용
	 */
	@Schema(description = "응답 내용", example = "null or boolean or or string or json")
	private Object response;

	/**
	 * 에러 메시지 코드
	 */
	@Schema(description = "에러 메시지 코드", example = "E0012")
	private String errorCode;

	/**
	 * 에러 메시지 내용
	 */
	@Schema(description = "에러 메시지 내용", example = "데이터 입력 실패")
	private String errorMessage;
}
