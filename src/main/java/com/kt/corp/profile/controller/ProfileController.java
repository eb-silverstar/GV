package com.kt.corp.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.message.manage.DbMessageManager;
import com.kt.corp.profile.dto.GuestProfileDTO;
import com.kt.corp.profile.dto.UserProfileDTO;
import com.kt.corp.profile.service.ProfileService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "Profile", description = "프로필 API")
@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	ProfileService profileService;
	
	/**
	 * 게스트 닉네임 유효성 체크
	 * 
	 * @param guestNickNm
	 * @return
	 */
	@Operation(summary = "게스트 닉네임 유효성 체크", description = "게스트 닉네임 중복 여부를 체크합니다.")
	@GetMapping("/guest/chk")
	public ResponseEntity<ApiResponseEntity> chkGuestNickNm(@RequestParam String guestId, @RequestParam String guestNickNm) {
		profileService.chkGuestNickNm(guestId, guestNickNm);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사용자 프로필 조회
	 * 
	 * @param userId
	 * @return
	 */
	@Operation(summary = "사용자 프로필 조회", description = "사용자의 프로필을 조회합니다.")
	@GetMapping("/{userId}")
	public ResponseEntity<ApiResponseEntity> getUserProfile(@PathVariable("userId") String userId) {
		UserProfileDTO userProfile = profileService.getUserProfile(userId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), userProfile, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 게스트 프로필 조회
	 * 
	 * @param guestId
	 * @return
	 */
	@Operation(summary = "게스트 프로필 조회", description = "게스트의 프로필을 조회합니다.")
	@GetMapping("/guest/{guestId}")
	public ResponseEntity<ApiResponseEntity> getGuestProfile(@PathVariable("guestId") String guestId) {
		GuestProfileDTO guestProfile = profileService.getGuestProfile(guestId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), guestProfile, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}

	/**
	 * 사용자 프로필 수정
	 * 
	 * @param userProfileDto
	 * @return
	 */
	@Operation(summary = "사용자 프로필 수정", description = "사용자의 프로필을 수정합니다.")
	@PostMapping("/modify-user")
	public ResponseEntity<ApiResponseEntity> updateUserProfile(@RequestBody UserProfileDTO userProfileDto) {
		profileService.updateUserProfile(userProfileDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 게스트 프로필 수정
	 * 
	 * @param guestProfileDto
	 * @return
	 */
	@Operation(summary = "게스트 프로필 수정", description = "게스트의 프로필을 수정합니다.")
	@PostMapping("/modify-guest")
	public ResponseEntity<ApiResponseEntity> updateGuestProfile(@RequestBody GuestProfileDTO guestProfileDto) {
		profileService.updateGuestProfile(guestProfileDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}

}
