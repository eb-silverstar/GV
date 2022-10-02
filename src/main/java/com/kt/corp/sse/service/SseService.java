package com.kt.corp.sse.service;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.kt.corp.comm.BaseComm;

@Service
public class SseService extends BaseComm {
	
	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;
	
	private ConcurrentHashMap<String, SseEmitter> emitterMap = new ConcurrentHashMap<>();
	
	/**
	 * SSE 연결
	 * 
	 * @param userId
	 * @return
	 */
	public SseEmitter connectSse(String userId) {
		logger.info("SSE CONNECTED. [userId=" + userId + "]");
		
		SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
		emitterMap.put(userId, emitter);
		
		emitter.onCompletion(() -> emitterMap.remove(userId));
		emitter.onTimeout(() -> emitterMap.remove(userId));
		
		// 503 Error 방지를 위한 더미 이벤트 전송
		sendToClient(emitter, "SSE CONNECTED. [userId=" + userId + "]");
		
		return emitter;
	}
	
	/**
	 * 실시간 알림 전송
	 * 
	 * @param emitter
	 * @param userId
	 * @param data
	 */
	private void sendToClient(SseEmitter emitter, Object data) {
		try {
			emitter.send(SseEmitter.event().name("sse").data(data));
		} catch(IOException e) {
			throw new RuntimeException("SSE CONNECTED ERROR!");
		}
	}
	
	/**
	 * 알림 푸시
	 * 
	 * @param userId
	 * @param content
	 */
	public void pushNoti(String userId, Object data) {
		logger.info("Push Noti to " + userId + ". [data=" + data + "]");
		
		if(emitterMap.containsKey(userId)) {
			sendToClient(emitterMap.get(userId), data);
		}
	}

}
