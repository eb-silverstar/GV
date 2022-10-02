package com.kt.corp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.ibatis.io.Resources;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import com.kt.corp.comm.ApiException;
import com.kt.corp.comm.BaseComm;

@SuppressWarnings({"rawtypes","unchecked"})
public class CommonUtil extends BaseComm{
	
	
	
    
	public static Map getMap(HttpServletRequest req) {
		Map rmap = new HashMap();
		
		Enumeration en =  req.getParameterNames();
		while(en.hasMoreElements()) {
			String paramName = en.nextElement().toString();
			rmap.put(paramName, req.getParameter(paramName));
		}
		
		return rmap;
	}
	
	public static Map getSessMap(HttpServletRequest req) {
		Map rmap = getMap(req);
		
		HttpSession session = req.getSession();
		if( session.getAttribute("SESSIONDOMAIN") != null) {
			//TODO 세션값 추후 정의
			Map lmap = (Map)session.getAttribute("SESSIONDOMAIN");
//			rmap.put("SESS_USER_ID", lmap.get("USER_ID"));
//			rmap.put("SESS_USER_NM", lmap.get("USER_NAME"));
//			rmap.put("SESS_USER_LV", lmap.get("USER_LEVEL"));
//			rmap.put("SESS_USER_CODE", lmap.get("USER_CODE"));
//			rmap.put("SESS_USER_NAME", lmap.get("USER_NAME"));
//			rmap.put("SESS_AUTH_CODE", lmap.get("AUTH_CODE"));
//			rmap.put("SESS_LANG", lmap.get("LANGUAGE"));
//			rmap.put("SESS_EMAIL", lmap.get("EMAIL"));
//			rmap.put("SESS_TEL", lmap.get("TEL"));
//			rmap.put("SESS_PHONE", lmap.get("PHONE"));
//			rmap.put("SESS_EXPIRE_TIME", lmap.get("SESS_EXPIRE_TIME"));
		}
		rmap.put("SESS_IP", req.getRemoteAddr());
		rmap.put("SESS_ID", session.getId());
		
		return rmap;
	}
	
	public static String getJson(List src, String[] columnName) {
		if ((src == null) || (src.isEmpty())) {
			return "[]";
		}
		StringBuilder buf = new StringBuilder("[");
		for (int i = 0; i < src.size(); i++) {
			Object ele = src.get(i);
			buf.append(i == 0 ? "" : ",");
			try {
				buf.append(parseJson(ele, columnName));
			} catch (Exception e) {
				e.hashCode();
			}
		}
		buf.append("]");
		return buf.toString();
	}

	public static String getJson(List src) {
		if ((src == null) || (src.isEmpty())) {
			return "[]";
		}
		StringBuilder buf = new StringBuilder("[");
		for (int i = 0; i < src.size(); i++) {
			Object ele = src.get(i);
			buf.append(i == 0 ? "" : ",");
			try {
				buf.append(parseJson(ele, null));
			} catch (Exception e) {
				e.hashCode();
			}
		}
		buf.append("]");
		return buf.toString();
	}

	public static String getJson(Map map, String[] columnName) {
		if ((map == null) || (map.isEmpty())) {
			return "";
		}
		StringBuilder buf = new StringBuilder("");
		try {
			buf.append(parseJson(map, columnName));
		} catch (Exception e) {
			e.hashCode();
		}
		return buf.toString();
	}

	public static String getJson(Map map) {
		if ((map == null) || (map.isEmpty())) {
			return "";
		}
		StringBuilder buf = new StringBuilder("");
		try {
			buf.append(parseJson(map, null));
		} catch (Exception e) {
			e.hashCode();
		}
		return buf.toString();
	}

	private static String parseJson(Object obj, String[] columnName) throws Exception {
		int s = 0;
		if (obj == null) {
			return "";
		}
		if ((obj instanceof Map)) {
			Map map = (Map) obj;
			StringBuilder buf = new StringBuilder("{");
			Iterator<String> iter = map.keySet().iterator();
			for (; iter.hasNext();) {
				String key = (String) iter.next();
				if (columnName == null) {
					buf.append(s == 0 ? "" : ",");
					buf.append("\"").append(key).append("\":\"");
					buf.append(map.get(key) != null
							? map.get(key).toString().replace("'", "'").replace("\r\n", "\\r\\n").replace("\"", "\\\"")
									.replace("\r", "\\r").replace("\t", "\\t").replace("\n", "\\n")
							: "").append("\"");
					s++;
				} else {
					for (int i = 0; i < columnName.length; i++) {
						if (key.equals(columnName[i])) {
							buf.append(s == 0 ? "" : ",");
							buf.append("\"").append(key).append("\":\"");
							buf.append(
									map.get(key) != null
											? map.get(key).toString().replace("'", "'").replace("\r\n", "\\r\\n")
													.replace("\"", "\\\"").replace("\r", "\\r").replace("\t", "\\t")
													.replace("\n", "\\n")
											: "")
									.append("\"");
							s++;
						}
					}
				}
			}
			buf.append("}");
			return buf.toString();
		}
		if ((obj instanceof List)) {
			return getJson((List) obj, columnName);
		}
		throw new Exception();
	}
	
	/**
	 * @category UUID 생성
	 * @param
	 * @return
	 * @throws
	 */
	public static String getUuid() {
		//return UUID.randomUUID().toString().replaceAll("-", "");
		return UUID.randomUUID().toString();
	}
	
	/**
	 * @category 프로퍼티 value 값 호출
	 * @param
	 * @return
	 * @throws
	 */
	@SuppressWarnings("deprecation")
	public static String getProp(HttpServletRequest request, String prop){
		
		String prop_value = "";
		
		try{
//			String propFile = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/config/property/config.properties";
			Reader reader = Resources.getResourceAsReader("config/config.properties");
			Properties props = new Properties();// 프로퍼티 객체 생성
			props.load(reader);
//            FileInputStream fis = new FileInputStream(propFile);// 프로퍼티 파일 스트림에 담기
//            props.load(new java.io.BufferedInputStream(fis));// 프로퍼티 파일 로딩
            prop_value = props.getProperty(prop);
			
		}catch (Exception e){
			e.hashCode();
		}
		return prop_value;
	}
	
	
	/**
	 * @category 프로퍼티 value 값 호출
	 * @param prop
	 * @return
	 */
	public static String getProp(String prop) {
		String prop_value = "";
		
		try{
			Reader reader = Resources.getResourceAsReader("config/config.properties");
			Properties props = new Properties();
			props.load(reader);
			prop_value = props.getProperty(prop);
		}catch (Exception e){
			e.hashCode();
		}
		return prop_value;
	}
	
	
	public static void writeInfoLog(Log log, String...logMsg) {
	    //log4j의 설정값에서 로그 레벨이 INFO 인 경우에만 로그표시 
	    if(log.isInfoEnabled()) {
            if (logMsg != null) {
                for (int i=0;i<logMsg.length;i++) {
                    log.info(StringUtils.defaultIfBlank(logMsg[i], ""));
                }
            }
        }
	}
	
	
	/**
	 * 메소드에서 리턴페이지 반환 
	 * @param request
	 * @return
	 */
	public static String page(HttpServletRequest request) {
	    return StringUtils.replace(request.getRequestURI(), ".do", "");
    }
	
	
	/**
	 * 올바른 경로를 통한 접근여부 판단 
	 * @param request
	 * @return
	 */
	public static boolean isReferer(HttpServletRequest request){
		String referer = request.getHeader("referer");
		
		if (referer == null){
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
     * exclusiveParam 과 내장된 검사조건에 걸리는 다수의 파라미터만 뺀 나머지 파라미터 리턴.
     * @param request
     * @param ampChar
     * @param exclusiveParams
     * @return
     */
    public static String getParams(HttpServletRequest request, String ampChar, String ... exclusiveParams) {
        Enumeration paramNames = request.getParameterNames();
        ampChar = (ampChar == null)? "&amp;" : ampChar;
        StringBuffer params = new StringBuffer();
        
        while (paramNames.hasMoreElements()) {
            try {
                String k = (String) paramNames.nextElement();
                String v = java.net.URLEncoder.encode(request.getParameter(k), "UTF-8");
                if (isNotExistinExclusiveParams(k,exclusiveParams)) {
                    if (v != null) {
                        if (params.length() > 0) {
                            params.append(ampChar).append(k).append("=").append(v);
                        } else {
                            params.append(k).append("=").append(v);
                        }
                    }
                }
            } catch (Exception ex) { ex.hashCode(); }
        }   
        
        ampChar = null;
        paramNames = null;
        return params.toString();
    }
    
    
    public static boolean isNotExistinExclusiveParams(String value, String[] exclusiveParams) {
        boolean result = true;
        if (exclusiveParams == null) { return result; }
        
        for (String compareValue : exclusiveParams) {
        	if (compareValue.equals(value) || "auth".equals(value)) {
                return false;
            }
        }
        return result;
    }
	
    private static char[] charArr = {'a','b','c','d','e','f','g','h','i'
                                    ,'j','k','l','m','n','o','p','q','r'
                                    ,'s','t','u','v','w','x','y','z'};
    /**
    * 난수 발생을 하여 문자 조합
    * @param random
    * @return
    */
    private static String getRandomValue(SecureRandom random) {
        String result = "";
        int type = random.nextInt(2);
        int i = random.nextInt(26);
        
        if (type == 0) {
        	if(i < 0) return result;
            result = charArr[i] + "";
        } else {
            result = random.nextInt(10) + "";
        }
        
        return result;
    }

    /** 임시비밀번호 생성
    * @return
    */
    public static String createNewPassword() {
        SecureRandom random = new SecureRandom();
        StringBuffer sb = new StringBuffer();
        int i = random.nextInt(26);
        
        if(i < 0) return sb.toString();
        
        sb.append(charArr[i])
          .append(getRandomValue(random))
          .append(getRandomValue(random))
          .append(getRandomValue(random))
          .append(getRandomValue(random))
          .append(getRandomValue(random))
          .append(getRandomValue(random))
          .append(getRandomValue(random));
        return sb.toString();
    }
    
    
    public static String execute(String div, String model_id, String cmdPath) {
        Process process = null;
        Runtime runtime = Runtime.getRuntime();
        StringBuffer successOutput = new StringBuffer(); // 성공 스트링 버퍼
        StringBuffer errorOutput = new StringBuffer(); // 오류 스트링 버퍼
        BufferedReader successBufferReader = null; // 성공 버퍼
        BufferedReader errorBufferReader = null; // 오류 버퍼
        String msg = null; // 메시지
 
        List<String> cmdList = new ArrayList<String>();
 
        // 운영체제 구분 (window, window 가 아니면 무조건 linux 로 판단)
        if (System.getProperty("os.name").indexOf("Windows") > -1) {
            cmdList.add("cmd");
            cmdList.add("/c");
        } else {
            cmdList.add("/bin/sh");
            cmdList.add("-c");
        }
        // 명령어 셋팅
        String cmd = "";
        if (StringUtils.equals(div, "veri")) {
            cmd = "python3 "+cmdPath+"/GetCNNEvaluation.py --modelid '"+model_id+"'";
        } if (StringUtils.equals(div, "study")) {
        	cmd = "python3 "+cmdPath+"/GetCNNTraining.py --modelid '"+model_id+"'";
        } if (StringUtils.equals(div, "submit")) {
        	cmd = "python3 "+cmdPath+"/GetCNNSubmit.py --modelid '"+model_id+"'";
        }
        
        cmdList.add(cmd);
        String[] array = cmdList.toArray(new String[cmdList.size()]);
 
        String excuteSuccesYn = "Y";
        try {
 
            // 명령어 실행
            process = runtime.exec(array);
 
            // shell 실행이 정상 동작했을 경우
            successBufferReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));
 
            while ((msg = successBufferReader.readLine()) != null) {
                successOutput.append(msg + System.getProperty("line.separator"));
            }
 
            // shell 실행시 에러가 발생했을 경우
            errorBufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
            while ((msg = errorBufferReader.readLine()) != null) {
                errorOutput.append(msg + System.getProperty("line.separator"));
            }
 
            // 프로세스의 수행이 끝날때까지 대기
            process.waitFor();
 
            // shell 실행이 정상 종료되었을 경우
            if (process.exitValue() == 0) {
            } else {
                // shell 실행이 비정상 종료되었을 경우
                excuteSuccesYn = "N";
            }
 
            // shell 실행시 에러가 발생
            if (StringUtils.isNotEmpty(errorOutput.toString())) {
                // shell 실행이 비정상 종료되었을 경우
                excuteSuccesYn = "N";
            }
 
        } catch (IOException e) {
            e.hashCode();
            excuteSuccesYn = "N";
        } catch (InterruptedException e) {
            e.hashCode();
            excuteSuccesYn = "N";
        } finally {
            try {
                process.destroy();
                if (successBufferReader != null) successBufferReader.close();
                if (errorBufferReader != null) errorBufferReader.close();
            } catch (IOException e1) {
                e1.hashCode();
            }
        }
        
        return excuteSuccesYn;
    }
    
    /**
     * 문자열을 HTML 표현 태그로 변환 후 리턴
     * @param param
     * @return
     */
	public static String getDeRplcAll(String param){
		if ( param != null && param.length() > 0 ) {
			param = param.replaceAll("&amp;", "&");
			param = param.replaceAll("&lt;", "<");
			param = param.replaceAll("&gt;",">");		
			param = param.replaceAll("&#40;", "\\(");
			param = param.replaceAll("&#41;", "\\)");
			param = param.replaceAll("&quot;", "\"");
			param = param.replaceAll("&apos;","\'");
		}
		return param;
	}
	
	public static Map<String, Object> getResultData(List<Map<String, Object>> list){
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("data", list);
		json.put("status", "success");
		json.put("errorCode", "");
		json.put("errorMessage", "");
		return json;
	}
	
	public static void sendMail(Map<String, Object> mailMap, InternetAddress[] addArray) {
		String host = mailMap.get("host").toString(); 
		Integer port = (Integer)mailMap.get("port"); 
		String userId = mailMap.get("adminId").toString(); 
		String userPwd = mailMap.get("adminPwd").toString();
		String title = mailMap.get("title").toString(); 
		String msg = mailMap.get("msg").toString();
		
		Properties prop = new Properties();
		prop.put("mail.smtp.host", host);										// SMTP 서버 지정 
		prop.put("mail.smtp.port", port); 									// SMTP 포트
		prop.put("mail.smtp.auth", "true"); 
		prop.put("mail.smtp.ssl.enable", "true"); 
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(userId, userPwd); 
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userId));
			message.addRecipients(Message.RecipientType.TO, addArray);
			message.setSubject(title);
			message.setText(msg);
			
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			throw new ApiException("E0021", e.getMessage());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new ApiException("E0021", e.getMessage());
		}
	}
	
	public static void sendMailNew(Map<String, Object> mailMap, String tomail) {
		final String bodyEncoding = "UTF-8";
		
		String host = mailMap.get("host").toString(); 
		String port = mailMap.get("port").toString(); 
		String msg = mailMap.get("msg").toString();
	    String subject = mailMap.get("title").toString();
	    String fromEmail = mailMap.get("adminId").toString();
	    String fromUsername = "SYSTEM MANAGER";
	    String toEmail = tomail; 										// 콤마(,)로 여러개 나열
	    
	    final String username = mailMap.get("adminId").toString();         
	    final String password = mailMap.get("adminPwd").toString();
	    
	    // 메일에 출력할 텍스트
	    String html = msg;
	    
	    // 메일 옵션 설정
	    Properties props = new Properties();    
	    
	    if(host.contains("naver")) {
	    	props.put("mail.smtp.host", host);
	        props.put("mail.smtp.port", Integer.parseInt(port));
	        props.put("mail.smtp.auth", "true");
	    }else if(host.contains("gmail")) {
	    	props.put("mail.smtp.host", host); 
	    	props.put("mail.smtp.port", port); 
	    	props.put("mail.smtp.auth", "true");
	    	
	    	//TLS
	    	props.put("mail.smtp.starttls.enable", "true");
	    	
	    	//SSL
	    	//props.put("mail.smtp.ssl.enable", "true"); 
	    	//props.put("mail.smtp.ssl.trust", host);
	    	props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	    	
	    	//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    	//props.put("mail.smtp.socketFactory.fallback", "false");
	    	
	    }else {
	    	props.put("mail.transport.protocol", "smtp");
	    	props.put("mail.smtp.host", host);
	    	props.put("mail.smtp.port", port);
	    	props.put("mail.smtp.auth", "true");
	    	props.put("mail.smtp.user", username);
	    	props.put("mail.smtp.password", password);
	    	props.put("mail.smtp.starttls.enable", "true");
	    	props.put("mail.smtp.socketFactory.port", port);
	    	props.put("mail.smtp.quitwait", "false");
	    	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    	props.put("mail.smtp.socketFactory.fallback", "false");
	    	props.put("mail.smtp.ssl.trust", host);
	    	props.setProperty("mail.smtp.quitwait", "false");
	    }
	    
	    try {
	      // 메일 세션 생성
	      Session session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO 메일 서버  인증 계정 설정
					return new PasswordAuthentication(username, password); 
				}
			});
	      
	      // 메일 송/수신 옵션 설정
	      Message message = new MimeMessage(session);
	      message.setFrom(new InternetAddress(fromEmail, fromUsername));
	      message.setRecipients(RecipientType.TO, InternetAddress.parse(toEmail, false));
	      message.setSubject(subject);
	      message.setSentDate(new Date());
	      
	      // 메일 콘텐츠 설정
	      Multipart mParts = new MimeMultipart();
	      MimeBodyPart mTextPart = new MimeBodyPart();
	      MimeBodyPart mFilePart = null;
	 
	      // 메일 콘텐츠 - 내용
	      mTextPart.setText(html, bodyEncoding, "html");
	      mParts.addBodyPart(mTextPart);
	            
	      // 메일 콘텐츠 설정
	      message.setContent(mParts);
	      
	      // MIME 타입 설정
	      MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	      MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
	      MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
	      MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	      MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
	      MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
	      CommandMap.setDefaultCommandMap(MailcapCmdMap);
	 
	      // 메일 발송
	      Transport.send( message );
	    } catch ( Exception e ) {
	    	throw new ApiException("E0021", e.getMessage());
	    }
	}
	
	
	public static void sendGmail(Map<String, Object> mailMap, String tomail) {
		final String username = mailMap.get("adminId").toString();         
	    final String password = mailMap.get("adminPwd").toString();
	    
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		String receiver = tomail; // 메일 받을 주소
		String title = "테스트 메일입니다.";
		String content = "<h2 style='color:blue'>안녕하세요</h2>";
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("ncy7697@gmail.com", "관리자", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html; charset=utf-8");

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
    public static String getRamdomPassword(int size) {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&' };

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i=0; i<size; i++) {
            idx = sr.nextInt(len);    
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }
    
	public static Map<String, Object> setFailResponse(String exceptionCode, String msg) {
        Map<String, Object> responseJson = new HashMap<String, Object>();
        responseJson.put("status", "fail");
        responseJson.put("response", "");
        responseJson.put("errorCode", exceptionCode);
        responseJson.put("errorMessage", msg);

        return responseJson;
    }

}



















