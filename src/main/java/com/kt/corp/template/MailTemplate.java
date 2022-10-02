package com.kt.corp.template;

import com.kt.corp.Constant;

public class MailTemplate {
	
	public static String getCertMailHtml(String link, String mailUrl) {
		return getDoctypeHtml()
				+ "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
				+ getHeaderHtml("이메일 인증")
				+ getStyleHtml()
				+ getBodyHtml(link, mailUrl, Constant.MAIL_SEND_CERT)
				+ "</html>";
	}
	
	public static String getPwdMailHtml(String pw, String mailUrl) {
		return getDoctypeHtml()
				+ "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
				+ getHeaderHtml("임시 비밀번호 이메일")
				+ getStyleHtml()
				+ getBodyHtml(pw, mailUrl, Constant.MAIL_SEND_PW_TEMPORARY)
				+ "</html>";
	}
	
	private static String getDoctypeHtml() {
		return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
	}
	
	private static String getHeaderHtml(String title) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<head>");
		sb.append("	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		sb.append("	<title>"+  title +"</title>");
		sb.append("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
		sb.append("	<link href=\"https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap\" rel=\"stylesheet\">");
		sb.append("</head>");
		
		return sb.toString();
	}
	
	private static String getStyleHtml() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<style>");
		sb.append("	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap');");
		sb.append("</style>");
		
		return sb.toString();
	}
	
	private static String getBodyHtml(String link, String mailUrl, String mailType) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<body style=\"margin: 0;padding: 0;\">");
		sb.append("	<div class=\"view\">");
		sb.append("		<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"padding: 77px 0 213px;background: #f4f4f4;\">");
		sb.append("			<tr>");
		sb.append("				<td>");
		sb.append("					<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"628\" style=\"margin: 0 auto;background: #fff;text-align: center;font-family: 'Noto Sans KR';font-weight: 400;\">");
		sb.append("						<tr>");
		sb.append("							<td style=\"width: 100%; height: 5px;background: linear-gradient(101.91deg, #91dd8b -3.09%, #2d9bff 105.63%);\"></td>");
		sb.append("						</tr>");
		sb.append("						<tr>");
		sb.append("							<td height=\"60px\"></td>");
		sb.append("						</tr>");
		sb.append("						<tr>");
		sb.append("							<td align=\"center\">");
		sb.append("								<a href=\"#\">");
		sb.append("									<img src=\""+ mailUrl +"/img/bi.png\" alt=\"로고\">");
		sb.append("								</a>");
		sb.append("							</td>");
		sb.append("						</tr>");
		
		switch (mailType) {
			case Constant.MAIL_SEND_CERT: 					sb.append( getCertContHtml(link, mailUrl) ); break;
			case Constant.MAIL_SEND_INVITE: 					sb.append(""); break;
			case Constant.MAIL_SEND_PW_TEMPORARY: sb.append( getPwdContHtml(link, mailUrl) ); break;
		}
		
		sb.append("						<tr>");
		sb.append("							<td>");
		sb.append("								<p style=\"margin: 0;font-size: 10px;color: #474747;padding-bottom: 36px;\">회원가입을 요청하지 않았는데 본 메일이 수신되었다면<br>다른 사용자가 이메일 주소를 잘못 입력했을 수 있어요. 이 경우 본 메일을 삭제해주세요.</p>");
		sb.append("							</td>");
		sb.append("						</tr>");
		sb.append("					</table>");
		sb.append("				</td>");
		sb.append("			</tr>");
		sb.append("		</table>");
		sb.append("	</div>");
		sb.append("</body>");
		
		return sb.toString();
	}
	
	private static String getCertContHtml(String link, String mailUrl) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("						<tr>");
		sb.append("							<td height=\"33px\"></td>");
		sb.append("						</tr>");
		sb.append("						<tr>");
		sb.append("							<td>");
		sb.append("								<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"408\" style=\"margin: 0 auto;\">");
		sb.append("									<tr>");
		sb.append("										<td style=\"font-size: 20px;font-weight: 500;\">지니벨리 가입을 환영해요!<br><span style=\"color: #248ff0;\">이메일 인증</span>을 진행해주세요</td>");
		sb.append("									</tr>");
		sb.append("									<tr>");
		sb.append("										<td height=\"24px\"></td>");
		sb.append("									</tr>");
		sb.append("									<tr>");
		sb.append("										<td>");
		sb.append("											<p style=\"margin: 0;font-size: 12px;color: #474747;line-height: 18px;\">안녕하세요. 지니벨리를 이용해주셔서 감사합니다.<br>아래 <em style=\"font-style: normal;font-weight: 700;color: #474747;\">‘이메일인증’</em> 버튼을 클릭하여 회원가입을 완료해주세요. </p>");
		sb.append("										</td>");
		sb.append("									</tr>");
		sb.append("									<tr>");
		sb.append("										<td height=\"67px\"></td>");
		sb.append("									</tr>");
		sb.append("									<tr>");
		sb.append("										<td>");
		sb.append("											<a href=\""+link+"\">");
		sb.append("												<button type=\"button\" style=\"width: 408px;height: 48px;background: linear-gradient(101.91deg, #91dd8b -3.09%, #2d9bff 105.63%);border-radius: 4px;font-size: 14px;border: none;color: #fff;cursor: pointer;\">이메일 인증</button>");
		sb.append("											</a>");
		sb.append("										</td>");
		sb.append("									</tr>");
		sb.append("								</table>");
		sb.append("							</td>");
		sb.append("						</tr>");
		sb.append("						<tr>");
		sb.append("							<td height=\"13px\"></td>");
		sb.append("						</tr>");
		sb.append("						<tr>");
		sb.append("							<td>");
		sb.append("								<img src=\""+ mailUrl +"/img/icon-warning.png\">");
		sb.append("								<span style=\"font-size: 10px;color: rgba(0, 0, 0, 0.5);vertical-align: top;line-height: 22px;\">인증 링크는 최대 24시간까지 유효해요</span>");
		sb.append("							</td>");
		sb.append("						</tr>");
		sb.append("						<tr>");
		sb.append("							<td height=\"78px\"></td>");
		sb.append("						</tr>");
		
		return sb.toString();
	}
	
	private static String getPwdContHtml(String pw, String mailUrl) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<tr>");
		sb.append("	<td height=\"12px\"></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("	<td style=\"font-size: 14px;font-weight: 500;color: #7d7d7d;\">메타버스 공간에서 간편하게 비즈니스 하세요</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("	<td height=\"77px\"></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("	<td style=\"font-size: 20px;\">김벨리님의 <span style=\"font-weight: 500;color: #248ff0;font-size: 20px;\">임시 비밀번호</span>를 알려드려요</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("	<td height=\"20px\"></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("	<td>");
		sb.append("		<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"408\" style=\"margin: 0 auto;\">");
		sb.append("			<tr>");
		sb.append("				<td style=\"width: 408px;height: 87px;background: linear-gradient(101.91deg, #91dd8b -3.09%, #2d9bff 105.63%);border-radius: 4px;\">");
		sb.append("					<p style=\"margin:0;color: #fff;font-size: 12px;\">임시 비밀번호</p>");
		sb.append("					<strong style=\"margin-top: 2px;color: #fff;font-size: 24px;\">"+ pw +"</strong>");
		sb.append("				</td>");
		sb.append("			</tr>");
		sb.append("		</table>");
		sb.append("	</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("	<td height=\"14px\"></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("	<td>");
		sb.append("		<p style=\"font-size: 12px;color: #474747;\">안녕하세요. Genie VALLEY를 이용해주셔서 감사합니다.<br>로그인 후 반드시 <em style=\"font-style: normal;font-weight: 700;\">비밀번호를 수정</em>해 주세요!</p>");
		sb.append("	</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("	<td height=\"78px\"></td>");
		sb.append("</tr>");
		
		return sb.toString();
	}
	

}





