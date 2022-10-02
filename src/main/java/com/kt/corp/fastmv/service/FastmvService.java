package com.kt.corp.fastmv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.kt.corp.Constant;
import com.kt.corp.comm.ApiException;
import com.kt.corp.fastmv.dto.FastMoveDTO;
import com.kt.corp.fastmv.mapper.FastmvMapper;
import com.kt.corp.util.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FastmvService {
	
	@Value("${resources.upload-path}")
	String uploadPath;
	
	@Value("${admin.mail.url}")
	String adminMailUrl;
	
	@Autowired private FastmvMapper fastmvMapper;

	/**
	 * 빠른 이동 리스트
	 * 
	 * @param userId
	 * @return List<FastMoveDTO>
	 */
	public List<FastMoveDTO> selectFastmv(Map<String, Object> params){
		List<FastMoveDTO> list = this.fastmvMapper.selectFastmv(params);
		List<FastMoveDTO> resuts = new ArrayList<FastMoveDTO>();
		
		try {
			if(list != null && list.size() > 0) {
				for(FastMoveDTO dto : list) {
					String imgPath = uploadPath + dto.getImgLog();
					String imgBase64 =	FileUtil.getBase64String(imgPath);
					
					dto.setOfficeUrlPath(adminMailUrl + "/office?comId=" + dto.getComId());
					dto.setWebUrlPath(adminMailUrl + "/web?comId=" + dto.getComId());
					dto.setImgLog(imgBase64);
					
					resuts.add(dto);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ApiException(Constant.ERR_0001, "일반 오류");
		}
		
		return resuts;
	}
	
	/**
	 * 빠른 이동 삭제
	 * 
	 * @param userId
	 * @return int
	 */
	public int deleteFastmv(Map<String, Object> params) {
		return this.fastmvMapper.deleteFastmv(params);
	}
	
	/**
	 * 빠른 이동 수정
	 * 
	 * @param userId
	 * @return int
	 */
	public int updateFastmv(Map<String, Object> params) {
		return this.fastmvMapper.updateFastmv(params);
	}
	
}
