package com.kt.corp.bizcard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.corp.bizcard.dto.BizcardDTO;
import com.kt.corp.bizcard.dto.BizcardListDTO;
import com.kt.corp.bizcard.mapper.BizcardMapper;
import com.kt.corp.comm.ApiException;
import com.kt.corp.member.dto.UpdateMemberDTO;
import com.kt.corp.member.service.MemberService;
import com.kt.corp.user.mapper.UserMapper;

@Service
public class BizcardService {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BizcardMapper bizcardMapper;
	
	@Autowired
	UserMapper userMapper;
	
	/**
	 * 받은 명함 목록 조회
	 * 
	 * @param userId
	 * @return bizcardMapper.getBizcardList(userId)
	 */
	public BizcardListDTO getBizcardList(String userId) {
		BizcardListDTO bizcardListDto = new BizcardListDTO();
		bizcardListDto.setReadN(bizcardMapper.getReadNBizcardList(userId));
		bizcardListDto.setReadY(bizcardMapper.getReadYBizcardList(userId));
		
		return bizcardListDto;
	}
	
	/**
	 * 받은 명함 검색 (사용자 이름 OR 회사 이름)
	 * 
	 * @param userId
	 * @param keyword
	 * @return
	 */
	public List<BizcardDTO> getBizcardsByName(String userId, String keyword) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("keyword", keyword);
		
		return bizcardMapper.getBizcardsByName(map);
	}
	
	/**
	 * 명함 보내기 (받은 명함 등록)
	 * 
	 * @param bizcardDto
	 */
	public void insertBizcard(BizcardDTO bizcardDto) {
		if(!userMapper.existUser(bizcardDto.getReceiverId())) {
			throw new ApiException("E0012", "사용자");
		}
		
		if(!userMapper.existUser(bizcardDto.getSenderId())) {
			throw new ApiException("E0012", "사용자");
		}
		
		if(bizcardMapper.existBizcard(bizcardDto)) {
			throw new ApiException("E0017", "명함");
		}
		
		bizcardMapper.insertBizcard(bizcardDto);
	}
	
	/**
	 * 받은 명함 수락
	 * 
	 * @param seq
	 */
	public void acceptBizcard(Long seq) {
		BizcardDTO bizcardDto = bizcardMapper.getUserByBizcard(seq);
		
		// 수신자 프라이빗 멤버 등록
		UpdateMemberDTO updateMemberDto = new UpdateMemberDTO();
		updateMemberDto.setUserId(bizcardDto.getReceiverId());
		updateMemberDto.setMemId(bizcardDto.getSenderId());
		memberService.insertPteMember(updateMemberDto);
		
		// 송신자 프라이빗 멤버 등록
		updateMemberDto.setUserId(bizcardDto.getSenderId());
		updateMemberDto.setMemId(bizcardDto.getReceiverId());
		if(bizcardDto.getGrpId() != null) {
			updateMemberDto.setGrpId(bizcardDto.getGrpId());
		}
		memberService.insertPteMember(updateMemberDto);
		
		// 받은 명함 수락 처리
		bizcardMapper.updateAcceptFlag(seq);
	}
	
	/**
	 * 받은 명함 읽음 처리
	 * 
	 * @param userId
	 */
	public void updateReadFlag(String userId) {
		bizcardMapper.updateReadFlag(userId);
	}
	
	/**
	 * 받은 명함 삭제
	 * 
	 * @param seq
	 */
	public void deleteBizcardBySeq(Long seq) {
		bizcardMapper.deleteBizcardBySeq(seq);
	}

}
