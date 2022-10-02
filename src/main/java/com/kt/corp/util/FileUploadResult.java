package com.kt.corp.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadResult {
	
	private MultipartFile multipartFile;
	private File uploadFile;
	private String uploadRelPath;
	private String requestName;
	private String fileSize;
	
	/**
	 * 
	 * @param mf Spring 제공객체
	 * @param uf Upload된 파일
	 * @param uploadRelPath	Upload 상대경로
	 * @param requestName
	 * @param fileSize
	 */
	public FileUploadResult(MultipartFile mf, File uf, String uploadRelPath, String requestName, String fileSize) {
		this.multipartFile=mf;
		this.uploadFile=uf;
		this.uploadRelPath=uploadRelPath;
		this.requestName=requestName;
		this.fileSize=fileSize;
	}

	/**
	 * @return
	 */
	public MultipartFile getMultipartFile() {
		return this.multipartFile;
	}
	/**
	 * @return
	 */
	public File getUploadFile() {
		return this.uploadFile;
	}

	/** 원래의 파일명
	 * @return
	 */
	public String getOriginalFilename() {
		return this.multipartFile.getOriginalFilename();
	}
	
	
	/**
	 * 
	 * @Method Name        : getNewFilename
	 * @Method description : 중복되는 이름이 존재하여 바뀐 파일명, 중복이름이 존재하지 않았다면 원래의 파일명
	 * @return
	 */
	public String getNewFilename() {
		return uploadFile.getName();
	}
	/**
	 * @return
	 */
	public String getContentType() {
		return this.multipartFile.getContentType();
	}
	/**
	 * @return
	 */
	public long getSize() {
		return this.multipartFile.getSize();
	}

	/** 상대경로
	 * @return
	 */
	public String getUploadRelPath() {
		return uploadRelPath;
	}
	
	
	/** 업로드 오브젝트명
	 * @return
	 */
	public String getRequestName() {
		return requestName;
	}
	
	/** 업로드 오브젝트명
	 * @return
	 */
	public String getFileSize() {
		return fileSize;
	}
	
	/** 상대경로 수정
	 * @param uploadRelPath
	 */
	public void setUploadRelPath(String uploadRelPath) {
		this.uploadRelPath = uploadRelPath;
	}
	
	
	/** 업로드 오브젝트명
	 * @param uploadRelPath
	 */
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	
	
	/** 업로드 오브젝트명
	 * @param uploadRelPath
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	
	/** 다음의 정보 제공: 원래파일명, 저장된파일명, 사이즈, 컨텐트타입, 상대경로
	 */
	public String toString() {
		String str="oriFileName: "+multipartFile.getOriginalFilename()
				+", saveFileName: "+uploadFile.getName()
				+", size: "+multipartFile.getSize()
				+", contentType: "+multipartFile.getContentType()
				+", uploadRelPath: "+uploadRelPath
				+", requestName: "+multipartFile.getName()
				+", fileSize: "+multipartFile.getSize()+""
		;

		return str;
	}

}
