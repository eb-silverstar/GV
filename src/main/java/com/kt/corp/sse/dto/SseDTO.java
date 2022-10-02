package com.kt.corp.sse.dto;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SseDTO {
	
	private SseEmitter emitter;

}
