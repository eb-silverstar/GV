package com.kt.corp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GenieValleyApplication extends SpringBootServletInitializer{
	private static Logger logger = LogManager.getLogger(GenieValleyApplication.class);

	public static void main(String[] args) {
		logger.info("Starting Spring Boot Genie Valley~~");
		SpringApplication.run(GenieValleyApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(GenieValleyApplication.class);
	}
}
