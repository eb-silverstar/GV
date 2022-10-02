package com.kt.corp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessengerNoti {

	// private static Logger logger;
	private final Logger logger = LoggerFactory.getLogger(MessengerNoti.class);

	/** 메신져 알림 포트및 주소 **/
	private String ip 		= "10.50.28.46";	// 메신져 서버 ip테스트 서버는 111.60.3.73 포트는 5560  리얼서버는 61.78.74.94 포트는 5560 입니다.
	private int port 		= 5560;				// 메신져 서버 포트
	private String isUse 	= "Y";				// 메세지 전송여부.

	/** 생성자 */
	public MessengerNoti() {
	}

	/**
	 * 설명 : 메세지 전송.
	 */
//	public void sendMsg(String rcv_id, String msg, String url, String systemCd) throws MessengerNotiException {
//		try {
//			String[] comm = new String[8];
//			comm[0] = systemCd; 	// systemGubun; //구분값
//			comm[1] = "1"; 				// 예약구분 0:No 1:Yes
//			comm[2] = "3"; 				// 알림레벨 0:무조건열림 1:매우높음 2:높음 3:보통 4:낮음 5:매우낮음
//			comm[3] = rcv_id; 			// 수신자 ':'로 구분
//			comm[4] = msg; 			// 메세지 내용
//			comm[5] = url; 				// URL
//			comm[6] = ""; 				// 게시시작일
//			comm[7] = ""; 				// 게시종료일
//
//			this.socketSend(comm); // 소켓 생성 및 전송 라이브러리 함수 호출
//
//		} catch (SecurityException ie) {
//			logger.error(ie.getMessage(), ie);
//			throw new MessengerNotiException(ie.getMessage());
//		} catch (MessengerNotiException ie) {
//			logger.error(ie.getMessage(), ie);
//			throw new MessengerNotiException(ie.getMessage());
//		} catch (Exception ie) {
//			logger.error(ie.getMessage(), ie);
//			throw new MessengerNotiException(ie.getMessage());
//		}
//	}

	/**
	 * 소캣을 보내는 클래스
	 * 
	 * @param comm
	 * @return
	 */
//	public void socketSend(String[] comm) throws MessengerNotiException {
//
//		/** IO 클래스 **/
//		OutputStream os = null;
//		OutputStreamWriter osw = null;
//		InputStream in = null;
//		InputStreamReader isr = null;
//		BufferedReader br = null;
//
//		/** 소캣 클래스 **/
//		SocketAddress sockaddr = null;
//		Socket socket = null;
//
//		/************* 업무 구분 관련 ***************/
//		String gubun = ""; // 하나세상인지 오피스인지 구분
//		String reservegubun = ""; // 예약구분 1:Yes 0:No
//		String alimlevel = ""; // 알림레벨
//		String receiver = ""; // 수신자
//		String mesg = ""; // 내용
//		String fullurl = ""; // URL
//		String str_il = ""; // 게시시작일
//		String end_il = ""; // 게시종료일
//
//		try {
//			for (int m = 0; m < comm.length; m++) {
//				if (m == 0)	gubun = comm[m];
//				if (m == 1) 	reservegubun = comm[m];
//				if (m == 2) 	alimlevel = comm[m];
//				if (m == 3) 	receiver = comm[m];
//				if (m == 4) 	mesg = comm[m];
//				if (m == 5) 	fullurl = comm[m];
//				if (m == 6) 	str_il = comm[m];
//				if (m == 7) 	end_il = comm[m];
//			}
//
//			logger.info(":: socketSend :: 메신져 알림 서비스 시작 ");
//
//			sockaddr = new InetSocketAddress(ip, port); // Socket Instance...
//			logger.info(":: socketSend :: # messenger socket info ==> " + sockaddr);
//
//			socket = new Socket();
//			int timeoutMs = 15000; // 10 초 시간을 둔다.
//
//			logger.info(":: socketSend :: # prepareing socket connecting ");
//
//			String sendMsg = gubun + "♂" + reservegubun + "♂" + alimlevel + "♂" + receiver + "♂" + mesg + "♂" + fullurl + "♂" + str_il + "♂" + end_il;
//			// if ("Y".equals(isTest) )
//			// sendMsg = "┐┐"+ sendMsg +"└└";
//
//			socket.connect(sockaddr, timeoutMs);
//			logger.info(":: socketSend :: # connected messenger socket ");
//
//			os = socket.getOutputStream();
//			osw = new OutputStreamWriter(os, "EUC-KR");
//
//			in = socket.getInputStream();
//			isr = new InputStreamReader(in, "EUC-KR");
//			br = new BufferedReader(isr);
//
//			osw.write(sendMsg); // input은 Data
//			logger.debug(":: socketSend :: # DATA ==>" + sendMsg);
//
//			osw.flush(); // buffer에 있는내용을 뿌리고 비워준다...
//			br.close();
//			osw.close();
//
//			logger.info(":: socketSend :: 메신져 알림 서비스 종료 ");
//
//			// -----------------------------------------
//			// 예외처리
//			// -----------------------------------------
//		} catch (BindException bie) {
//			throw new MessengerNotiException(bie.getMessage());
//		} catch (ConnectException coe) {
//			throw new MessengerNotiException(coe.getMessage());
//		} catch (NoRouteToHostException noe) {
//			throw new MessengerNotiException(noe.getMessage());
//		} catch (ProtocolException pre) {
//			throw new MessengerNotiException(pre.getMessage());
//		} catch (UnknownHostException uhe) {
//			throw new MessengerNotiException(uhe.getMessage());
//		} catch (SocketException soe) {
//			logger.error(soe.getMessage(), soe);
//			throw new MessengerNotiException(soe.getMessage());
//		} catch (UnknownServiceException use) {
//			throw new MessengerNotiException(use.getMessage());
//		} catch (IOException ioe) {
//			throw new MessengerNotiException(ioe.getMessage());
//		} catch (Exception e) {
//			throw new MessengerNotiException(e.getMessage());
//		} finally {
//			if (br != null)
//				try { br.close(); } catch (Exception e) { logger.error(e.getMessage(), e); }
//			if (osw != null)
//				try { osw.close(); } catch (Exception e) { logger.error(e.getMessage(), e); }
//		}
//	}

}
