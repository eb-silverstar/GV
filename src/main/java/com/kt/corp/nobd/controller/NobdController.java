package com.kt.corp.nobd.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.message.manage.DbMessageManager;
import com.kt.corp.nobd.dto.NobdDTO;
import com.kt.corp.nobd.service.NobdService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "Notice Board", description = "공지사항 API")
@RestController
@RequestMapping("/nobd")
public class NobdController {
	
	@Autowired
	NobdService nobdService;
	
	/**
	 * 공지사항 목록 조회
	 * 
	 * @return
	 */
	@Operation(summary = "공지사항 목록 조회", description = "공지사항목 록을 조회")
	@GetMapping
	public ResponseEntity<ApiResponseEntity> getNobdList() {
		List<NobdDTO> nobdList = nobdService.getNobdList();
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), nobdList, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 공지사항 내용 조회
	 * 
	 * @param seq
	 * @return
	 */
	@Operation(summary = "공지사항 내용 조회", description = "공지사항 내용을 조회")
	@GetMapping("/{seq}")
	public ResponseEntity<ApiResponseEntity> getNobdDetail(@PathVariable("seq") int seq) {
		NobdDTO nobd = nobdService.getNobdDetail(seq);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), nobd, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 공지사항 등록
	 * 
	 * @param nobdDto
	 * @return
	 */
	@Operation(summary = "공지사항 등록", description = "공지사항 게시글을 등록")
	@PostMapping
	public ResponseEntity<ApiResponseEntity> postNobd(@RequestBody NobdDTO nobdDto) {
		Map<String, Object> nobd = nobdService.insertNobd(nobdDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), nobd, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 공지사항 수정
	 * 
	 * @param nobdDto
	 * @return
	 */
	@Operation(summary = "공지사항 수정", description = "공지사항 게시글을 수정합니다.")
	@PostMapping("/modify-nobd")
	public ResponseEntity<ApiResponseEntity> putNobd(@RequestBody NobdDTO nobdDto) {
		Map<String, Object> nobd = nobdService.updateNobd(nobdDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), nobd, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 공지사항 삭제
	 * 
	 * @param seq
	 * @return
	 */
	@Operation(summary = "공지사항 삭제", description = "공지사항 게시글을 삭제합니다.")
	@PostMapping("/remove-nobd/{seq}")
	public ResponseEntity<ApiResponseEntity> deleteNobd(@PathVariable("seq") int seq) {
		nobdService.deleteNobd(seq);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
}
