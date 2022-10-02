package com.kt.corp.stomp.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.corp.stomp.dto.StompDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class StompController {
	
	private final SimpMessageSendingOperations sendingOperations;
	
	/**
	 * 공간 좌표 송수신
	 * 
	 * @param space
	 * @param id
	 * @param stompDto
	 */
	@MessageMapping("/{space}/{id}")
	public void getMetaOfficeDetail(@DestinationVariable("space") String space, @DestinationVariable("id") String id, StompDTO stompDto) {		
		sendingOperations.convertAndSend("/space/" + space + "/" + id, stompDto);
	}

}
