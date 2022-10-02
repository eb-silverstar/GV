package com.kt.corp.fastmv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.fastmv.dto.FastMoveDTO;
import com.kt.corp.fastmv.service.FastmvService;
import com.kt.corp.message.manage.DbMessageManager;
import com.kt.corp.user.dto.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "FastMove", description = "빠른 이동 API")
@RestController
@RequestMapping("/fastmv")
public class FastmvController {

	@Autowired private FastmvService fastmvService;
	
	/**
	 * 빠른 이동 리스트
	 * 
	 * @param userId
	 * @return List<FastMoveDTO>
	 */
	@Operation(summary = "사용자 빠른 이동 리스트", description = "사용자의 빠른 이동 리스트 입니다.")
	@GetMapping("/getFastmvList")
	public ResponseEntity<ApiResponseEntity> getFastmvList(@RequestParam String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		
		List<FastMoveDTO> result = this.fastmvService.selectFastmv(param);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 상단 고정 변경
	 * 
	 * @param Map
	 * @return
	 */
	@Operation(summary = "사용자 상단고정 변경", description = "사용자 상단고정 변경을 합니다.")
	@PostMapping("/modify-fixed")
	public ResponseEntity<ApiResponseEntity> changeFixed(@RequestBody HashMap<String, Object> map) {
		
		int result = this.fastmvService.updateFastmv(map);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 *  빠른이동 삭제
	 * 
	 * @param Map
	 * @return
	 */
	@Operation(summary = "사용자 빠른이동 삭제", description = "사용자 빠른이동 삭제를 합니다.")
	@PostMapping("/remove-fastmv")
	public ResponseEntity<ApiResponseEntity> removeFastmv(@RequestBody HashMap<String, Object> map) {
		
		int result = this.fastmvService.deleteFastmv(map);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
}
