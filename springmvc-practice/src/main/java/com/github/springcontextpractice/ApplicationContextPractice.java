package com.github.springcontextpractice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gitjub.springmvc.scheduler.Min11x5OpenAward;

public class ApplicationContextPractice {
	private static final String path = "/springmvcPracticeConfig/spring-context.xml";
	private static ApplicationContext classPathXmlApplicationContext;
	static{
		classPathXmlApplicationContext = new ClassPathXmlApplicationContext(path);

	}

	public static void main(String[] args) {
		Min11x5OpenAward min11x5OpenAward = classPathXmlApplicationContext.getBean(Min11x5OpenAward.class);
		min11x5OpenAward.getOpenAwardOfLeCai();

	}

}
