package com.kt.corp.nobd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kt.corp.nobd.dto.NobdDTO;

@Mapper
public interface NobdMapper {
	
	public List<NobdDTO> getNobdList();
	
	public NobdDTO getNobdDetail(int seq);
	
	public void insertNobd(NobdDTO nobdDto);
	
	public void updateNobd(NobdDTO nobdDto);
	
	public void deleteNobd(int seq);

}
