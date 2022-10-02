package com.kt.corp.metaweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.message.manage.DbMessageManager;
import com.kt.corp.metaweb.dto.MetaWebDTO;
import com.kt.corp.metaweb.dto.MetaWebEmpmnDTO;
import com.kt.corp.metaweb.dto.MetaWebHistDTO;
import com.kt.corp.metaweb.dto.MetaWebNewsDTO;
import com.kt.corp.metaweb.dto.MetaWebObjDTO;
import com.kt.corp.metaweb.dto.MetaWebTeamDTO;
import com.kt.corp.metaweb.service.MetawebService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "Metaweb", description = "메타웹 API")
@RestController
@RequestMapping("/metaweb")
public class MetawebController {
	
	@Autowired private MetawebService metawebService;
	
	/**
	 * 공간멤버 조회
	 * 
	 * @return
	 */
	@Operation(summary = "공간멤버 조회", description = "공간멤버를 조회 합니다.")
	@GetMapping("/{spaceId}/{spaceType}")
	public ResponseEntity<ApiResponseEntity> getNobdList(@PathVariable("spaceId") String spaceId, @PathVariable("spaceType") String spaceType, @RequestParam String userNm) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("spaceId", spaceId);
		param.put("spaceType", spaceType);
		param.put("userNm", userNm);
		
		 List<Map<String,Object>> list = this.metawebService.selectSpaceMember(param);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), list, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 회사소개 조회
	 * 
	 * @return
	 */
	@Operation(summary = "회사소개 조회", description = "회사소개를 조회 합니다.")
	@GetMapping("/about/{comId}")
	public ResponseEntity<ApiResponseEntity> getAboutUs( @PathVariable("comId") String comId) {
		Map<String, Object>  result = this.metawebService.selectMetaWebAboutUs(comId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사업소개 등록
	 * 
	 * @param Map
	 * @return
	 */
	@Operation(summary = "사업소개 등록", description = "사업소개 등록을 합니다.")
	@PostMapping("/biz")
	public ResponseEntity<ApiResponseEntity> registBiz(@RequestBody MetaWebDTO dto) {
		int result = this.metawebService.insertMetaWeb(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사업소개 수정
	 * 
	 * @param Map
	 * @return
	 */
	@Operation(summary = "사업소개 수정", description = "사업소개 수정을 합니다.")
	@PostMapping("/modify-biz")
	public ResponseEntity<ApiResponseEntity> modifyBiz(@RequestBody MetaWebDTO dto) {
		int result = this.metawebService.updateMetaWeb(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사업소개 조회
	 * 
	 * @return
	 */
	@Operation(summary = "사업소개 조회", description = "사업소개를 조회 합니다.")
	@GetMapping("/getBiz/{comId}")
	public ResponseEntity<ApiResponseEntity> getBiz(@PathVariable("comId") String comId) {
		MetaWebDTO dto = this.metawebService.selectMetaWeb(comId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), dto, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사업연혁 등록
	 * 
	 * @param Map
	 * @return
	 */
	@Operation(summary = "사업연혁 등록", description = "사업연혁 등록을 합니다.")
	@PostMapping("/bizHis")
	public ResponseEntity<ApiResponseEntity> registBizHis(@RequestBody MetaWebHistDTO dto) {
		int result = this.metawebService.insertMetaWebHist(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사업연혁 수정
	 * 
	 * @param Map
	 * @return
	 */
	@Operation(summary = "사업연혁 수정", description = "사업연혁 수정을 합니다.")
	@PostMapping("/modify-bizHis")
	public ResponseEntity<ApiResponseEntity> modifyBizHis(@RequestBody MetaWebHistDTO dto) {
		int result = this.metawebService.updateMetaWebHist(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사업연혁 조회
	 * 
	 * @return
	 */
	@Operation(summary = "사업연혁 조회", description = "사업연혁를 조회 합니다.")
	@GetMapping("/getBizHis/{metaWebId}")
	public ResponseEntity<ApiResponseEntity> getBizHis(@PathVariable("metaWebId") int metaWebId) {
		MetaWebHistDTO paramDto = new MetaWebHistDTO();
		paramDto.setMetaWebId(metaWebId);
		
		List<MetaWebHistDTO> list = this.metawebService.selectMetaWebHist(paramDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), list, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사업 뉴스 등록
	 * 
	 * @param Map
	 * @return
	 */
	@Operation(summary = "사업뉴스 등록", description = "사업뉴스 등록을 합니다.")
	@PostMapping("/bizNews")
	public ResponseEntity<ApiResponseEntity> registBizNews(@RequestBody MetaWebNewsDTO dto) {
		int result = this.metawebService.insertMetaWebNews(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사업 뉴스 수정
	 * 
	 * @param Map
	 * @return
	 */
	@Operation(summary = "사업뉴스 수정", description = "사업뉴스 수정을 합니다.")
	@PostMapping("/modify-bizNews")
	public ResponseEntity<ApiResponseEntity> modifyBizNews(@RequestBody MetaWebNewsDTO dto) {
		int result = this.metawebService.updateMetaWebNews(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사업뉴스 조회
	 * 
	 * @return
	 */
	@Operation(summary = "사업뉴스 조회", description = "사업뉴스를 조회 합니다.")
	@GetMapping("/getBizNews/{metaWebId}")
	public ResponseEntity<ApiResponseEntity> getBizNews(@PathVariable("metaWebId") int metaWebId) {
		MetaWebNewsDTO paramDto = new MetaWebNewsDTO();
		paramDto.setMetaWebId(metaWebId);
		
		 List<MetaWebNewsDTO> list = this.metawebService.selectMetaWebNews(paramDto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), list, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사업 컨텐츠 등록
	 * 
	 * @param Map
	 * @return
	 */
	@Operation(summary = "사업컨텐츠 등록", description = "사업컨텐츠 등록을 합니다.")
	@PostMapping("/bizCont")
	public ResponseEntity<ApiResponseEntity> registBizCont(@RequestBody MetaWebObjDTO dto) {
		int result = this.metawebService.insertMetaWebObj(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사업 컨텐츠 수정
	 * 
	 * @param Map
	 * @return
	 */
	@Operation(summary = "사업컨텐츠 수정", description = "사업컨텐츠 수정을 합니다.")
	@PostMapping("/modify-bizCont")
	public ResponseEntity<ApiResponseEntity> modifyBizCont(@RequestBody MetaWebObjDTO dto) {
		int result = this.metawebService.updateMetaWebObj(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 사업콘텐츠 조회
	 * 
	 * @return
	 */
	@Operation(summary = "사업콘텐츠 조회", description = "사업콘텐츠를 조회 합니다.")
	@GetMapping("/getBizCont/{metaWebId}")
	public ResponseEntity<ApiResponseEntity> getBizCont(@PathVariable("metaWebId") int metaWebId) {
		 MetaWebObjDTO dto = this.metawebService.selectMetaWebObj(metaWebId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), dto, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 안내데스크 담당자 등록
	 * 
	 * @param Map
	 * @return
	 */
	@Operation(summary = "안내데스크 담당자 등록", description = "안내데스크 담당자 등록을 합니다.")
	@PostMapping("/guidance/{comId}/{metaWebId}")
	public ResponseEntity<ApiResponseEntity> registGuidance(@PathVariable("comId") String comId, @PathVariable("metaWebId") int metaWebId, @RequestBody String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("comId", comId);
		param.put("metaWebId", metaWebId);
		
		int result = this.metawebService.insertMetaWebGuidance(param);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 안내데스크 담당자 조회
	 * 
	 * @return
	 */
	@Operation(summary = "안내데스크 담당자 조회", description = "안내데스크 담당자 조회 합니다.")
	@GetMapping("/guidance/{comId}/{metaWebId}")
	public ResponseEntity<ApiResponseEntity> getGuidance(@PathVariable("comId") String comId, @PathVariable("metaWebId") int metaWebId) {
		MetaWebDTO dto = new MetaWebDTO();
		dto.setComId(comId);
		dto.setMetaWebId(metaWebId);
		
		List<Map<String, Object>> list = this.metawebService.selectMetaWebGuidance(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), list, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 팀 문화 조회
	 * 
	 * @return
	 */
	@Operation(summary = "팀 문화 조회", description = "팀 문화 조회 합니다.")
	@GetMapping("/team/{metaWebId}")
	public ResponseEntity<ApiResponseEntity> getTeam(@PathVariable("metaWebId") int metaWebId) {
		MetaWebTeamDTO dto = this.metawebService.selectMetaWebTeam(metaWebId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), dto, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 팀 문화 등록
	 * 
	 * @param MetaWebTeamDTO
	 * @return
	 */
	@Operation(summary = "팀 문화 등록", description = "팀 문화 등록을 합니다.")
	@PostMapping("/team")
	public ResponseEntity<ApiResponseEntity> registTeam(@RequestBody MetaWebTeamDTO dto) {
		int result = this.metawebService.insertMetaWebTeam(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 팀 문화 삭제
	 * 
	 * @param MetaWebTeamDTO
	 * @return
	 */
	@Operation(summary = "팀 문화 삭제", description = "팀 문화 삭제 합니다.")
	@PostMapping("/remove-team/{metaWebId}")
	public ResponseEntity<ApiResponseEntity> removeTeam(@PathVariable("metaWebId") int metaWebId) {
		int result = this.metawebService.deleteMetaWebTeam(metaWebId);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 채용 공고 등록
	 * 
	 * @param MetaWebEmpmnDTO
	 * @return
	 */
	@Operation(summary = "채용 공고 등록", description = "채용 공고 등록을 합니다.")
	@PostMapping("/empmn")
	public ResponseEntity<ApiResponseEntity> registEmpmn(@RequestBody MetaWebEmpmnDTO dto) {
		int result = this.metawebService.insertMetaWebEmpmn(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 채용 공고 삭제
	 * 
	 * @param MetaWebTeamDTO
	 * @return
	 */
	@Operation(summary = "채용 공고 삭제", description = "채용 공고 삭제 합니다.")
	@PostMapping("/remove-empmn/{seq}")
	public ResponseEntity<ApiResponseEntity> removeEmpmn(@PathVariable("seq") int seq) {
		int result = this.metawebService.deleteMetaWebEmpmn(seq);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 채용 공고 수정
	 * 
	 * @param Map
	 * @return
	 */
	@Operation(summary = "채용 공고 수정", description = "채용 공고 수정을 합니다.")
	@PostMapping("/modify-empmn")
	public ResponseEntity<ApiResponseEntity> modifyEmpmn(@RequestBody MetaWebEmpmnDTO dto) {
		int result = this.metawebService.updateMetaWebEmpmn(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}
	
	/**
	 * 채용 공고 조회
	 * 
	 * @return
	 */
	@Operation(summary = "채용 공고 조회", description = "채용 공고 조회 합니다.")
	@GetMapping("/empmn/{metaWebId}")
	public ResponseEntity<ApiResponseEntity> getEmpmn(@PathVariable("metaWebId") int metaWebId, @RequestParam int seq) {
		MetaWebEmpmnDTO dto = new MetaWebEmpmnDTO();
		dto.setMetaWebId(metaWebId);
		dto.setSeq(seq);
		
		 List<MetaWebEmpmnDTO> list = this.metawebService.selectMetaWebEmpmn(dto);
		
		ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), list, null, null);
		
		return new ResponseEntity<ApiResponseEntity>(message, HttpStatus.OK);
	}

}
