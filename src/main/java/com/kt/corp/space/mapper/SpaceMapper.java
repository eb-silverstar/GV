package com.kt.corp.space.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpaceMapper {
	
	public void insertSpaceMember(Map<String, String> map);
	
	public void deleteSpaceMember(String userId);

}
