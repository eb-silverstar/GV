package com.kt.corp.mtg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.message.manage.DbMessageManager;
import com.kt.corp.mtg.dto.MtgDTO;
import com.kt.corp.mtg.service.MtgService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "Meeting", description = "회의 API")
@RestController
@RequestMapping("/mtg")
public class MtgController {
	
	@Autowired
	MtgService mtgService;
	
	/**
	 * 회의 개설
	 * 
	 * @return
	 */
	@Operation(summary = "회의 개설", description = "회의를 개설합니다.")
	@PostMapping
	public ResponseEntity<ApiResponseEntity> postMtg(@RequestPart MtgDTO mtgDto, @RequestPart(required = false) MultipartFile file) {
		mtgService.insertMtg(mtgDto, file);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}

}
