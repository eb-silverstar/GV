package com.kt.corp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.kt.corp.comm.BaseComm;
import com.kt.corp.dto.DatabaseDTO;
import com.kt.corp.mapper.TestMapper;
import com.kt.corp.util.CommonUtil;

@Service("testService")
public class TestService extends BaseComm {
	
	@Autowired
	TestMapper mapper;
	
	public List getDatabases(Map params) throws Exception {
		return mapper.getDatabases(params);
	}
	
	public List getConpType(Map params) throws Exception {
		return mapper.getConpType(params);
	}
	
	public List getConpList(Map params) throws Exception {
		List conpList = mapper.getConpList(params);
		List rList = new ArrayList<HashMap<String, Object>>();
		
		if( !CollectionUtils.isEmpty(conpList) ) {
			for(int i=0 ;i<conpList.size() ;i++ ) {
				Map item = (Map)conpList.get(i);
				Integer conpId = Integer.parseInt(item.get("conp_id").toString());
				item.put("conpId", conpId);
				
				List tagList = this.getConpTag(item);
				item.put("tagList", tagList);
				item.remove("conpId");
				
				rList.add(item);
			}
		}
		
		return rList;
	}
	
	public List getConpTag(Map params) throws Exception {
		return mapper.getConpTag(params);
	}
	
}
