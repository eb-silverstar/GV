package com.kt.corp.user.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kt.corp.user.dto.GuestDTO;
import com.kt.corp.user.dto.UserDTO;

@Mapper
public interface UserMapper {
	
	public boolean existUser(String userId);
	
	public boolean existGuest(String guestId);
	
	public boolean existUserMail(String mail);
	
	public UserDTO getUserByMail(String mail);
	
	public void insertUser(UserDTO userDto);

	public String getRandomUserId();

	public UserDTO selectUser(Map<String, Object> params);
	
	public int updateUserInfo(Map<String, Object> params);

	public void insertMailLog(Map<String, Object> param);
	
	public int insertUserToken(Map<String, Object> param);
	
	public int updateUserToken(Map<String, Object> params);
	
	public Map<String, Object> selectUserToken(Map<String, Object> params);
	
	public int deleteUserToken(String userId);
	
	public int insertUserLoginLog(UserDTO userDto);
	
	public int insertGuest(GuestDTO guestDTO);
	
	public GuestDTO selectGuest(GuestDTO guestDTO);
	
	public int updateGuest(GuestDTO guestDTO);
	
	public int deleteGuest(String guestNickNm);
	
	public int insertUserStat(UserDTO userDto);
	
	public UserDTO selectUserStat(String guestId);
	
	public int updateUserStat(UserDTO userDto);
	
	public int deleteUserStat(String guestId);
	
}
