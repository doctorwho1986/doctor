package com.github.dubbo.common.demo.service;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboNamespaceHandlerEXTest {

	private ClassPathXmlApplicationContext providerContext;
	private String providerConfigLocation = "/dubboPractice-provider/dubboNamespaceHandlerEX/spring-context-provider.xml";

	private ClassPathXmlApplicationContext providerContext2;
	
	private ClassPathXmlApplicationContext consumerContext;
	private String consumerConfigLocation = "/dubboPractice-provider/dubboNamespaceHandlerEX/spring-dubbo-consumer.xml";

	@Before
	public void init() {
		providerContext = new ClassPathXmlApplicationContext(providerConfigLocation);
		providerContext.start();

		providerContext2 = new ClassPathXmlApplicationContext(providerConfigLocation);
		providerContext2.start();
		
		consumerContext = new ClassPathXmlApplicationContext(consumerConfigLocation);
		consumerContext.start();
	}

	@After
	public void destroy() {
		// 这里写个while死循环，eclipse安装zookeeper可视化界面看服务提供和消费者在注册中心的情况
		while (true) {

		}
		// providerContext.close();
		// consumerContext.close();
	}

	@Test
	public void testGet() {
		MergeService mergeService = (MergeService) consumerContext.getBean("mergeService");
		List<String> list = mergeService.get(6);
		System.out.println(list);
		assertThat(list.size(), equalTo(3));
		assertThat(list.get(0).length(), equalTo(6));
		assertThat(list.get(1).length(), equalTo(6));
		assertThat(list.get(2).length(), equalTo(6));

	}
}
