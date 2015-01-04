package com.github.doctor.dubbo.config;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.github.dubbo.common.demo.service.GetIpAndPortService;

public class DubboPortHandler2IPandPortTest {

	private ClassPathXmlApplicationContext providerContext;
	private String providerConfigLocation = "/dubboPractice-provider/dubboPortHandler2-IPandPort/spring-context-provider.xml";

	private ClassPathXmlApplicationContext providerContext2;

	private ClassPathXmlApplicationContext consumerContext;
	private String consumerConfigLocation = "/dubboPractice-provider/dubboPortHandler2-IPandPort/spring-dubbo-consumer.xml";

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
//		providerContext.close();
//		consumerContext.close();
	}

	@Test
	public void testGetClusterName() {
		GetIpAndPortService getIpAndPortService = (GetIpAndPortService) consumerContext.getBean("getIpAndPortService");
		Map<String, String> memoryInfo = getIpAndPortService.getMemoryInfo();
		memoryInfo.forEach((k, v) -> {
			System.out.println(k + " : " + v);
		});

	}

}
