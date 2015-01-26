package com.doctor.spring.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * 启动方便调试跟踪一些功能
 * @author doctor
 *
 * @time   2015年1月26日 上午11:19:50
 */
public class AnnotationConfigApplicationContextPractice {

	private static AnnotationConfigApplicationContext annotationConfigApplicationContext;

	public static void main(String[] args) {
		
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AnnotationConfigApplicationContextPracticeConfig.class);
		InitializingBean1 bean = annotationConfigApplicationContext.getBean(InitializingBean1.class);
		System.out.println(bean);
	}

}
