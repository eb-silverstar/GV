package com.kt.corp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUploadUtil {
	public static final String CREATE_NEW_FOLDER="RANDOM_CREATE_BY_CURTIME";
	/** 인스턴스 생성 금지
	 *
	 */
	private FileUploadUtil(){

	}

	/**
	 * @param request
	 * @param uploadBasePath 파일을 저장할 최상위 경로, 해당 폴더 및 하위에 쓰기권한 필요, 이하 폴더 자동생성
	 * @return
	 * @throws IOException
	 */
	public static FileUploadResults upload(HttpServletRequest request, String uploadBasePath)
			throws IOException
	{
		return upload(request, uploadBasePath, CREATE_NEW_FOLDER);
	}

	/**
	 * @param request
	 * @param uploadBasePath 마지막 "/"는 붙이거나 말거나 상관없음
	 * @param uploadRelativePath 마지막 "/"는 붙이거나 말거나 상관없음
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public static FileUploadResults upload(HttpServletRequest request, String uploadBasePath1, String uploadRelativePath1)
		throws IOException
	{
		
		long fileSize = 0;
		MultipartHttpServletRequest mrequest=null;
		try {
			//mrequest=(MultipartHttpServletRequest)request;
			Assert.state(request instanceof MultipartHttpServletRequest,
					"request !instanceof MultipartHttpServletRequest");
			mrequest = (MultipartHttpServletRequest) request;
		} catch(Exception e) {
			return null;
		}
		Map fileMap=mrequest.getFileMap();

		if (fileMap==null) return null;

		String uploadBasePath=StringUtils.defaultString(uploadBasePath1).replaceAll("/+$", "")+File.separator;
		String uploadRelativePath=StringUtils.defaultString(uploadRelativePath1).replaceAll("/+$", "");
		uploadRelativePath=StringUtils.defaultString(uploadRelativePath).replaceAll("^/+", "");

		FileUploadResults results=new FileUploadResults();
		
		String uploadPath=uploadBasePath;
		results.setUploadBase(uploadPath);
		String uploadRelPath=StringUtils.defaultString(uploadRelativePath);
		String systemFileName = "";
		if (CREATE_NEW_FOLDER.equals(uploadRelativePath)) {
			int year, month, day;
			Timestamp ts=new Timestamp(System.currentTimeMillis());
			long strTs=(long)(Math.ceil(ts.getTime()/1000));
			Calendar cal=Calendar.getInstance();
			year=cal.get(Calendar.YEAR);
			month=cal.get(Calendar.MONTH)+1;
			day=cal.get(Calendar.DAY_OF_MONTH);
			uploadRelPath=year+File.separator+month+File.separator+day+File.separator+strTs;
		}

		results.setUploadRelPath(uploadRelPath);
		results.setUploadPath(results.getUploadBase()+uploadRelPath+File.separator);

		boolean folderPrepared=false;

		uploadPath=results.getUploadPath();
		for (Iterator it=fileMap.entrySet().iterator();it.hasNext();) {
			Entry entry=(Entry)it.next();
			MultipartFile mf=(MultipartFile)entry.getValue();
			results.addMultipartFile(mf);
			results.addRequestName(mf.getName());
			String originalFilename=mf.getOriginalFilename();
			results.addOriginalFilename(originalFilename);
			
			if (!mf.isEmpty()) {
				if (!folderPrepared) {
					createDir(uploadPath);
					folderPrepared=true;
				}
				
				
				
				systemFileName = System.currentTimeMillis()+"."+originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());;
				File fOri = new File(uploadPath, systemFileName);
				File f=null;
				
//				if (fOri.exists()) {
//					FileRenamer fileRenamer=new FileRenamer(fOri);
//					results.addNewFilename(fileRenamer.getNewName());
//					f=new File(uploadPath, fileRenamer.getNewName());
//				} else {
//					results.addNewFilename(systemFileName);
//					f=fOri;
//				}
				
				results.addNewFilename(systemFileName);
				f=fOri;
				
				
				results.addUploadFile(f);
				InputStream is=mf.getInputStream();
				OutputStream os=new FileOutputStream(f);
				FileCopyUtils.copy(is, os);

				os.close();
				is.close();
				f = null;
				fOri = null;
			}
		}
		return results;
	}
	
	
	
	/**
	 * @param request
	 * @param uploadBasePath 마지막 "/"는 붙이거나 말거나 상관없음
	 * @param uploadRelativePath 마지막 "/"는 붙이거나 말거나 상관없음
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public static FileUploadResults uploadForDirectory(HttpServletRequest request, String uploadBasePath1, String uploadRelativePath1)
		throws IOException
	{
		
		long fileSize = 0;
		MultipartHttpServletRequest mrequest=null;
		try {
			Assert.state(request instanceof MultipartHttpServletRequest,"request !instanceof MultipartHttpServletRequest");
			mrequest = (MultipartHttpServletRequest) request;
		} catch(Exception e) {
			return null;
		}
		Map fileMap=mrequest.getFileMap();

		if (fileMap==null) return null;

		String uploadBasePath=StringUtils.defaultString(uploadBasePath1).replaceAll("/+$", "")+File.separator;
		String uploadRelativePath=StringUtils.defaultString(uploadRelativePath1).replaceAll("/+$", "");
		uploadRelativePath=StringUtils.defaultString(uploadRelativePath).replaceAll("^/+", "");

		FileUploadResults results=new FileUploadResults();
		
		String uploadPath=uploadBasePath;
		results.setUploadBase(uploadPath);
		String uploadRelPath=StringUtils.defaultString(uploadRelativePath);
		String systemFileName = "";
		
		results.setUploadRelPath(uploadRelPath);
		results.setUploadPath(results.getUploadBase()+uploadRelPath+File.separator);

		boolean folderPrepared=false;

		uploadPath=results.getUploadPath();
		
		for (Iterator it=fileMap.entrySet().iterator();it.hasNext();) {
			Entry entry=(Entry)it.next();
			MultipartFile mf=(MultipartFile)entry.getValue();
			
			if (!mf.getOriginalFilename().equals("")) {
			results.addFileSize(mf.getSize()+"");
			results.addMultipartFile(mf);
			results.addRequestName(mf.getName());
			String originalFilename=mf.getOriginalFilename();
			results.addOriginalFilename(originalFilename);
			
			if (!mf.getOriginalFilename().equals("")) {
				if (!folderPrepared) {
					createDir(uploadPath);
					folderPrepared=true;
				}
				
				SecureRandom secureRandom = new SecureRandom();
				secureRandom.setSeed(System.currentTimeMillis());
				
				systemFileName = secureRandom.nextInt(10000)+"_"+System.currentTimeMillis()+"."+originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());;
				File fOri = new File(uploadPath, systemFileName);
				File f=null;
				
				results.addNewFilename(systemFileName);
				f=fOri;
				
				results.addUploadFile(f);
				InputStream is=mf.getInputStream();
				OutputStream os=new FileOutputStream(f);
				FileCopyUtils.copy(is, os);

				os.close();
				is.close();
				f = null;
				fOri = null;
			}
		}
		}
		return results;
	}


	/** 디렉토리 생성
	 * @param dir
	 * @return
	 */
	public static File createDir(String dir) {
		File bf=new File(dir);
		bf.mkdirs();

		return bf;
	}
	/** 지정한 디렉토리 강제 삭제 프록시
	 * @param filePath
	 * @return
	 */
	public static boolean forceDelete(String filePath) {
		
		boolean result = true;
		
		if (filePath != null){
			File f = new File(filePath);
			
			if (f.exists()){
				result = forceDelete(f);
			}
		}
		return result;
	}
	/** 지정한 디렉토리 강제 삭제
	 * @param f
	 * @return
	 */
	public static boolean forceDelete(File f) {
		boolean retVal=false; 
		if (f.exists()) {
			try {
				if (f.isDirectory()) {
					FileUtils.deleteDirectory(f);
				} else {
					FileUtils.forceDelete(f);
				}
				retVal=true;
			} catch (Exception e) {e.hashCode(); }
		}
		return retVal;
	}
	public static void resetTempDir(String baseDir) {
		resetTempDir(baseDir, 3600*2);	// 2 hours
	}

	/** 2시간 지난 임시폴더 및 내용 삭제
	 * @param baseDir
	 * @param interval 초단위
	 */
	@SuppressWarnings("rawtypes")
	public static void resetTempDir(String baseDir, int interval) {
		File bf=createDir(baseDir);

		long cur=System.currentTimeMillis();
		long timeLimit=interval*1000;

		for (Iterator it=Arrays.asList(bf.listFiles()).iterator();it.hasNext();) {
			File f=(File)it.next();

			// 생성이후 제한시간 넘은 임시폴더 삭제
			if ((f.lastModified()+timeLimit)<cur) {
				forceDelete(f);
			}
		}
	}

	private static final String EXCEL_COMMA = ",";
	private static final String EXCEL_NEW_LINE = "\n";

	public static String getExcelData(String[] headerNames,
			String[] fieldNames, List<? extends Object> data) {
		StringBuffer sb = new StringBuffer();

		for (String header : headerNames) {
			sb.append(header).append(EXCEL_COMMA);
		}
		sb.append(EXCEL_NEW_LINE);

		try {
			for (int i = 0; i < data.size(); i++) {
				Object row = data.get(i);
				Method[] methods = row.getClass().getDeclaredMethods();
				String value = "";
				for (String fieldName : fieldNames) {
					String methodName = "get"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);
					for (Method method : methods) {
						value = "";
						if (method.getName().equals(methodName)) {
							value = fixNull(method.invoke(row, new Object[] {}));
							break;
						}
					}
					sb.append(value.replaceAll(",", "")).append(EXCEL_COMMA);
				}
				sb.append(EXCEL_NEW_LINE);
			}
		} catch (IllegalAccessException e) {
			e.hashCode();
		} catch (IllegalArgumentException e) {
			e.hashCode();
		} catch (InvocationTargetException e) {
			e.hashCode();
		} catch (Exception e) {
			e.hashCode();
		}

		return sb.toString();
	}

	public static String fixNull(Object obj) {
		return fixNull(obj, "");
	}

	public static String fixNull(Object obj, String def) {
		if (obj == null || "".equals(obj.toString()))
			return def;
		return obj.toString();
	}

}
