package com.kt.corp.noti.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kt.corp.noti.dto.NotiDTO;

@Mapper
public interface NotiMapper {
	
	public List<NotiDTO> getNotiList(String userId);
	
	public void insertNotiToUser(NotiDTO notiDto);
	
	public void insertNotiToCompany(NotiDTO notiDto);
	
	public void insertNotiToAllUsers(NotiDTO notiDto);
	
	public void updateNotiRead(Long seq);
	
	public void deleteNoti(Long seq);
	
	public boolean existNoti(Long seq);

	NotiDTO getNotiBySeq(Long seq);
}
