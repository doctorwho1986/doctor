package com.doctor.spring.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author doctor
 *
 * @since 2014年12月31日 上午12:33:40
 */
@Component("initializingBean1")
public class InitializingBean1 implements InitializingBean, ApplicationContextAware {
	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("----" + context);
		System.out.println();
		//可以对spring管理的bean动手术（她的属性等）

	}

}
