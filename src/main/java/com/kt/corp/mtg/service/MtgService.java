package com.kt.corp.mtg.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kt.corp.comm.ApiException;
import com.kt.corp.comm.BaseComm;
import com.kt.corp.file.dto.FileDTO;
import com.kt.corp.file.service.FileService;
import com.kt.corp.mtg.dto.MtgDTO;
import com.kt.corp.mtg.dto.MtgMemberDTO;
import com.kt.corp.mtg.mapper.MtgMapper;

@Service
public class MtgService extends BaseComm {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	MtgMapper mtgMapper;
	
	/**
	 * 회의 개설
	 * 
	 * @param mtgDto
	 */
	public void insertMtg(MtgDTO mtgDto, MultipartFile file) {
		logger.info("Create Meeting. [comId=" + mtgDto.getMtgComId() + ", userId=" + mtgDto.getMtgUserId() + "]");
		
		// 회의 생성
		mtgMapper.insertMtg(mtgDto);
		
		if(mtgDto.getSeq() != null) {
			FileDTO fileDto = new FileDTO();
			
			// 첨부파일 업로드
			if(file != null) {
				logger.info("Upload Meeting's Attach File. [seq=" + mtgDto.getSeq() + "]");
				
				fileDto = fileService.uploadAttFile(file);
				mtgDto.setMtgAttTit(fileDto.getFileName());
				mtgDto.setMtgAttPath(fileDto.getFilePath());
			}
			
			// 되풀이 회의 생성
			if(mtgDto.getMtgRecur() != null) {
				try {
					LocalDate startDate = LocalDate.parse(mtgDto.getMtgStartDate());
					LocalDate endDate = LocalDate.parse(mtgDto.getMtgEndDate());
					LocalDate mtgDate = startDate;
					
					// 매일
					if(mtgDto.getMtgRecur().equals("D")) {
						while(!mtgDate.isAfter(endDate)) {
							mtgDto.setMtgStartDate(mtgDate.toString());
							mtgDto.setMtgEndDate(mtgDate.toString());
							
							mtgMapper.insertMtgRecur(mtgDto);
							
							mtgDate = mtgDate.plusDays(1);
						}
					
					// 매주 ○요일
					} else if(mtgDto.getMtgRecur().equals("W")) {
						int recurDtl = Integer.parseInt(mtgDto.getMtgRecurDtl());
						
						mtgDate = startDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.of(recurDtl)));
						
						while(!mtgDate.isAfter(endDate)) {
							mtgDto.setMtgStartDate(mtgDate.toString());
							mtgDto.setMtgEndDate(mtgDate.toString());
							
							mtgMapper.insertMtgRecur(mtgDto);
							
							mtgDate = mtgDate.plusWeeks(1);
						}
					
					// 매월 ○일
					} else if(mtgDto.getMtgRecur().equals("MD")) {
						int recurDtl = Integer.parseInt(mtgDto.getMtgRecurDtl());
						
						if(recurDtl < startDate.getDayOfMonth()) {
							mtgDate = mtgDate.plusMonths(1);
						}
						
						mtgDate = mtgDate.withDayOfMonth(recurDtl);
						
						while(!mtgDate.isAfter(endDate)) {
							mtgDto.setMtgStartDate(mtgDate.toString());
							mtgDto.setMtgEndDate(mtgDate.toString());
							
							mtgMapper.insertMtgRecur(mtgDto);
							
							mtgDate = mtgDate.plusMonths(1);
						}					
					
					// 매월 ○주차 ○요일
					} else if(mtgDto.getMtgRecur().equals("MW")) {
						int recurWeek = Integer.parseInt(mtgDto.getMtgRecurDtl().split("")[0]);
						int recurDayOfWeek = Integer.parseInt(mtgDto.getMtgRecurDtl().split("")[1]);
						
						mtgDate = startDate.with(TemporalAdjusters.dayOfWeekInMonth(recurWeek, DayOfWeek.of(recurDayOfWeek)));
						
						if(mtgDate.isBefore(startDate)) {
							mtgDate = mtgDate.plusMonths(1).with(TemporalAdjusters.dayOfWeekInMonth(recurWeek, DayOfWeek.of(recurDayOfWeek)));
						}
						
						while(!mtgDate.isAfter(endDate)) {
							mtgDto.setMtgStartDate(mtgDate.toString());
							mtgDto.setMtgEndDate(mtgDate.toString());
							
							mtgMapper.insertMtgRecur(mtgDto);
							
							mtgDate = mtgDate.plusMonths(1).with(TemporalAdjusters.dayOfWeekInMonth(recurWeek, DayOfWeek.of(recurDayOfWeek)));
						}	
						
					} else {
						// 첨부파일 삭제
						if(fileDto != null) {
							fileService.deleteAttFile(fileDto);
						}
						
						// 회의 DB 삭제
						mtgMapper.deleteMtg(mtgDto.getSeq());
						
						throw new ApiException("E0026", "되풀이 주기 타입");
					}
					
				} catch(Exception e) {
					// 첨부파일 삭제
					if(fileDto != null) {
						fileService.deleteAttFile(fileDto);
					}
					
					// 회의 DB 삭제
					mtgMapper.deleteMtg(mtgDto.getSeq());
					mtgMapper.deleteMtgRecurs(mtgDto.getSeq());
					
					throw new ApiException("E0027", e.getMessage());
				}
				
			} else {
				// 바로 회의 & 예약 회의 첨부파일 정보 업데이트
				mtgMapper.updateMtgAtt(mtgDto);
			}
			
			// 참석자 등록
			Map<String, Object> map = new HashMap<>();
			map.put("mtgSeq", mtgDto.getSeq());
			
			for(MtgMemberDTO mtgMember : mtgDto.getMtgMembers()) {
				map.put("userId", mtgMember.getUserId());
				mtgMapper.insertMtgMember(map);
				
				logger.info("Success Insert Meeting Member. [seq=" + mtgDto.getSeq() + ", userId=" + mtgMember.getUserId() + "]");
				
				// 회의 생성 후 알림 및 일정 프로세스 (참석자)
				// 바로 회의
				if(mtgDto.getMtgStartDate() == null) {
					// TODO: 내 일정 생성
					// TODO: 푸시 알림
					
				// 예약 회의, 되풀이 회의
				} else {
					// TODO: 내 알림 생성
				}
			}
			
			// 회의 생성 후 일정 프로세스 (개설자)
			// TODO: 내 일정 생성
		}
		
	}

}
