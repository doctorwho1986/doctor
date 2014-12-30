package com.doctor.spring.context.contextaware;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author doctor
 *
 * @since 2014年12月31日 上午1:07:37
 */
@Component("contextawareBean")
public class ContextawareBean {
	private static final Logger log = LoggerFactory.getLogger(ContextawareBean.class);
	@Autowired
	private ApplicationContext context;

	public ApplicationContext getContext() {
		return context;
	}

	@PostConstruct
	public void init(){
		log.info("{ContextawareBean:{}}",context.toString());
	}
}
