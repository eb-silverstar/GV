package com.kt.corp.metaoffice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kt.corp.file.dto.FileDTO;
import com.kt.corp.metaoffice.dto.MetaofficeDTO;
import com.kt.corp.metaoffice.dto.MetaofficeMemberDTO;
import com.kt.corp.metaoffice.dto.MetaofficeNobdDTO;

@Mapper
public interface MetaofficeMapper {
	
	public Boolean existOfficeAuth(Map<String, String> map);
	
	public Boolean existOfficeNobd(Long seq);
	
	public MetaofficeDTO getOffice(String comId);
	
	public List<MetaofficeNobdDTO> getOfficeNobdList(String comId);
	
	public MetaofficeNobdDTO getOfficeNobd(Long seq);
	
	public FileDTO getOfficeNobdAtt(Long seq);
	
	public List<MetaofficeMemberDTO> getOfficeMembers(Map<String, String> map);
	
	public List<MetaofficeMemberDTO> getOfficeGuestsInUser(Map<String, String> map);
	
	public List<MetaofficeMemberDTO> getOfficeGuestsInGuest(Map<String, String> map);
	
	public void insertOfficeNobd(MetaofficeNobdDTO officeNobd);
	
	public void updateOfficeNobd(MetaofficeNobdDTO officeNobd);
	
	public void updateOfficeNobdAtt(MetaofficeNobdDTO officeNobd);
	
	public void deleteOfficeNobd(Long seq);

}
