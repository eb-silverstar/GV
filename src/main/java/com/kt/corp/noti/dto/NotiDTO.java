package com.kt.corp.noti.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "내 알림 DTO")
public class NotiDTO {
	
	/**
	 * 알림 번호
	 */
	@Schema(description = "알림 번호", example = "1")
	private Long seq;
	
	/**
	 * 사용자 ID (fl이 내 일정일 경우)
	 */
	@Schema(description = "사용자 ID (fl이 내 일정일 경우)", example = "USER001")
	private String userId;
	
	/**
	 * 회사 Seq (fl이 IR, 제휴/협력일 경우)
	 */
	@Schema(description = "회사 Seq (fl이 IR or 제휴/협력일 경우)", example = "1")
	private String comSeq;
	
	/**
	 * 필터
	 * (CLD_MTG: 내 일정_회의 / CLD_EVT: 내 일정_이벤트 / EVT: 이벤트 / IR_RES: IR_투자사,엑셀러레이터 / IR_REQ: IR_입주사,경영지원사 / COOP: 제휴협력)
	 */
	@Schema(description = "필터", example = "CLD_MTG: 내 일정(회의) / CLD_EVT: 내 일정(이벤트) / EVT: 이벤트 / IR_RES: IR(투자사,엑셀러레이터) / IR_REQ: IR(입주사,경영지원사) / COOP: 제휴/협력")
	private String fl;
	
	/**
	 * 알림 송신자
	 */
	@Schema(description = "알림 송신자", example = "A Company or 김밸리")
	private String notiSender;
	
	/**
	 * 회의/이벤트 일시
	 */
	@Schema(description = "회의/이벤트 일시", example = "2022-08-05 15:00:00")
	private String mtgDate;
	
	/**
	 * 회의/이벤트 이름
	 */
	@Schema(description = "회의/이벤트 이름", example = "Genie Valley 오픈 이벤트")
	private String mtgTit;
	
	/**
	 * 초대된 사용자 역할
	 */
	@Schema(description = "사용자 역할", example = "안내인")
	private String mtgRole;
	
	/**
	 * 알림 내용
	 */
	@Schema(description = "알림 내용", example = "IR 제안이 도착하였습니다!")
	private String notiDtl;
	
	/**
	 * 알림 일시
	 */
	@Schema(description = "알림 일시", example = "2022-07-19 00:00:00")
	private String notiDate;
	
	/**
	 * 알림 확인 여부
	 */
	@Schema(description = "알림 확인 여부", example = "true or false")
	private boolean notiRead;
	
}
