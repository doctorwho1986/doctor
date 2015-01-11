package com.doctor.spring.context.inject;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * spring xml配置文件注入java.util.Properties的两种配置方法
 * @author doctor
 *
 * @since 2015年1月11日 下午9:49:28
 */
public class PropertiesInjectPractice {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/injectValueIntoBeanPropertiesPractice/propertiesInjectPracticeConfigurer.xml");
		PropertiesInjectBean1 injectBean1 = context.getBean(PropertiesInjectBean1.class);
		System.out.println(injectBean1.getProperties());
		
		PropertiesInjectBean2 injectBean2 = context.getBean(PropertiesInjectBean2.class);
		System.out.println(injectBean2.getProperties());
		
		context.close();
	}

}
