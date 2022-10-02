package com.kt.corp.nobd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.corp.comm.BaseComm;
import com.kt.corp.nobd.dto.NobdDTO;
import com.kt.corp.nobd.mapper.NobdMapper;

@Service
public class NobdService extends BaseComm {
	
	@Autowired
	NobdMapper nobdMapper;
	
	/**
	 * 공지사항 목록 조회
	 * 
	 * @return nobdMapper.getNobdList()
	 */
	public List<NobdDTO> getNobdList() {
		return nobdMapper.getNobdList();
	}
	
	/**
	 * 공지사항 내용 조회
	 * 
	 * @param seq
	 * @return nobdMapper.getNobdDetail(seq)
	 */
	public NobdDTO getNobdDetail(int seq) {
		return nobdMapper.getNobdDetail(seq);
	}
	
	/**
	 * 공지사항 등록
	 * 
	 * @param nobdDto
	 * @return result
	 */
	public Map<String, Object> insertNobd(NobdDTO nobdDto) {
		Map<String, Object> result = new HashMap<>();
		
		nobdMapper.insertNobd(nobdDto);
		
		if(nobdDto.getSeq() > 0) {
			logger.info("Success insert notice board ==> " + nobdDto.getSeq());
			result.put("seq", nobdDto.getSeq());
		}
		
		return result;
	}
	
	/**
	 * 공지사항 수정
	 * 
	 * @param nobdDto
	 * @return result
	 */
	public Map<String, Object> updateNobd(NobdDTO nobdDto) {
		Map<String, Object> result = new HashMap<>();
		
		nobdMapper.updateNobd(nobdDto);
		
		logger.info("Success update notice board ==> " + nobdDto.getSeq());
		result.put("seq", nobdDto.getSeq());
		
		return result;
	}
	
	/**
	 * 공지사항 삭제
	 * 
	 * @param seq
	 */
	public void deleteNobd(int seq) {
		nobdMapper.deleteNobd(seq);
	}

}
