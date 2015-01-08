package com.doctor.spring.context.inject.experiment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 配置文件内如果不用<context:component-scan base-package="com.doctor.spring.context.inject.experiment" />
 * 属性注入需要自己显示配置
 * @author doctor
 *
 * @time   2015年1月8日 上午11:21:26
 */
public class AutoWirePractice {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/context-inject-experiment/spring-config1.xml");
		Bean1 bean1 = context.getBean("bean1",Bean1.class);
		System.out.println(bean1.getBean2());

	}

}
