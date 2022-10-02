package com.kt.corp.comm;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class ContextWrapper implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	
	public static ApplicationContext getContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		applicationContext = ac;
	}

}
