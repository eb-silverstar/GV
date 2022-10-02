package com.kt.corp.sse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.kt.corp.sse.service.SseService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "SSE", description = "실시간 알림 API")
@RestController
@RequestMapping("/sse")
public class SseController {
	
	@Autowired
	SseService sseService;
	
	/**
	 * SSE 연결
	 * 
	 * @param userId
	 * @return
	 */
	@Operation(summary = "SSE 연결", description = "SSE 연결합니다.")
	@GetMapping(value = "/{userId}", produces = "text/event-stream")
	public SseEmitter connectSse(@PathVariable("userId") String userId) {
		return sseService.connectSse(userId);
	}

}
