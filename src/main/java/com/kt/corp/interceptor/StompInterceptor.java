package com.kt.corp.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.kt.corp.Constant;
import com.kt.corp.comm.BaseComm;
import com.kt.corp.jwt.TokenProvider;
import com.kt.corp.space.mapper.SpaceMapper;
import com.kt.corp.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StompInterceptor extends BaseComm implements ChannelInterceptor {
	
	private final TokenProvider tokenProvider;
	
	private final SpaceMapper spaceMapper;
	
	private final UserMapper userMapper;
	
//	private final MessageChannel clientOutboundChannel;
	
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		
		if(StompCommand.CONNECT.equals(accessor.getCommand())) {
			logger.info("STOMP CONNECT.");
			
			if(!accessor.getFirstNativeHeader("userType").equals("guest")) {
				// Token 검증
				if(!tokenProvider.validateToken(Objects.requireNonNull(accessor.getFirstNativeHeader(Constant.AUTHORIZATION_HEADER)).substring(Constant.AUTH_PREFIX.length()+1))) {
					logger.error("STOMP CONNECT ERROR! Invalid Token! [token=" + accessor.getNativeHeader(Constant.AUTHORIZATION_HEADER) + "]");
					throw new AccessDeniedException("STOMP CONNECT ERROR! Invalid Token!");
				}
			}
		}
		
		if(StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
			// 공간 멤버 Upsert
			String userType = accessor.getFirstNativeHeader("userType");
			String userId = accessor.getFirstNativeHeader("userId");
			String destination = accessor.getDestination();
			
			logger.info("STOMP SUBSCRIBE. [userType=" + userType + ", userId=" + userId + ", destination=" + destination + "]");
			
			if((userType.equals("user") && userMapper.existUser(userId)) || (userType.equals("guest") && userMapper.existGuest(userId))) {
				String spaceType = destination.split("/")[2];
				
				switch(spaceType) {
					case "openworld":
						spaceType = "OW";
						break;
					case "event":
						spaceType = "EZ";
						break;
					case "metaoffice":
						spaceType = "MO";
						break;
					case "metaweb":
						spaceType = "MW";
						break;
				}
				
				Map<String, String> map = new HashMap<>();
				if(!spaceType.equals("OW")) {
					map.put("spaceId", destination.split("/")[3]);
				}
				map.put("spaceType", spaceType);
				map.put("userType", userType);
				map.put("userId", userId);
				
				spaceMapper.insertSpaceMember(map);
				
			} else {
				logger.error("STOMP SUBSCRIBE ERROR! Do not exist user! [userType=" + userType + ", userId=" + userId + "]");
			}
			
//			StompHeaderAccessor headerAccessor = StompHeaderAccessor.create(StompCommand.MESSAGE);
//			headerAccessor.setDestination(accessor.getDestination());
//			headerAccessor.setSubscriptionId(accessor.getSubscriptionId());
//			headerAccessor.setSessionId(accessor.getSessionId());
//			
//			clientOutboundChannel.send(MessageBuilder.createMessage(new byte[0], headerAccessor.getMessageHeaders()));
		}
		
//		if(StompCommand.UNSUBSCRIBE.equals(accessor.getCommand())) {
//		}
		
		if(StompCommand.DISCONNECT.equals(accessor.getCommand())) {
			// 공간 멤버 삭제
			String userType = accessor.getFirstNativeHeader("userType");
			String userId = accessor.getFirstNativeHeader("userId");
			
			logger.info("STOMP DISCONNECT. [userType=" + userType + ", userId=" + userId + "]");
			
			if(userId != null) {
				spaceMapper.deleteSpaceMember(userId);
			}
		}
		
		return message;
	}

}
