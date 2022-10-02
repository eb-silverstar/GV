package com.kt.corp.stomp.dto;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StompDTO {
	
	/**
	 * 처리 구분 (init or update)
	 */
	private String stat;
	
	/**
	 * 사용자 리스트
	 */
	private List<Map<String, Object>> userList;
	
	/**
	 * 스몰톡 리스트
	 */
	private List<Map<String, Object>> stList;

}
