package com.github.dubbo.common.demo.service;

import static org.junit.Assert.*;

import static org.hamcrest.core.IsEqual.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author doctor
 *
 * @since 2014年12月21日 上午11:20:54
 */
public class DemoServiceTest {
	private ClassPathXmlApplicationContext providerContext;
	private String providerConfigLocation ="/dubboPractice-provider/demo-provider/spring-context.xml";
	
	private ClassPathXmlApplicationContext consumerContext;
	private String consumerConfigLocation ="/dubboPractice-provider/demmo-consumer/spring-dubbo.xml";

	@Before
	public void init(){
		providerContext = new ClassPathXmlApplicationContext(providerConfigLocation);
		providerContext.start();
		
		consumerContext = new ClassPathXmlApplicationContext(consumerConfigLocation);
		consumerContext.start();
	}
	
	@After
	public void destroy(){
		providerContext.close();
		consumerContext.close();
	}
	
	@Test
	public void testSayHello() {
		DemoService demoService = (DemoService) consumerContext.getBean("demoService");
		System.out.println(demoService.sayHello("doctor"));
		assertThat(demoService.sayHello("doctor"), equalTo("hello doctor"));
	}

	@Test
	public void testGetUsers() {
		DemoService demoService = (DemoService) consumerContext.getBean("demoService");
		demoService.getUsers().forEach(System.out::println);
	}
	
	

}
