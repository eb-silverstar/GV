package com.kt.corp.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kt.corp.member.dto.ComTeamDTO;
import com.kt.corp.member.dto.MemberDTO;
import com.kt.corp.member.dto.PteGroupDTO;
import com.kt.corp.member.dto.UpdateMemberDTO;
import com.kt.corp.member.dto.UpdatePteGroupDTO;

@Mapper
public interface MemberMapper {
	
	public boolean existFavMember(UpdateMemberDTO updateMemberDto);
	
	public boolean existPteMember(Map<String, Object> map);
	
	public boolean existPteGroup(Map<String, Object> map);
	
	public boolean existPteGroupMember(UpdateMemberDTO updateMemberDto);
	
	public boolean existComMember(UpdateMemberDTO updateMemberDTO);
	
	public MemberDTO getMember(String userId);
	
	public List<MemberDTO> getFavMembers(String userId);
	
	public List<MemberDTO> getPteMembers(String userId);
	
	public List<ComTeamDTO> getComTeams(String userId);
	
	public List<MemberDTO> getTeamMembers(Map<String, Object> map);
	
	public PteGroupDTO getPteGroup(Long grpId);
	
	public List<PteGroupDTO> getPteGroups(String userId);
	
	public List<MemberDTO> getPteGroupMembers(Long grpId);
	
	public List<MemberDTO> getMemberByName(Map<String, String> map);
	
	public void insertFavMember(UpdateMemberDTO updateMemberDto);
	
	public void insertPteMember(UpdateMemberDTO updateMemberDto);
	
	public void insertPteGroupMember(Map<String, Object> map);
	
	public void insertPteGroup(UpdatePteGroupDTO updatePteGroupDto);
	
	public void updatePteGroup(UpdatePteGroupDTO updatePteGroupDto);
	
	public void deleteFavMember(UpdateMemberDTO updateMemberDto);
	
	public void deletePteMember(UpdateMemberDTO updateMemberDto);
	
	public void deletePteGroup(Long grpId);
	
	public void deletePteGroupMember(UpdateMemberDTO updateMemberDto);
	
	public void deletePteGroupMembersByGrpId(Long grpId);

}
