package com.kt.corp.profile.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.corp.comm.ApiException;
import com.kt.corp.comm.BaseComm;
import com.kt.corp.profile.dto.GuestProfileDTO;
import com.kt.corp.profile.dto.UserProfileDTO;
import com.kt.corp.profile.mapper.ProfileMapper;
import com.kt.corp.specialty.dto.SpecialtyDTO;
import com.kt.corp.specialty.mapper.SpecialtyMapper;
import com.kt.corp.specialty.service.SpecialtyService;

@Service
public class ProfileService extends BaseComm {
	
	@Autowired
	SpecialtyService specialtyService;
	
	@Autowired
	ProfileMapper profileMapper;
	
	@Autowired
	SpecialtyMapper specialtyMapper;
	
	/**
	 * 게스트 닉네임 유효성 체크
	 * 
	 * @param guestNickNm
	 */
	public void chkGuestNickNm(String guestId, String guestNickNm) {
		Map<String, String> map = new HashMap<>();
		if(!"".equals(guestId) && !"''".equals(guestId)) {
			map.put("guestId", guestId);
		}
		map.put("guestNickNm", guestNickNm);
		
		if(profileMapper.existGuest(map)) {
			throw new ApiException("E0017", "닉네임");
		}
	}
	
	/**
	 * 사용자 프로필 조회
	 * 
	 * @param userId
	 * @return userProfile
	 */
	public UserProfileDTO getUserProfile(String userId) {
		UserProfileDTO userProfile = profileMapper.getUserProfile(userId);
		
		if(userProfile != null) {
			userProfile.setSpecialtyList(specialtyMapper.getSpecialtyList(userId));
		}
		
		return userProfile;
	}
	
	/**
	 * 게스트 프로필 조회
	 * 
	 * @param guestId
	 * @return profileMapper.getGuestProfile(guestId)
	 */
	public GuestProfileDTO getGuestProfile(String guestId) {
		return profileMapper.getGuestProfile(guestId);
	}
	
	/**
	 * 사용자 프로필 수정
	 * 
	 * @param userProfile
	 */
	public void updateUserProfile(UserProfileDTO userProfile) {
		SpecialtyDTO specialtyDto = new SpecialtyDTO();
		specialtyDto.setUserId(userProfile.getUserId());
		specialtyDto.setSpecialtyList(userProfile.getSpecialtyList());
		
		specialtyService.validSpecialty(specialtyDto);
		
		// 사용자 정보 수정
		profileMapper.updateUserInfo(userProfile);
		
		// 전문분야 수정
		specialtyService.updateSpecialty(specialtyDto);
	}
	
	/**
	 * 게스트 프로필 수정
	 * 
	 * @param guestProfile
	 */
	public void updateGuestProfile(GuestProfileDTO guestProfile) {
		chkGuestNickNm(guestProfile.getGuestId(), guestProfile.getGuestNickNm());
		profileMapper.updateGuestInfo(guestProfile);
	}

}
