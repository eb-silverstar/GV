package com.kt.corp.metaweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.corp.Constant;
import com.kt.corp.comm.ApiException;
import com.kt.corp.comm.BaseComm;
import com.kt.corp.metaweb.dto.MetaWebDTO;
import com.kt.corp.metaweb.dto.MetaWebEmpmnDTO;
import com.kt.corp.metaweb.dto.MetaWebHistDTO;
import com.kt.corp.metaweb.dto.MetaWebNewsDTO;
import com.kt.corp.metaweb.dto.MetaWebObjDTO;
import com.kt.corp.metaweb.dto.MetaWebTeamDTO;
import com.kt.corp.metaweb.mapper.MetawebMapper;

@Service
public class MetawebService extends BaseComm{
	
	@Autowired private MetawebMapper metawebMapper;
	
	
	public List<Map<String, Object>> selectSpaceMember(Map<String, Object> param){
		return this.metawebMapper.selectSpaceMember(param);
	}

	public int insertMetaWeb(MetaWebDTO dto) {
		int cnt = this.metawebMapper.selectMetaWebCnt();
		dto.setMetaWebId(cnt);
		
		return this.metawebMapper.insertMetaWeb(dto);
	}
	
	public int insertMetaWebHist(MetaWebHistDTO dto) {
		return this.metawebMapper.insertMetaWebHist(dto);
	}
	
	public int insertMetaWebNews(MetaWebNewsDTO dto) {
		return this.metawebMapper.insertMetaWebNews(dto);
	}
	
	public int insertMetaWebObj(MetaWebObjDTO dto) {
		return this.metawebMapper.insertMetaWebObj(dto);
	}
	
	public int updateMetaWeb(MetaWebDTO dto) {
		return this.metawebMapper.updateMetaWeb(dto);
	}
	
	public int updateMetaWebHist(MetaWebHistDTO dto) {
		return this.metawebMapper.updateMetaWebHist(dto);
	}
	
	public int updateMetaWebNews(MetaWebNewsDTO dto) {
		return this.metawebMapper.updateMetaWebNews(dto);
	}
	
	public int updateMetaWebObj(MetaWebObjDTO dto) {
		return this.metawebMapper.updateMetaWebObj(dto);
	}

	public MetaWebDTO selectMetaWeb(String comId) {
		MetaWebDTO dto = this.metawebMapper.selectMetaWeb(comId);
		
		if(dto != null)	dto.setIntroImgs(this.metawebMapper.selectMetaWebIntroImg(dto.getMetaWebId()));
		else					throw new ApiException(Constant.ERR_1103, "");
		
		return dto;
	}
	
	public List<MetaWebHistDTO> selectMetaWebHist(MetaWebHistDTO dto){
		return this.metawebMapper.selectMetaWebHist(dto);
	}
	
	public List<MetaWebNewsDTO> selectMetaWebNews(MetaWebNewsDTO dto){
		return this.metawebMapper.selectMetaWebNews(dto);
	}
	
	public MetaWebObjDTO selectMetaWebObj(int metaWebId) {
		return this.metawebMapper.selectMetaWebObj(metaWebId);
	}
	
	public int selectMetaWebIntroImgCnt(int metaWebId) {
		return this.metawebMapper.selectMetaWebIntroImgCnt(metaWebId);
	}
	
	public int insertMetaWebIntroImg(Map<String, Object> param) {
		return this.metawebMapper.insertMetaWebIntroImg(param);
	}
	
	public List<Map<String, Object>> selectMetaWebIntroImg(int metaWebId){
		return this.metawebMapper.selectMetaWebIntroImg(metaWebId);
	}
	
	public int insertMetaWebGuidance(Map<String, Object> param) {
		MetaWebDTO dto = new MetaWebDTO();
		dto.setComId(param.get("comId").toString());
		dto.setMetaWebId((int)param.get("metaWebId"));
		dto.setUserId(param.get("userId").toString());
		
		List<Map<String, Object>> list = this.metawebMapper.selectMetaWebGuidance(dto);
		
		if(list != null && list.size() > 0) return 0;
		else return this.metawebMapper.insertMetaWebGuidance(param);
	}
	
	public List<Map<String, Object>> selectMetaWebGuidance(MetaWebDTO dto){
		return this.metawebMapper.selectMetaWebGuidance(dto);
	}
	
	public Map<String, Object> selectMetaWebAboutUs(String comId){
		return this.metawebMapper.selectMetaWebAboutUs(comId);
	}
	
	public int insertMetaWebTeam(MetaWebTeamDTO dto) {
		return this.metawebMapper.insertMetaWebTeam(dto);
	}
	
	public MetaWebTeamDTO selectMetaWebTeam(int metaWebId) {
		return this.metawebMapper.selectMetaWebTeam(metaWebId);
	}
	
	public int deleteMetaWebTeam(int metaWebId) {
		return this.metawebMapper.deleteMetaWebTeam(metaWebId);
	}
	
	public int insertMetaWebEmpmn(MetaWebEmpmnDTO dto) {
		return this.metawebMapper.insertMetaWebEmpmn(dto);
	}
	
	public List<MetaWebEmpmnDTO> selectMetaWebEmpmn(MetaWebEmpmnDTO dto){
		return this.metawebMapper.selectMetaWebEmpmn(dto);
	}
	
	public int deleteMetaWebEmpmn(int seq) {
		return this.metawebMapper.deleteMetaWebEmpmn(seq);
	}
	
	public int updateMetaWebEmpmn(MetaWebEmpmnDTO dto) {
		return this.metawebMapper.updateMetaWebEmpmn(dto);
	}

}









