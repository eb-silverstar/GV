package com.kt.corp.building.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.corp.building.dto.BuildingHomeDTO;
import com.kt.corp.building.mapper.BuildingMapper;
import com.kt.corp.comm.BaseComm;

@Service
public class BuildingService extends BaseComm{
	
	@Autowired	private BuildingMapper buildingMapper;

	/**
	 * 홈 건물 정보 조회
	 * 
	 * @param userId
	 */
	public List<Map<String, Object>> selectHomeCompany(String userId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		
		return this.buildingMapper.selectHomeCompany(param);
	}
	
	/**
	 * 홈 건물 관심 기업 등록/해제
	 * 
	 * @param BuildingHomeDTO
	 */
	public int updateHomeCompanyFav(BuildingHomeDTO dto){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", dto.getUserId());
		
		List<Map<String, Object>>  list = this.buildingMapper.selectCompanyFav(dto);
		param.put("comId", dto.getComId());
		
		if(list != null && list.size() > 0) {
			return this.buildingMapper.updateCompanyFav(dto);
		}else {
			return this.buildingMapper.insertCompanyFav(dto);
		}
	}
	
	/**
	 * 홈 건물 관심 기업 리스트
	 * 
	 * @param BuildingHomeDTO
	 */
	public List<Map<String, Object>> selectCompanyFavUser(BuildingHomeDTO dto) {
		return this.buildingMapper.selectCompanyFavUser(dto);
	}
	
}
