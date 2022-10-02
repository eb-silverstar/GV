package com.kt.corp.profile.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kt.corp.profile.dto.GuestProfileDTO;
import com.kt.corp.profile.dto.UserProfileDTO;

@Mapper
public interface ProfileMapper {
	
	public boolean existGuest(Map<String, String> map);
	
	public UserProfileDTO getUserProfile(String userId);
	
	public GuestProfileDTO getGuestProfile(String guestId);
	
	public void updateUserInfo(UserProfileDTO userProfile);
	
	public void updateGuestInfo(GuestProfileDTO guestProfile);

}
