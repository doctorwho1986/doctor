package com.doctor.spring.context.inject;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author doctor
 *
 * @since 2015年1月7日 下午8:41:24
 */
public class InjectValueIntoBeanPropertiesPractice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/injectValueIntoBeanPropertiesPractice/spring-context1.xml");
		FileNameGenerator fileNameGenerator = context.getBean("fileNameGenerator", FileNameGenerator.class);
		System.out.println(fileNameGenerator.getName());
		System.out.println(fileNameGenerator.getType());

		context = new ClassPathXmlApplicationContext("classpath:/injectValueIntoBeanPropertiesPractice/spring-context2.xml");

		fileNameGenerator = context.getBean("fileNameGenerator", FileNameGenerator.class);
		System.out.println(fileNameGenerator.getName());
		System.out.println(fileNameGenerator.getType());
	}

}
