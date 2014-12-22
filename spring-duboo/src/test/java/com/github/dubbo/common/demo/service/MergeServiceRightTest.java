package com.github.dubbo.common.demo.service;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * dubbo　merger不同服务实现结果集． 详细查看com.alibaba.dubbo.rpc.cluster.merger
 * 
 * http://alibaba.github.io/dubbo-doc-static/User+Guide-zh.htm#UserGuide-zh-%E6%
 * 9C%8D%E5%8A%A1%E5%88%86%E7%BB%84 dubbo
 * 的文档指出：按组合并返回结果，比如菜单服务，接口一样，但有多种实现，用group区分，
 * 现在消费方需从每种group中调用一次返回结果，合并结果返回，这样就可以实现聚合菜单项。
 * 
 * @author doctor
 *
 * @time 2014年12月22日 下午6:01:32
 */
public class MergeServiceRightTest {

	private ClassPathXmlApplicationContext providerContext;
	private String providerConfigLocation = "/dubboPractice-provider/dubbo-merge/spring-context-provider.xml";

	private ClassPathXmlApplicationContext consumerContext;
	private String consumerConfigLocation = "/dubboPractice-provider/dubbo-merge/spring-dubbo-consumer.xml";

	@Before
	public void init() {
		providerContext = new ClassPathXmlApplicationContext(providerConfigLocation);
		providerContext.start();

		consumerContext = new ClassPathXmlApplicationContext(consumerConfigLocation);
		consumerContext.start();
	}

	@After
	public void destroy() {
		providerContext.close();
		consumerContext.close();
	}

	@Test
	public void testGet() {
		 MergeService mergeService = (MergeService) consumerContext.getBean("mergeService");
		 List<String> list = mergeService.get(6);
		 System.out.println(list);
		 assertThat(list.size(),equalTo(2) );
		 assertThat(list.get(0).length(),equalTo(6) );
		 assertThat(list.get(1).length(),equalTo(6) );
		
	}
}
