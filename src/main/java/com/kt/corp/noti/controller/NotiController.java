package com.kt.corp.noti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.message.manage.DbMessageManager;
import com.kt.corp.noti.dto.NotiDTO;
import com.kt.corp.noti.service.NotiService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "Noti", description = "알림 API")
@RestController
@RequestMapping("/noti")
public class NotiController {
	
	@Autowired
	NotiService notiService;
	
	/**
	 * 알림 목록 조회
	 * 
	 * @return
	 */
	@Operation(summary = "알림 목록 조회", description = "알림 목록을 조회합니다.")
	@GetMapping("/{userId}")
	public ResponseEntity<ApiResponseEntity> getNotiList(@PathVariable("userId") String userId) {
		List<NotiDTO> notiList = notiService.getNotiList(userId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), notiList, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 알림 생성
	 * 
	 * @param notiDto
	 * @return
	 */
	@Operation(summary = "알림 생성", description = "알림을 생성합니다.")
	@PostMapping
	public ResponseEntity<ApiResponseEntity> insertNoti(@RequestBody NotiDTO notiDto) {
		notiService.insertNoti(notiDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	
	/**
	 * 알림 읽음 처리
	 * 
	 * @param seq
	 * @return
	 */
	@Operation(summary = "알림 읽음 처리", description = "알림을 읽음 처리 합니다.")
	@PostMapping("/read/{seq}")
	public ResponseEntity<ApiResponseEntity> updateNotiRead(@PathVariable("seq") Long seq) {
		notiService.updateNotiRead(seq);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 알림 삭제
	 * 
	 * @param seq
	 * @return
	 */
	@Operation(summary = "알림 삭제", description = "알림을 삭제합니다.")
	@PostMapping("/del/{seq}")
	public ResponseEntity<ApiResponseEntity> deleteNoti(@PathVariable("seq") Long seq) {
		notiService.deleteNoti(seq);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}

}
