package com.github.dubbo.common.demo.service;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BroadcastCluster + MergeCluster 调用集群中每个接口，汇总结果，（用于jstorm bolt启动dubbo，但对于bolt实例数目限制为1个）
 * 测试方法，启动俩个集群测试，不要在一个jvm里面（无效集群）
 * @author doctor
 *
 * @time 2014年12月22日 下午6:01:32
 */
public class MergeServiceClusterRightTest {

	private ClassPathXmlApplicationContext providerContext;
	private String providerConfigLocation = "/dubboPractice-provider/dubbo-merge-cluster/spring-context-provider.xml";

	private ClassPathXmlApplicationContext consumerContext;
	private String consumerConfigLocation = "/dubboPractice-provider/dubbo-merge-cluster/spring-dubbo-consumer.xml";

	@Before
	public void init() {
		providerContext = new ClassPathXmlApplicationContext(providerConfigLocation);
		providerContext.start();

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
