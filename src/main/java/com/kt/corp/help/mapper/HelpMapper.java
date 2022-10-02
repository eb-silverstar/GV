package com.kt.corp.help.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kt.corp.help.dto.HelpDTO;

@Mapper
public interface HelpMapper {
	
	public List<HelpDTO> getHelpList();
	
	public HelpDTO getHelpDetail(int seq);
	
	public void insertHelp(HelpDTO helpDto);

}
