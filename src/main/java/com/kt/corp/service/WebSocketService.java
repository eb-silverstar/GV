package com.kt.corp.service;

import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.kt.corp.comm.BaseComm;


@Service
@ServerEndpoint(value = "/chatt")
public class WebSocketService extends BaseComm{
	
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session session) {
		this.logger.debug("open session : " + session.toString());
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("connection success~");
			
			if(!clients.contains(session)) {
				clients.add(session);
				this.logger.debug(">> session open ");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@OnMessage
	public void onMessage(String msg, Session session) {
		this.logger.debug("msg session : " + session.toString());
		this.logger.debug("send data : " + msg);
		try {
			for(Session s : clients) {
				if(!session.getId().equals(s.getId())) {
					JSONParser parser = new JSONParser();
					JSONObject json = (JSONObject)parser.parse(msg);
					json.put("userId", session.getId());
					
					s.getBasicRemote().sendText(json.toJSONString());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@OnError
	public void onError(Throwable e, Session session) {
		this.logger.debug("err session : " + session.toString());
	}
	
	@OnClose
	public void onClose(Session session) {
		this.logger.debug("close session : " + session.toString());
		String id = session.getId();
		clients.remove(session);
		
		try {
			for(Session s : clients) {
				s.getBasicRemote().sendText("close session >> " + id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
