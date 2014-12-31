package com.doctor.spring.context.beanpostprocessor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author doctor
 *
 * @since 2015年1月1日 上午12:50:23
 */
public class BeanPostProcessorPractice {
	private AnnotationConfigApplicationContext annotationConfigApplicationContext;
	
	@Before
	public void init(){
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);
		annotationConfigApplicationContext.start();
	}
	
	@After
	public void destroy(){
		annotationConfigApplicationContext.close();
	}
	@Test
	public void test(){
		SimpleBean bean = annotationConfigApplicationContext.getBean(SimpleBean.class);
		System.out.println(bean);
	}
	
}
