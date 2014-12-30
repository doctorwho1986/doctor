package com.doctor.spring.context;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * It is recommended that you do not use the InitializingBean interface because
 * it unnecessarily couples the code to Spring. Alternatively, use the @PostConstruct
 * annotation or specify a POJO initialization method. In the case of XML-based
 * configuration metadata, you use the init-method attribute to specify the name
 * of the method that has a void no-argument signature.
 * 
 * @author doctor
 *
 * @since 2014年12月31日 上午12:42:56
 */
@Component("initializingBean2")
public class InitializingBean2 implements ApplicationContextAware {
	private static final Logger log = LoggerFactory.getLogger(InitializingBean2.class);
	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	@PostConstruct
	public void init() {
		log.info("{InitializingBean2:'@PostConstruct'}");
		log.info(context.toString());
	}

}
