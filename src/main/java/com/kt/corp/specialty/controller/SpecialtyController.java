package com.kt.corp.specialty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.message.manage.DbMessageManager;
import com.kt.corp.specialty.dto.SpecialtyDTO;
import com.kt.corp.specialty.service.SpecialtyService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "Specialty", description = "전문분야 API")
@RestController
@RequestMapping("/specialty")
public class SpecialtyController {
	
	@Autowired
	SpecialtyService specialtyService;
	
	/**
	 * 전문분야 검색
	 * 
	 * @param keyword
	 * @return
	 */
	@Operation(summary = "전문분야 검색", description = "키워드로 기존 전문분야 데이터를 검색합니다.")
	@GetMapping("/search")
	public ResponseEntity<ApiResponseEntity> getSpecialtyByKeyword(@RequestParam String keyword) {
		SpecialtyDTO specialty = specialtyService.getSpecialtyByKeyword(keyword);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), specialty, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 전문분야 등록
	 * 
	 * @param keyword
	 * @return
	 */
	@Operation(summary = "전문분야 등록", description = "전문분야를 등록합니다.")
	@PostMapping
	public ResponseEntity<ApiResponseEntity> postSpecialty(@RequestBody SpecialtyDTO specialtyDto) {
		specialtyService.validSpecialty(specialtyDto);
		specialtyService.updateSpecialty(specialtyDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	} 

}
