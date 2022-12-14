package com.kt.corp.user.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kt.corp.Constant;
import com.kt.corp.bizcard.dto.BizcardDTO;
import com.kt.corp.bizcard.mapper.BizcardMapper;
import com.kt.corp.comm.ApiException;
import com.kt.corp.comm.BaseComm;
import com.kt.corp.jwt.RefreshToken;
import com.kt.corp.jwt.TokenDTO;
import com.kt.corp.jwt.TokenProvider;
import com.kt.corp.member.dto.UpdateMemberDTO;
import com.kt.corp.member.mapper.MemberMapper;
import com.kt.corp.setting.service.SettingService;
import com.kt.corp.template.MailTemplate;
import com.kt.corp.user.dto.GuestDTO;
import com.kt.corp.user.dto.UserDTO;
import com.kt.corp.user.mapper.UserMapper;
import com.kt.corp.util.CommonUtil;
import com.kt.corp.util.DeEncrypter;


@Service
public class UserService extends BaseComm {
	@Value("${admin.smtp.host}")
	String adminSmtpHost;
	
	@Value("${admin.smtp.port}")
	Integer adminSmtpPort;
	
	@Value("${admin.mail.id}")
	String adminMailId;
	
	@Value("${admin.mail.pwd}")
	String adminMailPwd;
	
	@Value("${admin.mail.url}")
	String adminMailUrl;
	
	@Value("${admin.mail.title}")
	String adminMailTitle;
	
	@Value("${admin.mail.msg}")
	String adminMailMsg;
	
	@Value("${resources.upload-path}")
	String uploadPath;
	
	@Value("${capcha.server-key}")
	String capchaServerKey;
	
	@Autowired
	SettingService settingService;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	BizcardMapper bizcardMapper;
	
	@Autowired
	MemberMapper memberMapper;
	
	
	@Autowired private  AuthenticationManagerBuilder authenticationManagerBuilder;
	
	@Autowired private  TokenProvider tokenProvider;
	
	
	/**
	 * ????????? ????????? ??????
	 * 
	 * @param mail
	 */
	public String chkUserMail(String mail) {
		if(!Pattern.matches("^[a-zA-Z0-9_!#$%&'\\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$", mail)) {
			throw new ApiException("E0016", "????????? ??????");
		}
		
		String encMail = "";
		try {
			encMail = DeEncrypter.encryptAES(mail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ApiException(Constant.ERR_0001, "encryAES Error");
		}
		
		if(userMapper.existUserMail(encMail)) {
			throw new ApiException("E0017", "?????????");
		}
		
		return "success";
	}
	
	/**
	 * ?????? ????????? ????????? ?????? (?????? ????????? ?????? ?????????)
	 * 
	 * @param mail
	 * @param senderId
	 * @return userDto
	 */
	public UserDTO getUserByMail(String mail, String senderId) {
		if(!Pattern.matches("^[a-zA-Z0-9_!#$%&'\\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$", mail)) {
			throw new ApiException("E0016", "????????? ??????");
		}
		
		String encMail = "";
		try {
			encMail = DeEncrypter.encryptAES(mail);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiException(Constant.ERR_0001, "encryptAES Error");
		}
		
		UserDTO userDto = userMapper.getUserByMail(encMail);
		
		if(userDto != null) {
			// ?????? ????????? ?????? ??????
			BizcardDTO bizcarDto = new BizcardDTO();
			bizcarDto.setReceiverId(userDto.getUserId());
			bizcarDto.setSenderId(senderId);
			boolean hasBizcard = bizcardMapper.existBizcard(bizcarDto);
			
			// ?????? ???????????? ??????
			UpdateMemberDTO updateMemberDto = new UpdateMemberDTO();
			updateMemberDto.setUserId(senderId);
			updateMemberDto.setMemId(userDto.getUserId());
			boolean isComMember = memberMapper.existComMember(updateMemberDto);
			
			if(!hasBizcard && !isComMember) {
				userDto.setAvailSendBizcard(true);
			} else {
				userDto.setAvailSendBizcard(false);
			}
		}
		
		return userDto;
	}
	
	/**
	 * ?????? ????????? ????????? ??????
	 * 
	 * @param mail
	 * @return userMapper.getUserByMail(mail)
	 */
	public UserDTO getUserByMail(String mail) {
		if(!Pattern.matches("^[a-zA-Z0-9_!#$%&'\\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$", mail)) {
			throw new ApiException("E0016", "????????? ??????");
		}
		
		return userMapper.getUserByMail(mail);
	}
	
	/**
	 * ????????? ?????? (?????? ??????)
	 * 
	 * @param userDto
	 * @return result
	 */
	public Map<String, String> insertUser(UserDTO userDto) {
		Map<String, String> result = null;
		
		result = new HashMap<String, String>();
		// Validation Check
		chkUserMail(userDto.getMail());
		chkUserPw(userDto);
		
		// SALT ??????
		userDto.setSalt(createSalt());
		
		String originMail = userDto.getMail();
		String encMail = null;
		String encPw = DeEncrypter.encSHA256(userDto.getPw() + userDto.getSalt());
		
		try {
			encMail = DeEncrypter.encryptAES(userDto.getMail());
		} catch (Exception e) {
			// TODO: handle exception
			throw new ApiException(Constant.ERR_0001, "encryptAES Error");
		}
		
		userDto.setMail(encMail);
		userDto.setPw(encPw);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userMail", userDto.getMail());
		
		UserDTO duDto = userMapper.selectUser(param);
		
		if(duDto != null) throw new ApiException("E0017", "?????????");
		
		// ?????? ????????? ??????
		userMapper.insertUser(userDto);
		
		// ?????? ????????? ??????
		settingService.addNtcnSetup(userDto.getUserId());
		
		// ???????????? ??????
		this.sendMail(originMail, Constant.MAIL_SEND_CERT, null);
		
		result.put("userId", userDto.getUserId());
		
		return result;
	}
	
	/**
	 * ?????? ??????
	 * 
	 * @param userMail
	 * @return UserDTO
	 */
	public UserDTO sendMail(String userMail, String mailType, String newPw) {
		InternetAddress[] addArray = null;
		userMail = userMail.replace("%40", "@");
		
		UserDTO userDto = null;
		
		try {
			if(!"".equals(userMail)) {
				addArray = new InternetAddress[1];
				addArray[0] = new InternetAddress(userMail);
				
				Map<String, Object> param = new HashMap<String, Object>(); 
				param.put("userMail", userMail);		
				userDto = this.getUser(param);																			// ????????? ???????????? ????????? ??????
				
				if(userDto != null) {
					Map<String, Object> mailMap = new HashMap<String, Object>();
					mailMap.put("host", adminSmtpHost); 
					mailMap.put("port", adminSmtpPort); 
					mailMap.put("adminId", adminMailId); 
					mailMap.put("adminPwd", adminMailPwd); 
					
					String title = "";
					String msg = "";
					switch (mailType) {
						case Constant.MAIL_SEND_CERT:
							title = "Genie Valley ?????? ??????";
							String link = adminMailUrl + "?code=" + (new String(Base64.getEncoder().encode(userMail.getBytes()))); 
							msg = MailTemplate.getCertMailHtml(link, adminMailUrl);
							break;
						case Constant.MAIL_SEND_INVITE:
							title = "Genie Valley ?????? ??????";
							msg = "invite";
							break;
						case Constant.MAIL_SEND_PW_TEMPORARY:
							title = "Genie Valley ?????? ???????????? ??????";
							msg = userDto.getNm() + "?????? ?????? ??????????????? " + newPw + " ?????????.";
							msg = MailTemplate.getPwdMailHtml(newPw, adminMailUrl);
							break;
						default:
							break;
					}
					mailMap.put("title", title);
					mailMap.put("msg", msg); 
					
					//CommonUtil.sendMail(mailMap, addArray);																// ?????? ??????
					CommonUtil.sendMailNew(mailMap, userMail);
//					CommonUtil.sendGmail(mailMap, userMail);
					
					param.keySet().remove("userMail");
					param.put("userId", userDto.getUserId());
					param.put("mailType", mailType);
					
					this.userMapper.insertMailLog(param);																		// ?????? ?????? ??????
					
					return userDto;
				}
			}
			
			return null;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			//throw new ApiException(Constant.ERR_0001, e.getMessage());
		}
		
		return userDto;
	}
	
	/**
	 * ????????? ??????
	 * 
	 * @param Map
	 * @return UserDTO
	 */
	public UserDTO getUser(Map<String, Object> params) {
		try {
			if(params.get("userMail") != null && !"".equals(params.get("userMail"))) {
				String encMail= DeEncrypter.encryptAES( params.get("userMail").toString() );
				params.put("userMail", encMail);
			}
			
			return this.userMapper.selectUser(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ApiException(Constant.ERR_0001, e.getMessage());
		}
	}
	
	/**
	 * ?????? ?????? ??????
	 * 
	 * @param userMail
	 * @return UserDTO
	 */
	public UserDTO certificationMail(String userMail) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userMail", new String(Base64.getDecoder().decode(userMail.getBytes())) );
		params.put("certification", "h24");
		
		UserDTO userDTO = this.getUser(params);					// ?????? ??????.
		
		if(userDTO != null) {
			params.put("active", 1);
			this.userMapper.updateUserInfo(params);				// ?????? ?????????
		}
		
		return userDTO;
	}
	
	/**
	 * ???????????? ??????
	 * 
	 * @param userMail
	 * @return UserDTO
	 */
	public UserDTO findUserPwd(String userMail) {
		userMail = userMail.replace("%40", "@");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userMail", userMail);
		
		UserDTO userDto = this.getUser(param);																		// ????????? ???????????? ????????? ??????
		
		if(userDto != null) {
			String newPwd = CommonUtil.getRamdomPassword(8);
			String encPw = DeEncrypter.encSHA256(newPwd + userDto.getSalt());
			param.put("newPwd", encPw);
			param.put("wrongPw", "0");
			
			this.userMapper.updateUserInfo(param);																// ????????? ???????????? ??? ???????????? ?????????
			
			this.sendMail(userMail, Constant.MAIL_SEND_PW_TEMPORARY, newPwd);		// ?????? ??????.
		}
		
		return userDto;
	}
	
	/**
	 * ????????? ?????????
	 * 
	 * @param userMail
	 * @return UserDTO
	 */
	@Transactional
	public Map<String, Object> userLogin(UserDTO userDto) {
		ApiException apiException = new ApiException("");
		
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getMail(), userDto.getPw());
			Authentication authentication = this.authenticationManagerBuilder.getObject().authenticate(authenticationToken);
			
			if(authentication.getPrincipal() != null) {
				Object princi = authentication.getPrincipal();
				if (princi instanceof ApiException) {
					apiException = (ApiException)princi;
					//return CommonUtil.setFailResponse(apiException.getMsgCode(), apiException.getMsgDesc());
					throw apiException;
				}
			}
			
			TokenDTO tokenDto = this.tokenProvider.generateTokenDto(authentication);
			RefreshToken refreshToken = RefreshToken.builder()
	                .key(authentication.getName())
	                .value(tokenDto.getRefreshToken())
	                .build();
		
			String encMail = DeEncrypter.encryptAES(userDto.getMail());
			
			param.put("userMail", encMail);
			userDto = this.userMapper.selectUser(param);																// ????????? ?????? ??????
			if(userDto == null)	{
				apiException = new ApiException(Constant.ERR_1103, "?????? ????????? ???????????????."); 
				throw  apiException;
			}
			
			param.put("active", "1");
			userDto = this.userMapper.selectUser(param);																// ????????? ????????? ??????
			if(userDto == null) {
				apiException = new ApiException(Constant.ERR_1104, "?????? ???????????? ????????? ???????????????.");
				throw  apiException;
			}
			
			param.put("userId", userDto.getUserId());
			param.put("token", tokenDto.getAccessToken());
			param.put("refreshToken", refreshToken.getValue());
			
			Map<String, Object> tokenMap = this.userMapper.selectUserToken(param);				// ????????? ?????? ?????? ??????
			
			if(tokenMap != null && !"".equals(tokenMap.get("token"))) this.userMapper.updateUserToken(param);
			else this.userMapper.insertUserToken(param);
			
			HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			String ip = req.getHeader("X-FORWARDED-FOR");
			ip = (ip == null) ? req.getRemoteAddr() : ip;																		// ????????? ?????? IP
			userDto.setIp(ip);
			
			String userAgent = req.getHeader("User-Agent").toUpperCase();
		    if(userAgent.indexOf("MOBILE") > -1) {																				// ????????? ?????? ????????? ??????
		        if(userAgent.indexOf("PHONE") == -1)	userDto.setEntryType(Constant.USER_ENTRY_TYPE2);
				else	userDto.setEntryType(Constant.USER_ENTRY_TYPE3);
		    } else	userDto.setEntryType(Constant.USER_ENTRY_TYPE1);
		    	
			this.userMapper.insertUserLoginLog(userDto);																// ????????? ??????
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("userId", userDto.getUserId());
			resultMap.put("accessToken", tokenDto.getAccessToken());
			resultMap.put("refreshToken", tokenDto.getRefreshToken());
			resultMap.put("accessTokenExpiresIn", tokenDto.getAccessTokenExpiresIn());
			
			return resultMap;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			throw apiException;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ApiException(Constant.ERR_0001, "encryptAES Error");
		}
	}

	/**
	 * ?????? ?????????
	 * 
	 * @param TokenDTO
	 * @return TokenDTO
	 */
	@Transactional
    public TokenDTO reissue(TokenDTO tokenRequestDto) {
        if (!tokenProvider.reflashValidateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token ??? ???????????? ????????????.");
        }

        // 2. Access Token ?????? Member ID ????????????
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. ??????????????? Member ID ??? ???????????? Refresh Token ??? ?????????
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", authentication.getName());
        
        // 4. Refresh Token ??????????????? ??????
        Map<String, Object> tokenMap = this.userMapper.selectUserToken(param);
        if(tokenMap != null) {
        	if(!tokenRequestDto.getRefreshToken().equals(tokenMap.get("refresh_token").toString()))
        		throw new ApiException(Constant.ERR_1005, "????????? ?????? ????????? ???????????? ????????????.");
        }else
        	throw new ApiException(Constant.ERR_1101, "???????????? ??? ??????????????????.");

        // 5. ????????? ?????? ??????
        TokenDTO tokenDto = tokenProvider.generateTokenDto(authentication);

        param.put("token", tokenDto.getAccessToken());
        param.put("refreshToken", tokenDto.getRefreshToken());
        
        // 6. ????????? ?????? ????????????
        this.userMapper.updateUserToken(param);

        // ?????? ??????
        return tokenDto;
    }
	
	/**
	 * ????????? ????????????
	 * 
	 * @param userId
	 * @return int
	 */
	public int deleteUserToken(String userId) {
		return this.userMapper.deleteUserToken(userId);
	}
	
	/**
	 * ???????????? ??????
	 * 
	 * @param userId
	 * @return UserDTO
	 */
	public UserDTO changePwd(HashMap<String, Object> map) {
		String userId = map.get("userId").toString();
		String pw = map.get("pw").toString();
		String newPw = map.get("newPw").toString();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		
		UserDTO userDto = this.userMapper.selectUser(param);
		
		if(userDto != null && !"".equals(userDto.getPw())) {
			String encPwd = DeEncrypter.encSHA256(pw + userDto.getSalt());
			
			if(encPwd.equals(userDto.getPw())) {
				String newPwd = DeEncrypter.encSHA256(newPw + userDto.getSalt());
				param.put("newPwd", newPwd);
				
				this.userMapper.updateUserInfo(param);
			}else
				throw new ApiException(HttpStatus.OK, Constant.ERR_1102, "????????? ??????????????? ????????????.");
		}else
			throw new ApiException(HttpStatus.OK, Constant.ERR_1103, "????????? ????????? ????????????.");
		
		return userDto;
	}
	
	/**
	 * ????????? ?????? ??????
	 * 
	 * @param GuestDTO
	 * @return GuestDTO
	 */
	public GuestDTO insertGuest(GuestDTO guestDTO) {
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		
		ip = (ip == null) ? req.getRemoteAddr() : ip;
		guestDTO.setIpAdd(ip);
		
		GuestDTO searchGuest =  this.userMapper.selectGuest(guestDTO);
		
		if(searchGuest != null && !searchGuest.getGuestNickNm().equals(""))
			throw new ApiException(Constant.ERR_0017, "?????????");
		
		this.userMapper.insertGuest(guestDTO);
		
		return guestDTO;
	}
	
	/**
	 * ????????? ?????? ??????
	 * 
	 * @param guestNickNm
	 * @return int
	 */
	public int deleteGuest(String guestId) {
		GuestDTO targetDto = new GuestDTO();
		targetDto.setGuestId(guestId);
		
		GuestDTO searchGuest =  this.userMapper.selectGuest(targetDto);
		
		if(searchGuest != null && !searchGuest.getGuestNickNm().equals("")) {
			if(!"".equals(searchGuest.getImgLog())) {
				String imgPath = uploadPath + searchGuest.getImgLog();
				File file = new File(imgPath);
				
				if( file.exists() )
					file.delete();
			}
			
			return this.userMapper.deleteGuest(guestId);
		}else
			throw new ApiException(Constant.ERR_0012, "?????????");
	}
	
	/**
	 * ????????? ?????? ??????
	 * 
	 * @param guestId
	 */
	public UserDTO getUserStat(String userId) {
		
		UserDTO userDto =  this.userMapper.selectUserStat(userId);
		
		if(userDto != null && !"".equals(userDto.getUserStateCode()))
			return this.userMapper.selectUserStat(userId);
		else {
			userDto = new UserDTO();
			userDto.setUserId(userId);
			userDto.setUserStateCode(Constant.USER_STATE_CD1);
			this.userMapper.insertUserStat(userDto);
			
			return userDto;
		}
	}
	
	/**
	 * ????????? ?????? ??????
	 * 
	 * @param guestId
	 */
	public int modifyUserStat(UserDTO dto) {
		UserDTO userDto =  this.userMapper.selectUserStat(dto.getUserId());
		
		if(userDto != null && !"".equals(userDto.getUserStateCode()))
			return this.userMapper.updateUserStat(dto);
		else {
			userDto = new UserDTO();
			userDto.setUserId(dto.getUserId());
			userDto.setUserStateCode(dto.getUserState());
			return this.userMapper.insertUserStat(userDto);
		}
	}
	
	/**
	 * ????????? ??????
	 * 
	 * @param token
	 */
	public JSONObject captcha(String token) {
		String []op = {"curl", "-d", "secret="+this.capchaServerKey+"&response="+token, Constant.SITE_VERIFY_URL};
		ProcessBuilder pb = new ProcessBuilder(op);
		Process pro;
		
		try {
			pro = pb.start();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String line;
			String text = "";
			
			while((line = reader.readLine()) != null) {
				text += line;
			}
			
			int exitCode = pro.waitFor();
			
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject)parser.parse(text);
			
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ApiException(Constant.ERR_0001, e.getMessage());
		}
	}
	
	/**
	 * ???????????? ????????? ??????
	 * 
	 * @param pw
	 */
	private void chkUserPw(UserDTO userDto) {
		String mail = userDto.getMail();
		String pw = userDto.getPw();
		
		if(!Pattern.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&*()\\-_+|=])[A-Za-z\\d~!@#$%^&*()\\-_+|=]{8,20}$", pw)) {
			throw new ApiException(Constant.ERR_0018, "??????/??????/??????????????? ?????? ???????????? 8??? ?????? 20??? ??????");
		}
		
		if(Pattern.matches("(\\w)\\1\\1\\1", pw)) {
			throw new ApiException(Constant.ERR_0019, "????????? ????????? 4??? ?????? ?????????");
		}
		
		if(pw.contains(" ")) {
			throw new ApiException(Constant.ERR_0020, "????????? ?????????");
		}
		
		if(pw.contains(mail.split("@")[0])) {
			throw new ApiException(Constant.ERR_0021, "????????? ????????? ?????????");
		}
	}
	
	/**
	 * SALT ??????
	 * 
	 * @return String(Base64.getEncoder().encode(bytes))
	 */
	private String createSalt() {
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			byte[] bytes = new byte[16];
			random.nextBytes(bytes);
			return new String(Base64.getEncoder().encode(bytes));
			
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
			throw new ApiException(Constant.ERR_0001, e.getMessage());
		}
	}
	
}










