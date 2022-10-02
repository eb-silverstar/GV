package com.kt.corp.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "게스트 DTO")
public class GuestDTO {
	
	@Schema(description = "게스트 ID", example = "SDDWF-DFE3F")
	private String guestId;
	
	@Schema(description = "게스트 이름", example = "홍길동")
	private String guestNm;
	
	@Schema(description = "게스트 별명", example = "블링블링")
	private String guestNickNm;
	
	@Schema(description = "게스트 이메일", example = "aaa@xxxxx.com")
	private String guestMail;
	
	@Schema(description = "게스트 전화번호", example = "010-2222-1234")
	private String guestNum;
	
	@Schema(description = "게스트 아이피 주소", example = "1.1.1.1")
	private String ipAdd;
	
	@Schema(description = "게스트 초대 주소", example = "http://genie.com")
	private String guestUrl;
	
	@Schema(description = "게스트 아바타 이미지", example = "/ex/xxx.jpg")
	private String imgLog;
	
	@Schema(description = "게스트 상태 코드", example = "CODE001")
	private String stateCode;

}
