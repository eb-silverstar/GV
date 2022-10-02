package com.kt.corp.file.dto;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "파일 DTO")
public class FileDTO {
	
	@Schema(description = "파일 제목", example = "지니밸리_명세서.xlsx")
	private String fileName;
	
	@Schema(description = "파일 저장 경로", example = "file/ddw4ger-xe85m9d-se83fe")
	private String filePath;
	
	@Schema(description = "파일 다운로드용 리소스")
	private UrlResource resource;
	
	@Schema(description = "파일 다운로드용 헤더")
	private HttpHeaders headers;

}
