package com.kt.corp.file.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.kt.corp.Constant;
import com.kt.corp.comm.ApiException;
import com.kt.corp.comm.BaseComm;
import com.kt.corp.file.dto.FileDTO;
import com.kt.corp.metaweb.dto.MetaWebDTO;
import com.kt.corp.metaweb.mapper.MetawebMapper;
import com.kt.corp.user.dto.GuestDTO;
import com.kt.corp.user.mapper.UserMapper;

@Service
public class FileService extends BaseComm {
	
	@Value("${resources.upload-path}")
	String uploadPath;
	
	@Value("${file.metaweb.main-size}")
	int metawebMainSize;
	
	@Value("${file.metaweb.intro-size}")
	int introSize;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired 
	MetawebMapper metawebMapper;
	
	/**
	 * 사용자 아바타(이미지 파일) 업로드
	 * 
	 * @param userId
	 * @param file
	 */
	public void uploadAvatar(String userId, MultipartFile file) {
		
		this.isFileChek(Constant.FILE_TYPE_AVATAR, file, userId);
		
		String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
		String beforePath = uploadPath + "avatar/" + userId + "." + extension;
		String afterPath = uploadPath + "avatar/" + userId;
		
		logger.info("Upload user's avatar before convert image ==> " + beforePath);
		logger.info("Upload user's avatar after convert image ==> " + afterPath);
		
		try {
			File beforeFile = new File(beforePath);
			File afterFile = new File(afterPath);
		
			file.transferTo(beforeFile);
			
			// 이미지 리사이즈(400*400) 및 jpg 타입으로 변환
			BufferedImage beforeImg = ImageIO.read(beforeFile);
			BufferedImage afterImg = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
			
			afterImg.createGraphics().drawImage(beforeImg, 0, 0, 400, 400, Color.white, null);
			
			ImageIO.write(afterImg, "jpg", afterFile);
			
			// 원본 파일 삭제
			beforeFile.delete();
			
		} catch (IOException e) {
			logger.error("Fail upload user's avatar {}", e.getMessage());
			throw new ApiException("E0014", e.getMessage());
		}
	}

	/**
	 * 게스트 아바타(이미지 파일) 업로드
	 * 
	 * @param guestNickNm
	 * @param file
	 */
	public Map<String, String> uploadGuest(String guestId, MultipartFile file) {
		// TODO Auto-generated method stub
		Map<String, String> resultMap = null;
		
		this.isFileChek(Constant.FILE_TYPE_GUEST, file, null);
		
		if(!"".equals(guestId)) {
			GuestDTO searchDto = new GuestDTO();
			searchDto.setGuestId(guestId);
			
			GuestDTO dto = this.userMapper.selectGuest(searchDto);
			
			if(dto != null) {
				String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
				String beforePath = uploadPath + "guest/" + guestId + "." + extension;
				String afterPath = uploadPath + "guest/" + guestId;
				
				resultMap = this.createImg(beforePath, afterPath, file);
				resultMap.put("count", "0");
				
				if(resultMap != null && !"".equals(resultMap.get("afterPath"))) {
					dto = null;
					dto = new GuestDTO();
					dto.setGuestId(guestId);
					dto.setImgLog("guest/" + guestId);
					
					 int cnt = this.userMapper.updateGuest(dto);
					 resultMap.put("count", cnt+"");
				}
			}else
				throw new ApiException(Constant.ERR_1103, "");
		}else
			throw new ApiException("E0011", "게스트 닉네임");
		
		return resultMap;
	}
	
	/**
	 * 첨부파일 업로드
	 * 
	 * @param file
	 * @return fileDto
	 */
	public FileDTO uploadAttFile(MultipartFile file) {
		FileDTO fileDto = new FileDTO();
		
		String fileName = file.getOriginalFilename();
		String uploadFileName = UUID.randomUUID().toString();
		String uploadFilePath = "file/" + uploadFileName;
		
		logger.info("Upload attach file ==> " + uploadFilePath);
		
		try {
			File uploadFile = new File(uploadPath + uploadFilePath);
			
			file.transferTo(uploadFile);
			
		} catch(Exception e) {
			logger.error("Fail upload attach file {}", e.getMessage());
			throw new ApiException("E0014", e.getMessage());
		}
		
		fileDto.setFileName(fileName);
		fileDto.setFilePath(uploadFilePath);
		
		return fileDto;
	}
	
	/**
	 * 첨부파일 다운로드
	 * 
	 * @param fileDto
	 * @return fileDto
	 */
	public FileDTO downloadAttFile(FileDTO fileDto) {
		try {
			fileDto.setResource(new UrlResource("file:" + uploadPath + fileDto.getFilePath()));

			String contentDisposition = "attachment; filename=\"" + UriUtils.encode(fileDto.getFileName(), StandardCharsets.UTF_8) + "\"";
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", contentDisposition);
			fileDto.setHeaders(headers);
			
		} catch (MalformedURLException e) {
			logger.error("Fail download attach file {}", e.getMessage());
			throw new ApiException("E0025", e.getMessage());
		}
		
		return fileDto;
	}	
	
	/**
	 * 첨부파일 삭제
	 * 
	 * @param fileDto
	 */
	public void deleteAttFile(FileDTO fileDto) {
		logger.info("Delete attach file ==> " + fileDto.getFilePath());
		
		File file = new File(uploadPath + fileDto.getFilePath());
		
		if(file.exists()) {
			file.delete();
		}
	}
	
	/**
	 * 메타웹 메인(이미지 파일) 업로드
	 * 
	 * @param metaWebId
	 * @param file
	 */
	public Map<String, String> uploadMetawebMainImg(String comId, MultipartFile file) {
		// TODO Auto-generated method stub
		Map<String, String> resultMap = null;
		
		this.isFileChek(Constant.FILE_TYPE_METAWEB_MAIN, file, null);
		
		if( comId != null && !"".equals(comId) ) {
			MetaWebDTO dto = this.metawebMapper.selectMetaWeb(comId);
			
			if(dto != null) {
				int metaWebId = dto.getMetaWebId();
				String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
				String beforePath = uploadPath + "metaweb/" + comId + "." + extension;
				String afterPath = uploadPath + "metaweb/" + comId;
				
				resultMap = this.createImg(beforePath, afterPath, file);
				resultMap.put("count", "0");
				
				if(resultMap != null && !"".equals(resultMap.get("afterPath"))) {
					dto = null;
					dto = new MetaWebDTO();
					dto.setMetaWebId(metaWebId);
					dto.setBsnsImg("metaweb/" + comId);
					
					 int cnt = this.metawebMapper.updateMetaWeb(dto);
					 resultMap.put("count", cnt+"");
				}
			}else
				throw new ApiException(Constant.ERR_1103, "");
		}else
			throw new ApiException("E0011", "메타웹 데이터");
		
		return resultMap;
	}
	
	/**
	 * 메타웹 인트로(이미지 파일) 업로드
	 * 
	 * @param metaWebId
	 * @param file
	 */
	public Map<String, String> uploadMetawebIntroImg(int metaWebId, List<MultipartFile> files) {
		// TODO Auto-generated method stub
		Map<String, String> resultMap = null;
		
		if(files != null && files.size() > 0) {														// 업로드 이미지 유무 체크 
			int result = 0;
			for(MultipartFile file : files) {
				this.isFileChek(Constant.FILE_TYPE_METAWEB_INTRO, file, null);
			}
			
			MetaWebDTO dto = this.metawebMapper.selectMetaWebId(metaWebId);
			
			if(dto != null) {																			// 업로드 이미지 관련 회사 유무 체크
				for(MultipartFile file : files) {
					int cnt = this.metawebMapper.selectMetaWebIntroImgCnt(metaWebId);
					String comId = dto.getComId();
					String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
					String beforePath = uploadPath + "metaweb/INTRO_" + comId + "_" + cnt + "." + extension;
					String afterPath = uploadPath + "metaweb/INTRO_" + comId + "_" + cnt;
					
					resultMap = this.createImg(beforePath, afterPath, file);
					resultMap.put("count", "0");
					
					if(resultMap != null && !"".equals(resultMap.get("afterPath"))) {
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("metaWebId", metaWebId);
						param.put("metaIntroImg", "metaweb/INTRO_" + comId  + "_" + cnt);
						
						result += this.metawebMapper.insertMetaWebIntroImg(param);
					}
				}
			}else
				throw new ApiException(Constant.ERR_1103, "");
			
			resultMap.put("count", result+"");
		}
		
		return resultMap;
	}
	
	/**
	 * 메타웹 인트로(이미지 파일) 삭제
	 * 
	 * @param metaWebId
	 * @param file
	 */
	public int deleteMetawebIntroImg(MetaWebDTO dto) {
		int result = 0;
		List<Map<String,Object>> delIds = dto.getIntroImgs();
		
		if(delIds != null && delIds.size() > 0){
			String comId = dto.getComId();
			MetaWebDTO metawebDto = this.metawebMapper.selectMetaWeb(comId);
			
			if(metawebDto != null) {
				List<Integer> dels = new ArrayList<Integer>(); 
				for(Map map : delIds) {
					String metaIntroImg = map.get("metaIntroImg") + "";
					
					if( !"".equals(metaIntroImg) ) {
						String imgPath = uploadPath + metaIntroImg;
						File imgFile = new File(imgPath);
						
						if( imgFile.delete() )
							dels.add((int)map.get("seq")); 
					}
				}
				
				if(dels != null && dels.size() > 0)
					result = this.metawebMapper.deleteMetaIntroImg(dels);
			}
		}
		
		return result;
	}
	
	private boolean isFileChek(String type, MultipartFile file, String value1) {
		
		switch (type.toUpperCase()) {
			case Constant.FILE_TYPE_METAWEB_MAIN:
			case Constant.FILE_TYPE_METAWEB_INTRO:
				if(file.isEmpty()) throw new ApiException(Constant.ERR_0011, "이미지 파일");
				
				// Content Type 제한 : jpeg, png 만 허용
				if(!file.getContentType().toLowerCase().endsWith("jpeg") 
						&& !file.getContentType().toLowerCase().endsWith("png")) throw new ApiException(Constant.ERR_0013, "JPG, PNG");
				
				// 용량 제한 
				if(file.getSize() > metawebMainSize) throw new ApiException(Constant.ERR_0015, "File size");
				break;
			case Constant.FILE_TYPE_GUEST:
				if(file.isEmpty()) throw new ApiException(Constant.ERR_0011, "이미지 파일");
				
				// Content Type 제한 : jpeg, png 만 허용
				if(!file.getContentType().toLowerCase().endsWith("jpeg") 
						&& !file.getContentType().toLowerCase().endsWith("png")) throw new ApiException(Constant.ERR_0013, "JPG, PNG");
				
				// 용량 제한 : 최대 2MB
				if(file.getSize() > 2097152) throw new ApiException(Constant.ERR_0015, "2MB");
				break;
				
			case Constant.FILE_TYPE_AVATAR:
				if(file.isEmpty()) {
					throw new ApiException("E0011", "이미지 파일");
				}
				
				if(value1 == null) {
					throw new ApiException("E0011", "사용자ID");
				}

				if(!userMapper.existUser(value1)) {
					throw new ApiException("E0012", "사용자");
				}
				
				// Content Type 제한 : jpeg, png 만 허용
				if(!file.getContentType().toLowerCase().endsWith("jpeg") && !file.getContentType().toLowerCase().endsWith("png")) {
					throw new ApiException("E0013", "JPG, PNG");
				}
				
				// 용량 제한 : 최대 2MB
				if(file.getSize() > 2097152) {
					throw new ApiException("E0015", "2MB");
				}
				break;
				
			default:
				break;
		}
		
		return true;
	}
	
	private Map<String, String> createImg(String beforePath, String afterPath, MultipartFile file){
		try {
			String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
			File beforeFile = new File(beforePath);
			File afterFile = new File(afterPath);
		
			file.transferTo(beforeFile);
			
			BufferedImage beforeImg = ImageIO.read(beforeFile);
			BufferedImage afterImg = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
			
			afterImg.createGraphics().drawImage(beforeImg, 0, 0, 400, 400, Color.white, null);
			
			ImageIO.write(afterImg, "jpg", afterFile);
			
//			File newAfterFile = new File(afterPath.substring(0, afterPath.length()-4));
//			
//			boolean result = afterFile.renameTo(newAfterFile);
			
			beforeFile.delete();
			
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("beforePath", beforePath);
			resultMap.put("afterPath", afterPath);
			
			return resultMap;
		} catch (IOException e) {
			logger.error("Fail upload user's avatar {}", e.getMessage());
			throw new ApiException("E0014", e.getMessage());
		}
	}
	
}
