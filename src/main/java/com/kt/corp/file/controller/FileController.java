package com.kt.corp.file.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.file.service.FileService;
import com.kt.corp.message.manage.DbMessageManager;
import com.kt.corp.metaweb.dto.MetaWebDTO;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "File", description = "파일 API")
@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	FileService fileService;

	/**
	 * 사용자 아바타(이미지 파일) 업로드
	 * 
	 * @param userId
	 * @param file
	 * @return
	 */
	@Operation(summary = "사용자 아바타(이미지 파일) 업로드", description = "사용자 아바타(이미지 파일)를 업로드 ")
	@PostMapping("/avatar")
	public ResponseEntity<ApiResponseEntity> postAvatar(@RequestPart(value = "userId") String userId, @RequestPart(value = "file") MultipartFile file) {
		fileService.uploadAvatar(userId, file);

		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), null, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 게스트 아바타(이미지 파일) 업로드
	 * 
	 * @param userId
	 * @param file
	 * @return
	 */
	@Operation(summary = "게스트 아바타(이미지 파일) 업로드", description = "게스트 아바타(이미지 파일)를 업로드 ")
	@PostMapping("/guest")
	public ResponseEntity<ApiResponseEntity> postGuest(@RequestParam String guestId, @RequestPart(value = "file") MultipartFile file) {
		Map<String, String> result = fileService.uploadGuest(guestId, file);

		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 메타웹 메인(이미지 파일) 업로드
	 * 
	 * @param metaWebId
	 * @param file
	 * @return
	 */
	@Operation(summary = "메타웹 메인(이미지 파일) 업로드", description = "메타웹 메인(이미지 파일)를 업로드 ")
	@PostMapping("/metawebMain")
	public ResponseEntity<ApiResponseEntity> postMetawebMain(@RequestParam String comId, @RequestPart(value = "file") MultipartFile file) {
		Map<String, String> result = fileService.uploadMetawebMainImg(comId, file);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 메타웹 인트로(이미지 파일) 업로드
	 * 
	 * @param metaWebId
	 * @param file
	 * @return
	 */
	@Operation(summary = "메타웹 인트로(이미지 파일) 업로드", description = "메타웹 인트로(이미지 파일)를 업로드 ")
	@PostMapping("/metawebIntro")
	public ResponseEntity<ApiResponseEntity> postMetawebIntro(@RequestParam int metaWebId, @RequestPart(value = "files") List<MultipartFile> files) {
		Map<String, String> result = fileService.uploadMetawebIntroImg(metaWebId, files);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 메타웹 인트로(이미지 파일) 삭제
	 * 
	 * @param Map
	 * @return
	 */
	@Operation(summary = "메타웹 인트로(이미지) 삭제", description = "메타웹 인트로(이미지) 삭제")
	@PostMapping("/remove-metawebIntro")
	public ResponseEntity<ApiResponseEntity> removeMetawebIntro(@RequestBody MetaWebDTO dto) {
		int result = fileService.deleteMetawebIntroImg(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
}
