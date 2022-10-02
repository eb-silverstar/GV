package com.kt.corp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kt.corp.comm.BaseComm;
import com.kt.corp.service.TestService;
import com.kt.corp.util.CommonUtil;

/**
 * @Project : 
 * @FileName : TestController.java
 * @Comment : 테스트 위한 클래스
 * @author :
 * @since : 2022.7.13
 * @category 테스트 - 테스트하위
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseComm {
	
	@Resource(name = "testService")
	TestService testService;
	
	
	
	/**
	 * @category DataBase 목록
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value= "/database/get.do", method=RequestMethod.GET)
	public Map<String, Object> getDatabase(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam Map<String, Object> map) throws Exception {
		
		this.logger.debug("Get map >> " + map.toString());
		
		
		List<Map<String, Object>> list = this.testService.getDatabases(map);
		
		if(list != null && list.size() > 0) {
			this.logger.debug("Get DataBase size >> " + list.size());
		}
		
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("databases", list);
		
		return json;
	}
	
	/**
	 * @category DataBase 목록
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value= "/database/post.do", method=RequestMethod.POST)
	public Map<String, Object> postDatabase(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam Map<String, Object> map) throws Exception {
		
		List list = this.testService.getDatabases(map);
		
		if(list != null && list.size() > 0) {
			this.logger.debug("Post DataBase size >> " + list.size());
		}
		
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("databases", list);
		
		return json;
	}
	
	/**
	 * @category DataBase 목록
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value= "/database/put.do", method=RequestMethod.PUT)
	public Map<String, Object> putDatabase(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam Map<String, Object> map) throws Exception {
		
		List list = this.testService.getDatabases(map);
		
		if(list != null && list.size() > 0) {
			this.logger.debug("Put DataBase size >> " + list.size());
		}
		
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("databases", list);
		
		return json;
	}
	
	/**
	 * @category DataBase 목록
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value= "/database/delete.do", method=RequestMethod.DELETE)
	public Map<String, Object> deleteDatabase(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam Map<String, Object> map) throws Exception {
		
		List list = this.testService.getDatabases(map);
		
		if(list != null && list.size() > 0) {
			this.logger.debug("DELETE DataBase size >> " + list.size());
		}
		
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("databases", list);
		
		return json;
	}
	
	/**
	 * @category DataBase 목록
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value= "/database/conpTypeList.do", method=RequestMethod.GET)
	public Map<String, Object> conpTypeList(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam Map<String, Object> map) throws Exception {
		
		this.logger.debug("/database/conpTypeList.do params >> " + map.toString());
		
		List<Map<String, Object>> list = this.testService.getConpType(map);
		
		if(list != null && list.size() > 0) {
			this.logger.debug("List size >> " + list.size());
		}
		
		return CommonUtil.getResultData(list);
	}
	
	
	/**
	 * @category DataBase 목록
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value= "/database/conpList.do", method=RequestMethod.GET)
	public Map<String, Object> conpList(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam Map<String, Object> map) throws Exception {
		
		this.logger.debug("/database/conpList.do params >> " + map.toString());
		
		List<Map<String, Object>> list = this.testService.getConpList(map);
		
		if(list != null && list.size() > 0) {
			this.logger.debug("List size >> " + list.size());
		}
		
		return CommonUtil.getResultData(list);
	}
	
	/**
	 * @category DataBase 목록
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value= "/database/conpTagList.do", method=RequestMethod.GET)
	public Map<String, Object> conpTags(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam Map<String, Object> map) throws Exception {
		
		this.logger.debug("/database/conpTagList.do params >> " + map.toString());
		
		List<Map<String, Object>> list = this.testService.getConpTag(map);
		
		if(list != null && list.size() > 0) {
			this.logger.debug("List size >> " + list.size());
		}
		
		return CommonUtil.getResultData(list);
	}



}













