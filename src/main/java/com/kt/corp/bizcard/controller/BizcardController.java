package com.kt.corp.bizcard.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.corp.bizcard.dto.BizcardDTO;
import com.kt.corp.bizcard.dto.BizcardListDTO;
import com.kt.corp.bizcard.service.BizcardService;
import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.message.manage.DbMessageManager;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "BizCard", description = "받은 명함 API")
@RestController
@RequestMapping("/bizcard")
public class BizcardController {
	
	@Autowired
	BizcardService bizcardService;
	
	/**
	 * 받은 명함 목록 조회
	 * 
	 * @param userId
	 * @return
	 */
	@Operation(summary = "받은 명함 목록 조회", description = "받은 명함 목록을 조회합니다.")
	@GetMapping("/{userId}")
	public ResponseEntity<ApiResponseEntity> getBizCardList(@PathVariable("userId") String userId) {
		BizcardListDTO bizcardList = bizcardService.getBizcardList(userId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), bizcardList, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 받은 명함 검색
	 * 
	 * @param userId
	 * @param keyword
	 * @return
	 */
	@Operation(summary = "받은 명함 검색", description = "받은 명함 목록에서 사용자 이름 혹은 회사 이름으로 검색합니다.")
	@GetMapping("/{userId}/search")
	public ResponseEntity<ApiResponseEntity> getBizcardsByName(@PathVariable("userId") String userId, @RequestParam String keyword) {
		List<BizcardDTO> bizcardList = bizcardService.getBizcardsByName(userId, keyword);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), bizcardList, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 명함 보내기 (받은 명함 등록)
	 * 
	 * @param bizcardDto
	 * @return
	 */
	@Operation(summary = "명함 보내기 (받은 명함 등록)", description = "사용자의 명함을 보냅니다.")
	@PostMapping
	public ResponseEntity<ApiResponseEntity> postBizcard(@RequestBody BizcardDTO bizcardDto) {
		bizcardService.insertBizcard(bizcardDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 받은 명함 수락
	 * 
	 * @param seq
	 * @return
	 */
	@Operation(summary = "받은 명함 수락", description = "받은 명함을 수락합니다.")
	@PostMapping("/accept/{seq}")
	public ResponseEntity<ApiResponseEntity> acceptBizcard(@PathVariable("seq") Long seq) {
		bizcardService.acceptBizcard(seq);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 받은 명함 확인 (읽음 처리)
	 * 
	 * @param userId
	 * @return
	 */
	@Operation(summary = "받은 명함 확인 (읽음 처리)", description = "사용자의 받은 명함을 전체 읽음 처리합니다.")
	@PostMapping("/{userId}/read")
	public ResponseEntity<ApiResponseEntity> updateReadFlag(@PathVariable("userId") String userId) {
		bizcardService.updateReadFlag(userId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 받은 명함 거절
	 * 
	 * @param seq
	 * @return
	 */
	@Operation(summary = "받은 명함 거절", description = "받은 명함을 거절합니다.")
	@PostMapping("/refuse/{seq}")
	public ResponseEntity<ApiResponseEntity> deleteBizcard(@PathVariable("seq") Long seq) {
		bizcardService.deleteBizcardBySeq(seq);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}

}
