package com.kt.corp.metaoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.file.dto.FileDTO;
import com.kt.corp.message.manage.DbMessageManager;
import com.kt.corp.metaoffice.dto.MetaofficeDTO;
import com.kt.corp.metaoffice.dto.MetaofficeMemberListDTO;
import com.kt.corp.metaoffice.dto.MetaofficeNobdDTO;
import com.kt.corp.metaoffice.dto.MetaofficeNobdListDTO;
import com.kt.corp.metaoffice.service.MetaofficeService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "Meta Office", description = "메타 오피스 API")
@RestController
@RequestMapping("/metaoffice")
public class MetaofficeController {
	
	@Autowired
	MetaofficeService metaofficeService;
	
	/**
	 * 메타 오피스 정보 조회
	 * 
	 * @return
	 */
	@Operation(summary = "메타 오피스 정보 조회", description = "메타 오피스 정보를 조회합니다.")
	@GetMapping("/{comId}")
	public ResponseEntity<ApiResponseEntity> getOffice(@PathVariable("comId") String comId) {
		MetaofficeDTO office = metaofficeService.getOffice(comId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), office, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 메타 오피스 공지사항 목록 조회
	 * 
	 * @param comId
	 * @param userId
	 * @return
	 */
	@Operation(summary = "메타 오피스 공지사항 목록 조회", description = "메타 오피스 공지사항 목록을 조회합니다.")
	@GetMapping("/{comId}/nobd")
	public ResponseEntity<ApiResponseEntity> getOfficeNobdList(@PathVariable("comId") String comId, @RequestParam String userId) {
		MetaofficeNobdListDTO officeNobds = metaofficeService.getOfficeNobdList(comId, userId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), officeNobds, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 메타 오피스 공지사항 내용 조회
	 * 
	 * @param seq
	 * @return
	 */
	@Operation(summary = "메타 오피스 공지사항 내용 조회", description = "메타 오피스 공지사항 내용을 조회합니다.")
	@GetMapping("/nobd/{seq}")
	public ResponseEntity<ApiResponseEntity> getOfficeNobd(@PathVariable("seq") Long seq) {
		MetaofficeNobdDTO officeNobd = metaofficeService.getOfficeNobd(seq);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), officeNobd, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 메타 오피스 공지사항 첨부파일 다운로드
	 * 
	 * @param seq
	 * @return
	 */
	@Operation(summary = "메타 오피스 공지사항 첨부파일 다운로드", description = "메타 오피스 공지사항의 첨부파일을 다운로드 합니다.")
	@GetMapping(value = "/nobd/{seq}/att", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> getOfficeNobdAtt(@PathVariable("seq") Long seq) {
		FileDTO fileDto = metaofficeService.getOfficeNobdAtt(seq);
		
		return new ResponseEntity<Resource>(fileDto.getResource(), fileDto.getHeaders(), HttpStatus.OK);
	}
	
	/**
	 * 메타 오피스 멤버 목록 조회
	 * 
	 * @param comId
	 * @param userId
	 * @return
	 */
	@Operation(summary = "메타 오피스 멤버 목록 조회", description = "메타 오피스 멤버 목록을 조회합니다.")
	@GetMapping("/{comId}/member")
	public ResponseEntity<ApiResponseEntity> getOfficeMemberList(@PathVariable("comId") String comId, @RequestParam String userId) {
		MetaofficeMemberListDTO officeMemberList = metaofficeService.getOfficeMemberList(comId, userId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), officeMemberList, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 메타 오피스 공지사항 등록
	 * 
	 * @param officeNobd
	 * @param file
	 * @return
	 */
	@Operation(summary = "메타 오피스 공지사항 등록", description = "메타 오피스 공지사항을 등록합니다.")
	@PostMapping("/nobd")
	public ResponseEntity<ApiResponseEntity> postOfficeNobd(@RequestPart MetaofficeNobdDTO officeNobd, @RequestPart(required = false) MultipartFile file) {
		metaofficeService.insertOfficeNobd(officeNobd, file);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 메타 오피스 공지사항 수정
	 * 
	 * @param officeNobd
	 * @param file
	 * @return
	 */
	@Operation(summary = "메타 오피스 공지사항 수정", description = "메타 오피스 공지사항을 수정합니다.")
	@PostMapping("/modify-nobd")
	public ResponseEntity<ApiResponseEntity> putOfficeNobd(@RequestPart MetaofficeNobdDTO officeNobd, @RequestPart(required = false) MultipartFile file) {
		metaofficeService.updateOfficeNobd(officeNobd, file);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 메타 오피스 공지사항 삭제
	 * 
	 * @param officeNobd
	 * @return
	 */
	@Operation(summary = "메타 오피스 공지사항 삭제", description = "메타 오피스 공지사항을 삭제합니다.")
	@PostMapping("/remove-nobd")
	public ResponseEntity<ApiResponseEntity> deleteOfficeNobd(@RequestBody MetaofficeNobdDTO officeNobd) {
		metaofficeService.deleteOfficeNobd(officeNobd);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}

}
