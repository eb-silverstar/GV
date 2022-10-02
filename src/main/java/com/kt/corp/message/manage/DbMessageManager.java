package com.kt.corp.message.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.kt.corp.comm.ContextWrapper;
import com.kt.corp.message.dto.MsgInfoDTO;
import com.kt.corp.message.mapper.MsgInfoMapper;

@Component
public class DbMessageManager {
	
	private static final Pattern msgAugsPattern = Pattern.compile("(\\{\\s*[0-9]+\\s*\\})", Pattern.CASE_INSENSITIVE);
	
	private static final Map<String, MsgInfoDTO> msgInfoMap = new HashMap<String, MsgInfoDTO>();
	
	/**
     * 사용자가 static getMessage 를 처음으로 호출하는 시점에 메시지 목록을 DB로부터 가져옴
     * 
     * @return msgInfoMap
     */
	private static Map<String, MsgInfoDTO> loadMsgInfo() {
		if(msgInfoMap.size() == 0) {
			Map<String, MsgInfoDTO> message = new HashMap<String, MsgInfoDTO>();
			
			MsgInfoMapper msgInfoMapper = ContextWrapper.getContext().getBean("msgInfoMapper", MsgInfoMapper.class);
			
			List<MsgInfoDTO> msgInfoList = msgInfoMapper.getAllMsgInfo();
			
			for(MsgInfoDTO msgInfoDto : msgInfoList) {
				message.put(msgInfoDto.getMsgCode(), msgInfoDto);
			}
			
			msgInfoMap.putAll(message);
		}
		
		return msgInfoMap;
	}
	
	/**
     * 메시지 코드로 부터 메시지 본문 가져오기
     * 
     * @param MSG_CODE String 메시지코드(MSG_INFO.MSG_CODE)
     * @param LANGUAGE_CODE String 메시지 언어
     * @return String 메시지본문 - 메시지 본문 반환
     */
    public static String getMessage(String MSG_CODE, String LANGUAGE_CODE) {
        return getMessage(MSG_CODE, null, LANGUAGE_CODE);
    }

    /**
     * 메시지 코드로 부터 메시지 본문 가져오기(메시지 파라메터 적용)
     * 
     * @param MSG_CODE String 메시지코드(MSG_INFO.MSG_CODE)
     * @param params Object 메시지 파라메터(1개)
     * @param LANGUAGE_CODE String 메시지 언어
     * @return String 메시지본문 - 메시지 본문 반환
     */
    public static String getMessage(String MSG_CODE, Object param, String LANGUAGE_CODE) {
        return getMessage(MSG_CODE, new Object[]{param}, LANGUAGE_CODE);
    }

    /**
     * 메시지 코드로 부터 메시지 본문 가져오기(메시지 파라메터 적용)
     * 
     * @param MSG_CODE String 메시지코드(MSG_INFO.MSG_CODE)
     * @param params Object[] 메시지 파라메터 배열
     * @param LANGUAGE_CODE String 메시지 언어
     * @return String 메시지본문 - 메시지 본문 반환
     */
    public static String getMessage(String MSG_CODE, Object[] params, String LANGUAGE_CODE) {
        Map<String, MsgInfoDTO> tempMsgInfoMap = DbMessageManager.loadMsgInfo();

        MsgInfoDTO msgInfo = tempMsgInfoMap.get(MSG_CODE);
        String messageContent = null;

        if(msgInfo != null) {
            if("en".equals(LANGUAGE_CODE)) {
                messageContent = msgInfo.getMsgEnDesc();
            } else if("ko".equals(LANGUAGE_CODE)) {
                messageContent = msgInfo.getMsgKoDesc();
            } else {
                messageContent = msgInfo.getMsgEnDesc();
            }

            if(messageContent != null && params != null) {
                StringBuffer sb = new StringBuffer();

                int matchedIndex = 0;
                Matcher matcher = msgAugsPattern.matcher(messageContent);

                while(matcher.find()) {
                    Object obj = null;
                    
                    try {
                        obj = params[matchedIndex];
                    } catch(IndexOutOfBoundsException ioobe) {
                        obj = "";
                    } catch(Exception e) {
                        obj = "";
                    }

                    String quoteExceptStr = Matcher.quoteReplacement(String.valueOf(obj));

                    matcher.appendReplacement(sb, quoteExceptStr);
                    matchedIndex++;
                }

                matcher.appendTail(sb);

                messageContent = sb.toString();
            }
        }

        return messageContent != null ? messageContent : "";
    }

}
