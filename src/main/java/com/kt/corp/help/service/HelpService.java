package com.kt.corp.help.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.corp.comm.BaseComm;
import com.kt.corp.help.dto.HelpDTO;
import com.kt.corp.help.mapper.HelpMapper;

@Service
public class HelpService extends BaseComm {
	
	@Autowired
	HelpMapper helpMapper;
	
	/**
	 * 헬프데스크 문의 목록 조회
	 * 
	 * @return helpMapper.getHelpList()
	 */
	public List<HelpDTO> getHelpList() {
		return helpMapper.getHelpList();
	}
	
	/**
	 * 헬프데스크 문의 내용 조회
	 * 
	 * @param seq
	 * @return helpMapper.getHelpDetail(seq)
	 */
	public HelpDTO getHelpDetail(int seq) {
		return helpMapper.getHelpDetail(seq);
	}
	
	/**
	 * 헬프데스크 문의글 등록
	 * 
	 * @param helpDto
	 * @return result
	 */
	public Map<String, Object> insertHelp(HelpDTO helpDto) {
		Map<String, Object> result = new HashMap<>();
		
		helpMapper.insertHelp(helpDto);
		
		if(helpDto.getSeq() > 0) {
			logger.info("Success insert help desk ==> " + helpDto.getSeq());
			result.put("seq", helpDto.getSeq());
		}
		
		return result;
	}

}
