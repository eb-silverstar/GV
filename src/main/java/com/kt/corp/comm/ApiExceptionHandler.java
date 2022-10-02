package com.kt.corp.comm;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.kt.corp.message.manage.DbMessageManager;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler extends BaseComm {
	
	@ExceptionHandler(value = { ApiException.class })
	public ResponseEntity<ApiResponseEntity> exceptionHandler(HttpServletRequest request, final ApiException e) {
		e.printStackTrace();
		return ResponseEntity
				.status(e.getStatus())
				.body(new ApiResponseEntity(DbMessageManager.getMessage("I0002", "ko"), null, e.getMsgCode(), e.getMsgDesc()));
	}
	
	@ExceptionHandler(value = { PersistenceException.class })
	public ResponseEntity<ApiResponseEntity> exceptionHandler(HttpServletRequest request, final PersistenceException e) {
		String errorMessage = null;
		
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			errorMessage = sw.toString();
			sw.close();
			pw.close();
		} catch(IOException e1) {
			logger.error("ApiExceptionHandler exceptionHandler ERROR: ", e1);
		}
		
		logger.info(request.toString());
		e.printStackTrace();
		
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponseEntity(DbMessageManager.getMessage("I0002", "ko"), null, null, errorMessage));
	}
	
	@ExceptionHandler(value = { TooManyResultsException.class })
	public ResponseEntity<ApiResponseEntity> exceptionHandler(HttpServletRequest request, final TooManyResultsException e) {
		String errorMessage = null;
		
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			errorMessage = sw.toString();
			sw.close();
			pw.close();
		} catch(IOException e1) {
			logger.error("ApiExceptionHandler exceptionHandler ERROR: ", e1);
		}
		
		logger.info(request.toString());
		e.printStackTrace();
		
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponseEntity(DbMessageManager.getMessage("I0002", "ko"), null, null, errorMessage));
	}
	
	@ExceptionHandler(value = { HttpClientErrorException.class })
	public ResponseEntity<ApiResponseEntity> exceptionHandler(HttpServletRequest request, final HttpClientErrorException e) {
		logger.info(request.toString());
		e.printStackTrace();
		String errorMessage = e.getStatusText();
		
		return ResponseEntity
				.status(e.getStatusCode())
				.body(new ApiResponseEntity(DbMessageManager.getMessage("I0002", "ko"), null, null, errorMessage));
	}
	
	@ExceptionHandler(value = { AccessDeniedException.class })
    public ResponseEntity<ApiResponseEntity> exceptionHandler(HttpServletRequest request, final AccessDeniedException e) {
        logger.info(request.toString());
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponseEntity(DbMessageManager.getMessage("I0002", "ko"), null, null, e.getMessage()));
    }
	
	@ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ApiResponseEntity> exceptionHandler(HttpServletRequest request, final Exception e) {
        logger.info(request.toString());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseEntity(DbMessageManager.getMessage("I0002", "ko"), null, null, e.getMessage()));
    }

}
