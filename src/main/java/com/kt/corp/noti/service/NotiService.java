package com.kt.corp.noti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.corp.comm.ApiException;
import com.kt.corp.comm.BaseComm;
import com.kt.corp.noti.dto.NotiDTO;
import com.kt.corp.noti.mapper.NotiMapper;

@Service
public class NotiService extends BaseComm {
	
	@Autowired
	NotiMapper notiMapper;
	
	/**
	 * 알림 목록 조회
	 * 
	 * @param userId
	 * @return notiMapper.getNotiList(userId)
	 */
	public List<NotiDTO> getNotiList(String userId) {
		return notiMapper.getNotiList(userId);
	}

	/**
	 * 개별 알림 조회
	 *
	 * @param seq
	 * @return
	 */
	public NotiDTO getNotiBySeq(Long seq) { return notiMapper.getNotiBySeq(seq); }
	
	/**
	 * 알림 생성
	 * 
	 * @param notiDto
	 */
	public void insertNoti(NotiDTO notiDto) {
		logger.info("Start insert NOTI ==> from " + notiDto.getNotiSender());
		
		switch (notiDto.getFl()) {
			// 내 일정 (수신인 : 사용자)
			case "CLD_EVT":
				if(notiDto.getMtgRole() == null) {
					throw new ApiException("E0011", "초대 수신인의 역할");
				}
			case "CLD_MTG":
				if(notiDto.getUserId() == null) {
					throw new ApiException("E0011", "수신인");
				}
				
				if(notiDto.getNotiSender() == null) {
					throw new ApiException("E0011", "발신인");
				}
				
				if(notiDto.getMtgDate() == null) {
					throw new ApiException("E0011", "회의 혹은 이벤트 일시");
				}
				
				if(notiDto.getMtgTit() == null) {
					throw new ApiException("E0011", "회의명 혹은 이벤트명");
				}
				
				notiMapper.insertNotiToUser(notiDto);
				break;
			
			// IR, 제휴/협력 (수신인 : 회사)
			case "IR_RES":
			case "IR_REQ":
			case "COOP":
				if(notiDto.getComSeq() == null) {
					throw new ApiException("E0011", "발신 회사");
				}
				
				if(notiDto.getNotiSender() == null) {
					throw new ApiException("E0011", "수신 회사");
				}
				
				notiMapper.insertNotiToCompany(notiDto);
				break;
				
			// 이벤트 (수신인 : 전체 사용자)
			case "EVT":
				if(notiDto.getNotiSender() == null) {
					throw new ApiException("E0011", "발신 회사");
				}
				
				if(notiDto.getMtgTit() == null) {
					throw new ApiException("E0011", "이벤트명");
				}
				
				notiMapper.insertNotiToAllUsers(notiDto);
				break;
				
			default:
				throw new ApiException("E0012", "필터");
		}
		
		logger.info("Success insert NOTI ==> from " + notiDto.getNotiSender());
	}
	
	/**
	 * 알림 읾음 처리
	 * 
	 * @param seq
	 */
	public void updateNotiRead(Long seq) {
		if(!notiMapper.existNoti(seq)) {
			throw new ApiException("E0012", "알림");
		}
		
		notiMapper.updateNotiRead(seq);
		logger.info("Success Update NOTI Read ==> " + seq);
	}
	
	/**
	 * 알림 삭제
	 * 
	 * @param seq
	 */
	public void deleteNoti(Long seq) {
		notiMapper.deleteNoti(seq);
	}

}
