package com.kt.corp.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadResults {
	private int size;
	@SuppressWarnings("rawtypes")
	private List multipartFiles=new ArrayList();
	private List<File> uploadFiles=new ArrayList<File>();
	private List<String> originalFilenames=new ArrayList<String>();
	private List<String> newFilenames=new ArrayList<String>();
	private List<String> requestName =new ArrayList<String>();
	private List<String> fileSize =new ArrayList<String>();

	private String uploadBase, uploadRelPath;
	private String uploadPath;	// = uploadBase+uploadRelPath;

	/** Inner Iterator class, java.util.Iterator 인터페이스 구현체
	 * @author scaler
	 *
	 */
	@SuppressWarnings("rawtypes")
	private class FileUploadResultIterator implements Iterator {
		private int pos;

		public boolean hasNext() {
			if (size<=0) return false;
			if (size>pos) {
				return true;
			} else {
				pos=0;
				return false;
			}
		}
		/**
		 * @return FileUploadResult
		 */
		public Object next() {
			FileUploadResult fur=new FileUploadResult((MultipartFile) multipartFiles.get(pos), (File) uploadFiles.get(pos), uploadRelPath, (String)requestName.get(pos), (String)fileSize.get(pos));
			pos++;
			return fur;
		}
		/**
		 * not supported
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	/** 이터레이터 프록시
	 * @deprecated
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Iterator iterator() {
		return new FileUploadResultIterator();
	}


	/** MultipartFile 추가
	 * @param multipartFile
	 */
	@SuppressWarnings("unchecked")
	public void addMultipartFile(MultipartFile multipartFile) {
		multipartFiles.add(multipartFile);
	}
	/** 파일추가
	 * @param file
	 */
	public void addUploadFile(File file) {
		size++;
		uploadFiles.add(file);
	}
	/** 원래파일명 추가
	 * @param filename
	 */
	public void addOriginalFilename(String filename) {
		originalFilenames.add(filename);
	}
	
	/** 
	 * 신규 파ㅇ파일명 추가
	 * @param filename
	 */
	public void addNewFilename(String filename) {
		newFilenames.add(filename);
	}

	/**
	 * @return List of MultipartFiles
	 */
	@SuppressWarnings("rawtypes")
	public List getMultipartFiles() {
		return multipartFiles;
	}
	/**
	 * @return
	 */
	public List<File> getUploadFiles() {
		return uploadFiles;
	}
	/**
	 * @return 원래의 파일명 리스트
	 */
	public List<String> getOriginalFilenames() {
		return originalFilenames;
	}
	public int size() {
		return size;
	}


	/**
	 * @return 업로드 경로
	 */
	public String getUploadPath() {
		return uploadPath;
	}


	/**
	 * @param uploadPath 업로드 경로
	 */
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}


	/**
	 * @return 업로드 최상위 경로
	 */
	public String getUploadBase() {
		return uploadBase;
	}


	/**
	 * @param uploadBase 업로드 최상위 경로
	 */
	public void setUploadBase(String uploadBase) {
		this.uploadBase = uploadBase;
	}


	/**
	 * @return 업로드 상대경로
	 */
	public String getUploadRelPath() {
		return uploadRelPath;
	}

	
	/** 업로드 오브젝트명
	 * @param uploadRelPath
	 */
	public void addRequestName(String requestName) {
		this.requestName.add(requestName);
	}
	
	
	public List<String> getRequestName() {
		return requestName;
	}
	
	
	/** 업로드 오브젝트명
	 * @param uploadRelPath
	 */
	public void addFileSize(String fileSize) {
		this.fileSize.add(fileSize);
	}
	
	
	public List<String> getFileSize() {
		return fileSize;
	}
	
	
	/**
	 * @param uploadRelPath 업로드 상대경로
	 */
	public void setUploadRelPath(String uploadRelPath) {
		this.uploadRelPath = uploadRelPath;
	}

	/** 업로드 결과정보
	 * @return
	 */
	public List<FileUploadResult> getResultList() {
		if (uploadFiles==null) return null;

		List<FileUploadResult> list=new ArrayList<FileUploadResult>();
		int len=uploadFiles.size();
		for (int i=0; i<len; i++) {
			list.add(new FileUploadResult((MultipartFile) multipartFiles.get(i), (File) uploadFiles.get(i), uploadRelPath, (String)requestName.get(i), (String)fileSize.get(i)));
		}
		return list;
	}


	public List<String> getNewFilenames() {
		return newFilenames;
	}

}
