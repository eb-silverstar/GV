package com.kt.corp.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.member.dto.UpdateMemberDTO;
import com.kt.corp.member.dto.UpdatePteGroupDTO;
import com.kt.corp.member.dto.MemberDTO;
import com.kt.corp.member.dto.MemberListDTO;
import com.kt.corp.member.dto.PteGroupDTO;
import com.kt.corp.member.service.MemberService;
import com.kt.corp.message.manage.DbMessageManager;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "Member", description = "멤버 목록 API")
@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	/**
	 * 멤버 목록 조회
	 * 
	 * @return
	 */
	@Operation(summary = "멤버 목록 조회", description = "전체 멤버 목록을 조회합니다.")
	@GetMapping("/{userId}")
	public ResponseEntity<ApiResponseEntity> getMemberList(@PathVariable("userId") String userId) {
		MemberListDTO memberList =  memberService.getMemberList(userId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), memberList, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 멤버 검색 (사용자 이름 OR 회사 이름)
	 * 
	 * @return
	 */
	@Operation(summary = "멤버 검색", description = "전체 멤버 목록에서 사용자 이름 혹은 회사 이름으로 검색합니다.")
	@GetMapping("/{userId}/search")
	public ResponseEntity<ApiResponseEntity> getMemberByName(@PathVariable("userId") String userId, @RequestParam String keyword) {
		List<MemberDTO> members = memberService.getMemberByName(userId, keyword);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), members, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 프라이빗 그룹 목록 조회
	 * 
	 * @param userId
	 * @return
	 */
	@Operation(summary = "프라이빗 그룹 목록 조회", description = "프라이빗 그룹 목록을 조회합니다.")
	@GetMapping("/{userId}/groups")
	public ResponseEntity<ApiResponseEntity> getPteGroups(@PathVariable("userId") String userId) {
		List<PteGroupDTO> groups = memberService.getPteGroups(userId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), groups, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 프라이빗 멤버 목록 조회
	 * 
	 * @param userId
	 * @return
	 */
	@Operation(summary = "프라이빗 멤버 목록 조회", description = "프라이빗 멤버 목록을 조회합니다.")
	@GetMapping("/{userId}/pte")
	public ResponseEntity<ApiResponseEntity> getPteMembers(@PathVariable("userId") String userId) {
		List<MemberDTO> pteMembers = memberService.getPteMembers(userId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), pteMembers, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 프라이빗 그룹 및 그룹 멤버 목록 조회
	 * 
	 * @param grpId
	 * @return
	 */
	@Operation(summary = "프라이빗 그룹 및 그룹 멤버 목록 조회", description = "프라이빗 그룹 및 그룹 멤버 목록을 조회합니다.")
	@GetMapping("/group/{grpId}")
	public ResponseEntity<ApiResponseEntity> getPteGroupMembers(@PathVariable("grpId") Long grpId) {
		PteGroupDTO group = memberService.getPteGroupMembers(grpId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), group, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 프라이빗 그룹 생성/수정/삭제
	 * 
	 * @param updatePteGroupDto
	 * @return
	 */
	@Operation(summary = "프라이빗 그룹 생성/수정/삭제", description = "프라이빗 그룹을 생성/수정/삭제 합니다.")
	@PostMapping("/group")
	public ResponseEntity<ApiResponseEntity> postPteGroups(@RequestBody List<UpdatePteGroupDTO> updatePteGroupDto) {
		memberService.validPteGroupList(updatePteGroupDto);
		memberService.postPteGroup(updatePteGroupDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 즐겨찾기 멤버 등록
	 * 
	 * @return
	 */
	@Operation(summary = "즐겨찾기 멤버 등록", description = "멤버를 즐겨찾기 목록에 등록합니다.")
	@PostMapping("/fav")
	public ResponseEntity<ApiResponseEntity> postFavMember(@RequestBody UpdateMemberDTO updateMemberDto) {
		memberService.insertFavMember(updateMemberDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 프라이빗 그룹 수정
	 * 
	 * @param updatePteGroupDto
	 * @return
	 */
	@Operation(summary = "프라이빗 그룹 수정", description = "프라이빗 그룹을 수정합니다.")
	@PostMapping("/modify-group")
	public ResponseEntity<ApiResponseEntity> putPteGroup(@RequestBody UpdatePteGroupDTO updatePteGroupDto) {
		memberService.updatePteGroup(updatePteGroupDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 즐겨찾기 멤버 삭제
	 * 
	 * @return
	 */
	@Operation(summary = "즐겨찾기 멤버 삭제", description = "멤버를 즐겨찾기 목록에서 삭제합니다.")
	@PostMapping("/remove-fav")
	public ResponseEntity<ApiResponseEntity> deleteFavMember(@RequestBody UpdateMemberDTO updateMemberDto) {
		memberService.deleteFavMember(updateMemberDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 프라이빗 멤버 삭제
	 * 
	 * @return
	 */
	@Operation(summary = "프라이빗 멤버 삭제", description = "프라이빗 멤버를 삭제합니다.")
	@PostMapping("/remove-pte")
	public ResponseEntity<ApiResponseEntity> deletePteMember(@RequestBody UpdateMemberDTO updateMemberDto) {
		memberService.deletePteMember(updateMemberDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}

}
