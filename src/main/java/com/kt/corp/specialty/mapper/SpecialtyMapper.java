package com.kt.corp.specialty.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpecialtyMapper {
	
	public List<String> getSpecialtyList(String userId);
	
	public List<String> getSpecialtyByKeyword(String keyword);
	
	public void insertSpecialty(Map<String, String> map);
	
	public void deleteSpecialty(String userId);

}
