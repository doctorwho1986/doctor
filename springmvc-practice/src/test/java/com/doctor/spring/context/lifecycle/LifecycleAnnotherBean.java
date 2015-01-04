package com.doctor.spring.context.lifecycle;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component("lifecycleAnnotherBean")
public class LifecycleAnnotherBean {
	private static final Logger log = LoggerFactory.getLogger(LifecycleAnnotherBean.class);

	@Autowired
	private ApplicationContext applicationContext;
	@PostConstruct
	public void postConstruct(){
		log.info("@PostConstruct:'postConstruct()'");
		log.info(applicationContext.toString());
	}
}
