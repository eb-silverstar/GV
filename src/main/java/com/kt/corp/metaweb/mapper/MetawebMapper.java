package com.kt.corp.metaweb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kt.corp.metaweb.dto.MetaWebDTO;
import com.kt.corp.metaweb.dto.MetaWebEmpmnDTO;
import com.kt.corp.metaweb.dto.MetaWebHistDTO;
import com.kt.corp.metaweb.dto.MetaWebNewsDTO;
import com.kt.corp.metaweb.dto.MetaWebObjDTO;
import com.kt.corp.metaweb.dto.MetaWebTeamDTO;

@Mapper
public interface MetawebMapper {
	
	public int selectMetaWebCnt();

	public List<Map<String, Object>> selectSpaceMember(Map<String, Object> param);

	public int insertMetaWeb(MetaWebDTO dto);
	
	public int insertMetaWebHist(MetaWebHistDTO dto);
	
	public int insertMetaWebNews(MetaWebNewsDTO dto);
	
	public int insertMetaWebObj(MetaWebObjDTO dto);
	
	public int insertMetaWebGuidance(Map<String, Object> param);
	
	public int updateMetaWeb(MetaWebDTO dto);
	
	public int updateMetaWebHist(MetaWebHistDTO dto);
	
	public int updateMetaWebNews(MetaWebNewsDTO dto);
	
	public int updateMetaWebObj(MetaWebObjDTO dto);
	
	public MetaWebDTO selectMetaWeb(String comId);
	
	public MetaWebDTO selectMetaWebId(int metaWebId);
	
	public List<MetaWebHistDTO> selectMetaWebHist(MetaWebHistDTO dto);
	
	public List<MetaWebNewsDTO> selectMetaWebNews(MetaWebNewsDTO dto);
	
	public MetaWebObjDTO selectMetaWebObj(int metaWebId);
	
	public int selectMetaWebIntroImgCnt(int metaWebId);
	
	public int insertMetaWebIntroImg(Map<String, Object> param);
	
	public List<Map<String, Object>> selectMetaWebIntroImg(int metaWebId);
	
	public int deleteMetaIntroImg(List<Integer> delList);
	
	public List<Map<String, Object>> selectMetaWebGuidance(MetaWebDTO dto);
	
	public Map<String, Object> selectMetaWebAboutUs(String comId);
	
	public int insertMetaWebTeam(MetaWebTeamDTO dto);
	
	public MetaWebTeamDTO selectMetaWebTeam(int metaWebId);
	
	public int deleteMetaWebTeam(int metaWebId);
	
	public int insertMetaWebEmpmn(MetaWebEmpmnDTO dto);
	
	public List<MetaWebEmpmnDTO> selectMetaWebEmpmn(MetaWebEmpmnDTO dto);
	
	public int deleteMetaWebEmpmn(int seq);
	
	public int updateMetaWebEmpmn(MetaWebEmpmnDTO dto);
	
}
