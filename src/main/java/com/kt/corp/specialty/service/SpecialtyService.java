package com.kt.corp.specialty.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.corp.comm.ApiException;
import com.kt.corp.specialty.dto.SpecialtyDTO;
import com.kt.corp.specialty.mapper.SpecialtyMapper;
import com.kt.corp.user.mapper.UserMapper;

@Service
public class SpecialtyService {
	
	@Autowired
	SpecialtyMapper specialtyMapper;
	
	@Autowired
	UserMapper userMapper;
	
	/**
	 * 전문분야 생성/수정 전 Validation Check
	 * 
	 * @param specialtyDto
	 */
	public void validSpecialty(SpecialtyDTO specialtyDto) {
		if(!userMapper.existUser(specialtyDto.getUserId())) {
			throw new ApiException("E0012", "사용자");
		}
		
		if(specialtyDto.getSpecialtyList().size() > 5) {
			throw new ApiException("E0023", "5");
		}
	}
	
	/**
	 * 전문분야 목록 조회
	 * 
	 * @param userId
	 * @return specialtyDto
	 */
	public SpecialtyDTO getSpecialtyList(String userId) {
		SpecialtyDTO specialtyDto = new SpecialtyDTO();
		specialtyDto.setSpecialtyList(specialtyMapper.getSpecialtyList(userId));
		
		return specialtyDto;
	}
	
	/**
	 * 전문분야 키워드로 검색
	 * 
	 * @param keyword
	 * @return
	 */
	public SpecialtyDTO getSpecialtyByKeyword(String keyword) {
		SpecialtyDTO specialtyDto = new SpecialtyDTO();
		specialtyDto.setSpecialtyList(specialtyMapper.getSpecialtyByKeyword(keyword));
		
		return specialtyDto;
	}
	
	/**
	 * 전문분야 생성 및 수정(Delete & Insert)
	 * 
	 * @param specialtyDto
	 */
	public void updateSpecialty(SpecialtyDTO specialtyDto) {
		String userId = specialtyDto.getUserId();
		List<String> specialtyList = specialtyDto.getSpecialtyList();
		
		// 기존 전문분야 삭제
		specialtyMapper.deleteSpecialty(userId);
		
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		
		for(String specialty : specialtyList) {
			map.put("specialty", specialty);
			specialtyMapper.insertSpecialty(map);
		}
	}

}
