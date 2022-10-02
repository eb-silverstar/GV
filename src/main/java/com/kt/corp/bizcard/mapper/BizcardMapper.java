package com.kt.corp.bizcard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kt.corp.bizcard.dto.BizcardDTO;

@Mapper
public interface BizcardMapper {
	
	public boolean existBizcard(BizcardDTO bizcardDto);
	
	public List<BizcardDTO> getReadNBizcardList(String receiverId);
	
	public List<BizcardDTO> getReadYBizcardList(String receiverId);
	
	public List<BizcardDTO> getBizcardsByName(Map<String, String> map);
	
	public BizcardDTO getUserByBizcard(Long seq);
	
	public void insertBizcard(BizcardDTO bizcardDto);
	
	public void updateReadFlag(String receiverId);
	
	public void updateAcceptFlag(Long seq);
	
	public void deleteBizcardBySeq(Long seq);
	
	public void deleteBizcardByUserId(BizcardDTO bizcardDto);

}
