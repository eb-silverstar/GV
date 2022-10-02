package com.kt.corp.building.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kt.corp.building.dto.BuildingHomeDTO;

@Mapper
public interface BuildingMapper {
	
	public List<Map<String, Object>> selectHomeCompany(Map<String, Object> param);
	
	public List<Map<String, Object>> selectCompanyFav(BuildingHomeDTO dto);
	
	public List<Map<String, Object>> selectCompanyFavUser(BuildingHomeDTO dto);
	
	public int insertCompanyFav(BuildingHomeDTO dto);
	
	public int updateCompanyFav(BuildingHomeDTO dto);
	
	public int deleteCompanyFav(BuildingHomeDTO dto);

}
