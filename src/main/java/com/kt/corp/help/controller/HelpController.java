package com.kt.corp.help.controller;

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
import com.kt.corp.help.dto.HelpDTO;
import com.kt.corp.help.service.HelpService;
import com.kt.corp.message.manage.DbMessageManager;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "Helpdesk", description = "헬프데스크 API")
@RestController
@RequestMapping("/help")
public class HelpController {
	
	@Autowired
	HelpService helpService;
	
	/**
	 * 헬프데스크 문의 목록 조회
	 * 
	 * @return
	 */
	@Operation(summary = "헬프데스크 문의 목록 조회", description = "헬프데스크 문의 목록을 조회")
	@GetMapping
	public ResponseEntity<ApiResponseEntity> getHelpList() {
		List<HelpDTO> helpList = helpService.getHelpList();
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), helpList, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 헬프데스크 문의 내용 조회
	 * 
	 * @param seq
	 * @return
	 */
	@Operation(summary = "헬프데스크 문의 내용 조회", description = "헬프데스크 문의 내용을 조회")
	@GetMapping("/{seq}")
	public ResponseEntity<ApiResponseEntity> getHelpDetail(@PathVariable("seq") int seq) {
		HelpDTO help = helpService.getHelpDetail(seq);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), help, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 헬프데스크 문의글 등록
	 * 
	 * @param helpDto
	 * @return
	 */
	@Operation(summary = "헬프데스크 문의글 등록", description = "헬프데스크 문의글을 등록")
	@PostMapping
	public ResponseEntity<ApiResponseEntity> postHelp(@RequestBody HelpDTO helpDto) {
		Map<String, Object> help = helpService.insertHelp(helpDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), help, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}

}
