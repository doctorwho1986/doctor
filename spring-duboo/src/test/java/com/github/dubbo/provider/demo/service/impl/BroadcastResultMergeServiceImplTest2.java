package com.github.dubbo.provider.demo.service.impl;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.*;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.dubbo.common.demo.service.BroadcastResultMergeService;

/**
 * 猜想是对的，这种集群结果不能合并（这是jstorm bolt 启动dubbo应用的情况）
 * 想实验一下不用扩展的BroadcastResultMergeCluster，直接用BroadcastCluster 调用结果被覆盖，直接用MergeableCluster 也只能取到一个结果，
 * 因为MergeableCluster 是对于一个dubbo服务中一个接口多个不同实现的情况聚合结果
 * @author doctor
 *
 * @time   2014年12月31日 下午4:37:59
 */
public class BroadcastResultMergeServiceImplTest2 {

	private ClassPathXmlApplicationContext providerContext;
	private String providerConfigLocation = "/dubboPractice-provider/dubboBroadcast-resultMerge5/spring-context-provider.xml";

	private ClassPathXmlApplicationContext providerContext2;

	private ClassPathXmlApplicationContext consumerContext;
	private String consumerConfigLocation = "/dubboPractice-provider/dubboBroadcast-resultMerge5/spring-dubbo-consumer.xml";

	@Before
	public void init() {
		providerContext = new ClassPathXmlApplicationContext(providerConfigLocation);
		providerContext.start();
		BroadcastResultMergeService bean = (BroadcastResultMergeService) providerContext.getBean("broadcastResultMergeService");
		bean.setClusterName("dubbo-cluster-1");

		providerContext2 = new ClassPathXmlApplicationContext(providerConfigLocation);
		providerContext2.start();
		bean = (BroadcastResultMergeService) providerContext2.getBean("broadcastResultMergeService");
		bean.setClusterName("dubbo-cluster-2");

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
	public void testGetClusterName() {
		BroadcastResultMergeService broadcastResultMergeService = (BroadcastResultMergeService) consumerContext.getBean("broadcastResultMergeService");
		List<String> list = broadcastResultMergeService.getClusterName();
		assertThat(list.size(), equalTo(1));
		System.out.println(list);

	}

}
