package com.kt.corp.fastmv.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kt.corp.fastmv.dto.FastMoveDTO;

@Mapper
public interface FastmvMapper {

	List<FastMoveDTO> selectFastmv(Map<String, Object> params);
	
	int deleteFastmv(Map<String, Object> params);
	
	int updateFastmv(Map<String, Object> params);
	
}
