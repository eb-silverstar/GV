package com.kt.corp.mtg.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kt.corp.mtg.dto.MtgDTO;

@Mapper
public interface MtgMapper {
	
	public void insertMtg(MtgDTO mtgDto);
	
	public void insertMtgRecur(MtgDTO mtgDto);
	
	public void insertMtgMember(Map<String, Object> map);
	
	public void updateMtgAtt(MtgDTO mtgDto);
	
	public void deleteMtg(Long seq);
	
	public void deleteMtgRecurs(Long mtgSeq);

}
