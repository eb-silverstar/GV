package com.kt.corp.building.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.corp.building.dto.BuildingHomeDTO;
import com.kt.corp.building.service.BuildingService;
import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.message.manage.DbMessageManager;
import com.kt.corp.user.controller.UserController;
import com.kt.corp.user.dto.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "Building", description = "건물 API")
@Slf4j
@RestController
@RequestMapping("/building")
public class BuildingController {

	@Autowired private BuildingService buildingService;
	
	/**
	 * 홈 건물 정보 조회
	 * 
	 * @param userId
	 * @return
	 */
	@Operation(summary = "홈 건물 정보 리스트", description = "홈 건물 정보 리스트를 조회 합니다.")
	@GetMapping("/home")
	public ResponseEntity<ApiResponseEntity> getHomeBuilding(@RequestParam String userId) {
		 List<Map<String,Object>> result =  this.buildingService.selectHomeCompany(userId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 홈 건물 관심 등록/해제
	 * @param BuildingHomeDTO
	 * @return
	 */
	@Operation(summary = "홈 건물 관심 등록/해제", description = "홈 건물 관심 등록/해제 합니다.")
	@PostMapping("/home")
	public ResponseEntity<ApiResponseEntity> modifyHomeBuilding(@RequestBody BuildingHomeDTO dto) {
		int result = this.buildingService.updateHomeCompanyFav(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사용자 관심 건물 정보 조회
	 * 
	 * @param userId
	 * @return
	 */
	@Operation(summary = "사용자 관심 건물 정보 리스트", description = "사용자 관심 건물 정보 리스트를 조회 합니다.")
	@GetMapping("/userFav")
	public ResponseEntity<ApiResponseEntity> getUserBuilding(@RequestParam String userId, @RequestParam String comType, @RequestParam String comNm) {
		BuildingHomeDTO dto = new BuildingHomeDTO();
		dto.setUserId(userId);
		if(!"''".equals(comType)) dto.setComType(comType);
		if(!"''".equals(comNm)) dto.setComNm(comNm);
		
		 List<Map<String,Object>> result =  this.buildingService.selectCompanyFavUser(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
}
