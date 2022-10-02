package com.kt.corp.bizcard.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "받은 명함 리스트 DTO")
public class BizcardListDTO {
	
	/**
	 * 안 읽은 명함 리스트
	 */
	@Schema(description = "안 읽은 명함 리스트")
	List<BizcardDTO> readN;
	
	/**
	 * 읽은 명함 리스트
	 */
	@Schema(description = "읽은 명함 리스트")
	List<BizcardDTO> readY;

}
