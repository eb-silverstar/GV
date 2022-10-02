package com.kt.corp.metaoffice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kt.corp.comm.ApiException;
import com.kt.corp.comm.BaseComm;
import com.kt.corp.file.dto.FileDTO;
import com.kt.corp.file.service.FileService;
import com.kt.corp.metaoffice.dto.MetaofficeDTO;
import com.kt.corp.metaoffice.dto.MetaofficeMemberDTO;
import com.kt.corp.metaoffice.dto.MetaofficeMemberListDTO;
import com.kt.corp.metaoffice.dto.MetaofficeNobdDTO;
import com.kt.corp.metaoffice.dto.MetaofficeNobdListDTO;
import com.kt.corp.metaoffice.mapper.MetaofficeMapper;

@Service
public class MetaofficeService extends BaseComm {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	MetaofficeMapper metaofficeMapper;
	
	/**
	 * 메타 오피스 정보 조회
	 * 
	 * @param comId
	 * @return office
	 */
	public MetaofficeDTO getOffice(String comId) {
		MetaofficeDTO office = metaofficeMapper.getOffice(comId);
		// TODO: 회의 목록 조회 추가
		
		return office;
	}
	
	/**
	 * 메타 오피스 공지사항 목록 조회
	 * 
	 * @param comId
	 * @param userId
	 * @return officeNobds
	 */
	public MetaofficeNobdListDTO getOfficeNobdList(String comId, String userId) {
		logger.info("Get Office Notice Board List. [comId=" + comId + ", userId=" + userId + "]");
		
		Map<String, String> map = new HashMap<>();
		map.put("comId", comId);
		map.put("userId", userId);
		
		MetaofficeNobdListDTO officeNobds = new MetaofficeNobdListDTO();
		officeNobds.setOfficeNobds(metaofficeMapper.getOfficeNobdList(comId));
		officeNobds.setAuth(metaofficeMapper.existOfficeAuth(map));
		
		return officeNobds;
	}
	
	/**
	 * 메타 오피스 공지사항 내용 조회
	 * 
	 * @param seq
	 * @return officeMapper.getOfficeNobd(seq)
	 */
	public MetaofficeNobdDTO getOfficeNobd(Long seq) {
		logger.info("Get Office Notice Board Detail. [seq=" + seq + "]");
		
		return metaofficeMapper.getOfficeNobd(seq);
	}
	
	/**
	 * 메타 오피스 공지사항 첨부파일 다운로드
	 * 
	 * @param seq
	 * @return fileDto
	 */
	public FileDTO getOfficeNobdAtt(Long seq) {
		FileDTO fileDto = metaofficeMapper.getOfficeNobdAtt(seq);
		
		if(fileDto == null) {
			throw new ApiException("E0012", "첨부파일");
		}
		
		logger.info("Download Office Notice Board's Attach File. [seq=" + seq + "]");
		
		return fileService.downloadAttFile(fileDto);
	}
	
	/**
	 * 메타 오피스 멤버 목록 조회
	 * 
	 * @param comId
	 * @param userId
	 * @return officeMemberList
	 */
	public MetaofficeMemberListDTO getOfficeMemberList(String comId, String userId) {
		logger.info("Get Metaoffice Space Member List. [spaceId" + comId + "]");
		
		Map<String, String> map = new HashMap<>();
		map.put("comId", comId);
		map.put("userId", userId);		
		
		List<MetaofficeMemberDTO> inOfficeMembers = new ArrayList<>();
		List<MetaofficeMemberDTO> outOfficeMembers = new ArrayList<>();
		List<MetaofficeMemberDTO> officeGuests = new ArrayList<>();
		
		// 우리 멤버 조회
		List<MetaofficeMemberDTO> officeMembers = metaofficeMapper.getOfficeMembers(map);
		
		for(MetaofficeMemberDTO officeMember : officeMembers) {
			// 현재 메타 오피스에 접속 중이면서 상태 [업무중] 인 우리 멤버
			if(officeMember.getUserStateCode().equals("CODE001") && officeMember.getSpaceType().equals("MO") && officeMember.getSpaceId().equals(comId)) {
				inOfficeMembers.add(officeMember);
			// 그 외 우리 멤버
			} else {
				outOfficeMembers.add(officeMember);
			}
		}
		
		// 게스트 조회
		officeGuests.addAll(metaofficeMapper.getOfficeGuestsInUser(map));
		officeGuests.addAll(metaofficeMapper.getOfficeGuestsInGuest(map));
		
		MetaofficeMemberListDTO officeMemberList = new MetaofficeMemberListDTO();
		officeMemberList.setInOfficeMembers(inOfficeMembers);
		officeMemberList.setOutOfficeMembers(outOfficeMembers);
		officeMemberList.setOfficeGuests(officeGuests);
		
		return officeMemberList;
	}
	
	/**
	 * 메타 오피스 공지사항 등록
	 * 
	 * @param officeNobd
	 */
	public void insertOfficeNobd(MetaofficeNobdDTO officeNobd, MultipartFile file) {
		logger.info("Insert Office Notice Board. [userId=" + officeNobd.getUserId() + "]");
		
		Map<String, String> map = new HashMap<>();
		map.put("comId", officeNobd.getComId());
		map.put("userId", officeNobd.getUserId());
		
		if(!metaofficeMapper.existOfficeAuth(map)) {
			throw new ApiException("E0024", "회사 관리자");
		}
		
		if(officeNobd.getNobdTit() == null) {
			throw new ApiException("E0011", "제목");
		}
		
		if(officeNobd.getNobdDtl() == null) {
			throw new ApiException("E0011", "내용");
		}
		
		metaofficeMapper.insertOfficeNobd(officeNobd);
		
		if(officeNobd.getSeq() > 0L && file != null) {
			// 첨부파일 업로드 및 정보 업데이트
			logger.info("Upload Office Notice Board's Attach File. [seq=" + officeNobd.getSeq() + "]");
			
			FileDTO fileDto = fileService.uploadAttFile(file);
			officeNobd.setNobdAttTit(fileDto.getFileName());
			officeNobd.setNobdAttPath(fileDto.getFilePath());
			
			metaofficeMapper.updateOfficeNobdAtt(officeNobd);
		}
	}
	
	/**
	 * 메타 오피스 공지사항 수정
	 * 
	 * @param officeNobd
	 * @param file
	 */
	public void updateOfficeNobd(MetaofficeNobdDTO officeNobd, MultipartFile file) {
		logger.info("Update Office Notice Board. [userId=" + officeNobd.getUserId() + ", seq=" + officeNobd.getSeq() + "]");
		
		Map<String, String> map = new HashMap<>();
		map.put("comId", officeNobd.getComId());
		map.put("userId", officeNobd.getUserId());
		
		if(!metaofficeMapper.existOfficeNobd(officeNobd.getSeq())) {
			throw new ApiException("E0012", "게시글");
		}
		
		if(!metaofficeMapper.existOfficeAuth(map)) {
			throw new ApiException("E0024", "회사 관리자");
		}
		
		if(officeNobd.getNobdTit() == null) {
			throw new ApiException("E0011", "제목");
		}
		
		if(officeNobd.getNobdDtl() == null) {
			throw new ApiException("E0011", "내용");
		}
		
		metaofficeMapper.updateOfficeNobd(officeNobd);
		
		if(officeNobd.getUpdateAtt()) {
			FileDTO fileDto = metaofficeMapper.getOfficeNobdAtt(officeNobd.getSeq());
			
			// 기존 첨부파일 삭제
			if(fileDto != null) {
				fileService.deleteAttFile(fileDto);
				
				officeNobd.setNobdAttTit(null);
				officeNobd.setNobdAttPath(null);
			}
			
			// 새 첨부파일 업로드
			if(file != null) {
				logger.info("Upload Office Notice Board's Attach File. [seq=" + officeNobd.getSeq() + "]");
				
				fileDto = fileService.uploadAttFile(file);
				officeNobd.setNobdAttTit(fileDto.getFileName());
				officeNobd.setNobdAttPath(fileDto.getFilePath());
			}
			
			// 첨부파일 정보 업데이트
			metaofficeMapper.updateOfficeNobdAtt(officeNobd);
		}
	}
	
	/**
	 * 메타 오피스 공지사항 삭제
	 * 
	 * @param officeNobd
	 */
	public void deleteOfficeNobd(MetaofficeNobdDTO officeNobd) {
		logger.info("Delete Office Notice Board. [userId=" + officeNobd.getUserId() + ", comId=" + officeNobd.getComId() + ", seq=" + officeNobd.getSeq() + "]");
		
		Map<String, String> map = new HashMap<>();
		map.put("comId", officeNobd.getComId());
		map.put("userId", officeNobd.getUserId());
		
		if(!metaofficeMapper.existOfficeAuth(map)) {
			throw new ApiException("E0024", "회사 관리자");
		}
		
		// 첨부파일 삭제
		FileDTO fileDto = metaofficeMapper.getOfficeNobdAtt(officeNobd.getSeq());
		
		if(fileDto != null) {
			fileService.deleteAttFile(fileDto);
		}
		
		// 공지사항 DB 삭제
		metaofficeMapper.deleteOfficeNobd(officeNobd.getSeq());
	}

}
