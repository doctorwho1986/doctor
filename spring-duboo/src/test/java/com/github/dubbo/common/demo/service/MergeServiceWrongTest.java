package com.github.dubbo.common.demo.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 一台机器上启动两个应用，客户端只能调到一个，这种聚合不能实现．这应该是集群概念了
 * http://alibaba.github.io/dubbo-doc-static/User+Guide-zh.htm#UserGuide-zh-%E6%9C%8D%E5%8A%A1%E5%88%86%E7%BB%84
 * dubbo 的文档指出：按组合并返回结果，比如菜单服务，接口一样，但有多种实现，用group区分，
 * 现在消费方需从每种group中调用一次返回结果，合并结果返回，这样就可以实现聚合菜单项。
 * 
 * 注意：
 * 从 dubbo 2.2.0 开始，每个服务默认都会在本地暴露；在引用服务的时候，默认优先引用本地服务；如果希望引用远程服务可以使用一下配置强制引用远程服务。
 * ...
 * <dubbo:reference ... scope="remote" />
 * ...
 * 
 * @author doctor
 *
 * @time 2014年12月22日 下午6:01:32
 */
public class MergeServiceWrongTest {
	private static final Logger log = LoggerFactory.getLogger(MergeServiceWrongTest.class);

	private ClassPathXmlApplicationContext providerContextOne;
	private String providerConfigLocation = "/dubboPractice-provider/demo-provider/spring-context.xml";

	private ClassPathXmlApplicationContext providerContext2;

	private ClassPathXmlApplicationContext consumerContext;
	private String consumerConfigLocation = "/dubboPractice-provider/demmo-consumer/spring-dubbo.xml";

	@Before
	public void init() {
		providerContextOne = new ClassPathXmlApplicationContext(providerConfigLocation);
		providerContextOne.start();

		providerContext2 = new ClassPathXmlApplicationContext(providerConfigLocation);
		providerContext2.start();

		consumerContext = new ClassPathXmlApplicationContext(consumerConfigLocation);
		consumerContext.start();
	}

	@After
	public void destroy() {
		while (true) {

		}
		// providerContextOne.close();
		// providerContext2.close();
		// consumerContext.close();
	}

	@Test
	public void testGet() {
		log.info("{dubbo method:'{}'}",MergeService.class);
		MergeService mergeService = (MergeService) consumerContext.getBean("mergeService");
		
		System.out.println(mergeService.get(6));
	}

}
