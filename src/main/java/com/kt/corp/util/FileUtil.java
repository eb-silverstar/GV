package com.kt.corp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	public static File multipartToFile (MultipartFile multipart) throws IllegalStateException, IOException {
		File convFile = new File( multipart.getOriginalFilename() );
		multipart.transferTo(convFile);
		return convFile;
	}

	public static String mkdirYYYYMMDDDir(String preFixPath) throws IOException {

		LocalDate now = LocalDate.now();

		int year = now.getYear();

		int month = now.getMonthValue();
		String strMonth = String.format("%02d", month);

		int dayOfMonth = now.getDayOfMonth();
		String strDayOfMonth = String.format("%02d", dayOfMonth);

		String filePath = year + File.separator + strMonth + File.separator + strDayOfMonth;

		filePath = preFixPath + File.separator + filePath;

		File file = new File(filePath);

		if (!file.exists()) {
			file.mkdirs();
		}

		setPermission(preFixPath + File.separator + year, "777");
		return filePath;
	}

	public static String getHHMISS() {

		LocalTime now = LocalTime.now();

		int minute = now.getMinute();
		int hour = now.getHour();
		int second = now.getSecond();

		String filePath =
				String.format("%02d", hour)
				+ String.format("%02d", minute)
				+ String.format("%02d", second);

		return filePath;
	}

	public static void fileWriter(String filePath, String text) throws IOException {
		BufferedWriter writer = null;
		try {
			File file = new File(filePath);
			FileWriter fw = new FileWriter(file);
			writer = new BufferedWriter(fw);
			writer.write(text);

		} catch (Exception e) {
			e.hashCode();
		} finally {
			writer.close();
		}
	}


	public static void fileDelete(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			fileDel(file);
		}
	}
	
	private synchronized static void fileDel(File file) {
		file.delete();
	}

	public static void fileWriter(String filePath, String text, boolean appendYn) throws IOException {
		BufferedWriter writer = null;
		try {
			File file = new File(filePath);
			FileWriter fwAppend = new FileWriter(file, appendYn);
			writer = new BufferedWriter(fwAppend);
			writer.write(text);

		} catch (Exception e) {
			e.hashCode();
		} finally {
			writer.close();
		}
	}

	public static boolean setPermission(String path, String permission) throws IOException {

		boolean res = true;

		String cmd = "chmod -R " + permission + " " + path;

		try {
			// 그룹 권한을 수정해야 돼서 쉘 커맨드 사용
			Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			res = false;
		} finally {
			return res;
		}

	}
	
	public static String getBase64String(String filePath) throws Exception {
	    String strBase64 = "";
	    File f = new File(filePath);
	    
	    if (f.exists() && f.isFile() && f.length() > 0) {
	        byte[] bt = new byte[(int) f.length()];
	        FileInputStream fis = null;

	        try {
	            fis = new FileInputStream(f);
	            fis.read(bt);
	            strBase64 = new String(Base64.encodeBase64(bt));
	        } catch (Exception e) {
	            throw e;
	        } finally {
	            try {
	                if (fis != null) {
	                    fis.close();
	                }
	            } catch (IOException e) {
	            } catch (Exception e) {
	            }
	        }
	    }
		return strBase64;
	}

}
