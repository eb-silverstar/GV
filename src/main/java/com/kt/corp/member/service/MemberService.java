package com.kt.corp.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.corp.bizcard.dto.BizcardDTO;
import com.kt.corp.bizcard.mapper.BizcardMapper;
import com.kt.corp.comm.ApiException;
import com.kt.corp.member.dto.ComTeamDTO;
import com.kt.corp.member.dto.MemberDTO;
import com.kt.corp.member.dto.MemberListDTO;
import com.kt.corp.member.dto.PteGroupDTO;
import com.kt.corp.member.dto.UpdateMemberDTO;
import com.kt.corp.member.dto.UpdatePteGroupDTO;
import com.kt.corp.member.mapper.MemberMapper;
import com.kt.corp.user.mapper.UserMapper;

@Service
public class MemberService {
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	BizcardMapper bizcardMapper;
	
	/**
	 * 프라이빗 그룹 생성/수정/삭제 Validation Check
	 * 
	 * @param updatePteGroupDtoList
	 */
	public void validPteGroupList(List<UpdatePteGroupDTO> updatePteGroupDtoList) {
		for(UpdatePteGroupDTO updatePteGroupDto : updatePteGroupDtoList) {
			String userId = updatePteGroupDto.getUserId();
			Long grpId = updatePteGroupDto.getGrpId();
			String grpNm = updatePteGroupDto.getGrpNm();
			
			Map<String, Object> map = new HashMap<>();
			map.put("userId", userId);
			map.put("grpId", grpId);
			
			if(!userMapper.existUser(userId)) {
				throw new ApiException("E0012", "사용자");
			}
			
			if(updatePteGroupDto.getType().equals("C")) {
				if(grpNm.length() > 10) {
					throw new ApiException("E0022", "10");
				}
				
				for(String memId : updatePteGroupDto.getGrpMembers()) {
					map.put("memId", memId);
					
					if(!memberMapper.existPteMember(map)) {
						throw new ApiException("E0012", "프라이빗 멤버");
					}
				}
				
			} else if(updatePteGroupDto.getType().equals("U")) {
				if(!memberMapper.existPteGroup(map)) {
					throw new ApiException("E0012", "그룹");
				}
				
				for(String memId : updatePteGroupDto.getGrpMembers()) {
					map.put("memId", memId);
					
					if(!memberMapper.existPteMember(map)) {
						throw new ApiException("E0012", "프라이빗 멤버");
					}
				}
				
			} else if(updatePteGroupDto.getType().equals("D")) {
				if(!memberMapper.existPteGroup(map)) {
					throw new ApiException("E0012", "그룹");
				}
			}			
		}
	}
	
	/**
	 * 멤버 목록 조회
	 * 
	 * @param userId
	 * @return memberListDto
	 */
	public MemberListDTO getMemberList(String userId) {
		MemberListDTO memberListDto = new MemberListDTO();
		
		// 내 정보 조회
		memberListDto.setMyPf(memberMapper.getMember(userId));
		
		// 즐겨찾기 멤버 조회
		memberListDto.setFavMembers(memberMapper.getFavMembers(userId));
		
		// 우리 멤버 조회
		memberListDto.setComMembers(memberMapper.getComTeams(userId));
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		
		for(ComTeamDTO comTeamDto : memberListDto.getComMembers()) {
			map.put("teamId", comTeamDto.getTeamId());
			comTeamDto.setTeamMembers(memberMapper.getTeamMembers(map));
		}
		
		// 프라이빗 멤버 조회
		memberListDto.setPteMembers(getPteMembers(userId));
		
		List<PteGroupDTO> pteGroups = getPteGroups(userId);
		
		// 프라이빗 그룹 멤버 조회
		for(PteGroupDTO pteGroup : pteGroups) {
			pteGroup.setGrpMembers(memberMapper.getPteGroupMembers(pteGroup.getGrpId()));
		}
		
		memberListDto.setPteGroups(pteGroups);
		
		return memberListDto;
	}
	
	/**
	 * 멤버 검색 (사용자 이름 OR 회사 이름)
	 * 
	 * @param userId
	 * @param keyword
	 * @return memberMapper.getMemberByName(map)
	 */
	public List<MemberDTO> getMemberByName(String userId, String keyword) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("keyword", keyword);
		
		return memberMapper.getMemberByName(map);
	}
	
	/**
	 * 프라이빗 그룹 목록 조회
	 * 
	 * @param userId
	 * @return memberMapper.getPteGroups(userId)
	 */
	public List<PteGroupDTO> getPteGroups(String userId) {
		return memberMapper.getPteGroups(userId);
	}
	
	/**
	 * 프라이빗 멤버 목록 조회
	 * 
	 * @param userId
	 * @return memberMapper.getPteMembers(userId)
	 */
	public List<MemberDTO> getPteMembers(String userId) {
		return memberMapper.getPteMembers(userId);
	}
	
	/**
	 * 프라이빗 그룹 및 그룹 멤버 목록 조회
	 * 
	 * @param grpId
	 * @return pteGroupDto
	 */
	public PteGroupDTO getPteGroupMembers(Long grpId) {
		PteGroupDTO pteGroupDto = memberMapper.getPteGroup(grpId);
		pteGroupDto.setGrpMembers(memberMapper.getPteGroupMembers(grpId));
		
		return pteGroupDto;
	}
	
	/**
	 * 즐겨찾기 멤버 등록
	 * 
	 * @param insertMemberDto
	 */
	public void insertFavMember(UpdateMemberDTO updateMemberDto) {
		if(!userMapper.existUser(updateMemberDto.getUserId())) {
			throw new ApiException("E0012", "사용자");
		}
		
		if(!userMapper.existUser(updateMemberDto.getMemId())) {
			throw new ApiException("E0012", "사용자");
		}
		
		if(memberMapper.existFavMember(updateMemberDto)) {
			throw new ApiException("E0021", "즐겨찾기");
		}
		
		memberMapper.insertFavMember(updateMemberDto);
	}
	
	/**
	 * 프라이빗 멤버 등록
	 * 
	 * @param updateMemberDto
	 */
	public void insertPteMember(UpdateMemberDTO updateMemberDto) {
		String userId = updateMemberDto.getUserId();
		String memId = updateMemberDto.getMemId();
		Long grpId = updateMemberDto.getGrpId();
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("memId", memId);
		map.put("grpId", grpId);
		
		if(!userMapper.existUser(userId)) {
			throw new ApiException("E0012", "사용자");
		}
		
		if(!userMapper.existUser(memId)) {
			throw new ApiException("E0012", "사용자");
		}
		
		if(memberMapper.existPteMember(map)) {
			throw new ApiException("E0021", "프라이빗 멤버");
		}
		
		if(memberMapper.existComMember(updateMemberDto)) {
			throw new ApiException("E0021", "우리 멤버");
		}
		
		memberMapper.insertPteMember(updateMemberDto);
		
		// 그룹 설정
		if(grpId != null && memberMapper.existPteGroup(map)) {			
			if(memberMapper.existPteGroupMember(updateMemberDto)) {
				throw new ApiException("E0021", "프라이빗 그룹 멤버");
			}
			
			memberMapper.insertPteGroupMember(map);
		}
	}
	
	/**
	 * 프라이빗 그룹 생성/수정/삭제
	 * 
	 * @param updatePteGroupDtoList
	 */
	public void postPteGroup(List<UpdatePteGroupDTO> updatePteGroupDtoList) {
		for(UpdatePteGroupDTO updatePteGroupDto : updatePteGroupDtoList) {
			String userId = updatePteGroupDto.getUserId();
			Long grpId = updatePteGroupDto.getGrpId();
			
			Map<String, Object> map = new HashMap<>();
			map.put("userId", userId);
			map.put("grpId", grpId);
			
			// 신규 그룹 생성
			if(updatePteGroupDto.getType().equals("C")) {
				// 그룹 생성
				memberMapper.insertPteGroup(updatePteGroupDto);
				
				// 그룹 멤버 등록
				for(String memId : updatePteGroupDto.getGrpMembers()) {
					map.put("grpId", updatePteGroupDto.getGrpId());
					map.put("memId", memId);
					
					if(memberMapper.existPteMember(map)) {
						memberMapper.insertPteGroupMember(map);
					}
				}
				
			// 그룹 수정
			} else if(updatePteGroupDto.getType().equals("U")) {
				// 기존 그룹 멤버 삭제
				memberMapper.deletePteGroupMembersByGrpId(grpId);
				
				// 그룹 멤버 등록
				for(String memId : updatePteGroupDto.getGrpMembers()) {
					map.put("memId", memId);
					
					if(memberMapper.existPteMember(map)) {
						memberMapper.insertPteGroupMember(map);
					}
				}
				
			// 그룹 삭제
			} else if(updatePteGroupDto.getType().equals("D")) {
				memberMapper.deletePteGroupMembersByGrpId(grpId);
				memberMapper.deletePteGroup(grpId);
			}
		}
	}
	
	/**
	 * 프라이빗 그룹 수정
	 * 
	 * @param updatePteGroupDto
	 */
	public void updatePteGroup(UpdatePteGroupDTO updatePteGroupDto) {
		String userId = updatePteGroupDto.getUserId();
		Long grpId = updatePteGroupDto.getGrpId();
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("grpId", grpId);
		
		if(!userMapper.existUser(userId)) {
			throw new ApiException("E0012", "사용자");
		}
		
		if(!memberMapper.existPteGroup(map)) {
			throw new ApiException("E0012", "그룹");
		}
		
		if(updatePteGroupDto.getGrpNm().length() > 10) {
			throw new ApiException("E0022", "10");
		}
		
		// 그룹명 수정
		memberMapper.updatePteGroup(updatePteGroupDto);
		// 기존 그룹 멤버 삭제
		memberMapper.deletePteGroupMembersByGrpId(grpId);
		
		// 그룹 멤버 등록
		for(String memId : updatePteGroupDto.getGrpMembers()) {
			map.put("memId", memId);
			
			if(memberMapper.existPteMember(map)) {
				memberMapper.insertPteGroupMember(map);
			}
		}
	}
	
	/**
	 * 즐겨찾기 멤버 삭제
	 * 
	 * @param updateMemberDTO
	 */
	public void deleteFavMember(UpdateMemberDTO updateMemberDto) {
		memberMapper.deleteFavMember(updateMemberDto);
	}
	
	/**
	 * 프라이빗 멤버 삭제
	 * 
	 * @param updateMemberDto
	 */
	public void deletePteMember(UpdateMemberDTO updateMemberDto) {
		// 본인 멤버 목록에서 대상 삭제
		memberMapper.deletePteMember(updateMemberDto);
		memberMapper.deletePteGroupMember(updateMemberDto);
		memberMapper.deleteFavMember(updateMemberDto);
		
		// 본인 받은 명함 목록에서 대상 삭제
		BizcardDTO bizcardDto = new BizcardDTO();
		bizcardDto.setReceiverId(updateMemberDto.getUserId());
		bizcardDto.setSenderId(updateMemberDto.getMemId());
		bizcardMapper.deleteBizcardByUserId(bizcardDto);
		
		// 대상 멤버 목록에서 본인 삭제
		String userId = updateMemberDto.getMemId();
		String memId = updateMemberDto.getUserId();
		updateMemberDto.setUserId(userId);
		updateMemberDto.setMemId(memId);
		
		memberMapper.deletePteMember(updateMemberDto);
		memberMapper.deletePteGroupMember(updateMemberDto);
		memberMapper.deleteFavMember(updateMemberDto);
		
		// 대상 받은 명함 목록에서 본인 삭제
		bizcardDto.setReceiverId(userId);
		bizcardDto.setSenderId(memId);
		bizcardMapper.deleteBizcardByUserId(bizcardDto);
	}

}
