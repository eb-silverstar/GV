package com.kt.corp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kt.corp.dto.DatabaseDTO;

@Mapper
public interface TestMapper {
	
	public List getDatabases(Map params) throws Exception;

	public List getConpType(Map params) throws Exception;
	
	public List getConpList(Map params) throws Exception;
	
	public List getConpTag(Map params) throws Exception;
	
}
