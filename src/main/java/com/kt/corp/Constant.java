package com.kt.corp;

public class Constant {
	
	// ************************** 메일 발송 타입  ***************************//
	public static final String MAIL_SEND_CERT = "CERT";										// 인증메일
	public static final String MAIL_SEND_INVITE = "INVITE";									// 초대메일
	public static final String MAIL_SEND_PW_TEMPORARY = "PW_TEMP";			// 임시 비밀번호 메일
	
	public static final String AUTHORIZATION_HEADER = "X-AUTH-TOKEN";		// 토큰 해더
	public static final String AUTH_PREFIX = "AUTHKT";										// 토큰 접두사
	
	public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
	
	public static final String FILE_TYPE_GUEST = "GUEST";										
	public static final String FILE_TYPE_AVATAR = "AVATAR";									
	public static final String FILE_TYPE_METAWEB_MAIN = "MWM";									
	public static final String FILE_TYPE_METAWEB_INTRO = "MWI";									
	
	public static final String  USER_STATE_CD1 =  "CODE001"; 					// 업무중
	public static final String  USER_STATE_CD2 =  "CODE002"; 					// 오프라인
	public static final String  USER_STATE_CD3 =  "CODE003"; 					// 자리비움
	public static final String  USER_STATE_CD4 =  "CODE004"; 					// 회의중
	public static final String  USER_STATE_CD5 =  "CODE005"; 					// 방해금지
	
	public static final int  USER_ENTRY_TYPE1 =  1; 									// PC 
	public static final int  USER_ENTRY_TYPE2 =  2; 									// mobile
	public static final int  USER_ENTRY_TYPE3 =  3; 									// 테블릿
	
	
	public static final String ERR_0001 = "E0001";										// 일반오류
	public static final String ERR_0011 = "E0011";										// {0}은/는 필수 정보입니다.
	public static final String ERR_0012 = "E0012";										// 중복되는 이메일
	public static final String ERR_0013 = "E0013";										// 업로드 가능한 파일 형식은 {0} 입니다.
	public static final String ERR_0015 = "E0015";										// 업로드 가능한 파일 용량은 최대 {0} 입니다.
	public static final String ERR_0016 = "E0016";										// 잘못된 메일 형식
	public static final String ERR_0017 = "E0017";										// 이미 존재하는 {0}입니다.
	public static final String ERR_0018 = "E0018";										// 잘못된 데이터 유효성
	public static final String ERR_0019 = "E0019";										// 동일 문자 포함
	public static final String ERR_0020 = "E0020";										// 공백 포함
	public static final String ERR_0021 = "E0021";										// 포함된 이메일 계정
	
	public static final String ERR_1001 = "E1001";										// 잘못된 JWT 서명
	public static final String ERR_1002 = "E1002";										// 만료된 JWT 토큰
	public static final String ERR_1003 = "E1003";										// 지원되지 않는 JWT 토큰
	public static final String ERR_1004 = "E1004";										// 잘못된 JWT 토큰
	public static final String ERR_1005 = "E1005";										// 토큰 사용자 불일치
	
	public static final String ERR_1101 = "E1101";										// 로그아웃
	public static final String ERR_1102 = "E1102";										// 잘못된 비밀번호
	public static final String ERR_1103 = "E1103";										// 없는 사용자 정보 
	public static final String ERR_1104 = "E1104";										// 승인 미처리된 사용자 정보 
	public static final String ERR_1105 = "E1105";										// 비밀번호 5회 초과
	
	static {
		
	}
}
