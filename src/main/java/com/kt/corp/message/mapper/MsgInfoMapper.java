package com.kt.corp.message.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kt.corp.message.dto.MsgInfoDTO;

@Mapper
public interface MsgInfoMapper {
	
	public List<MsgInfoDTO> getAllMsgInfo();

}
