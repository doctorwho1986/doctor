package com.github.doctor.dubbo.rpc.cluster;


import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.dubbo.common.demo.service.BroadcastResultMergeService;

/**
 * @author doctor
 *
 * @since 2014年12月31日 下午9:49:13
 */
public class MergeResultClusterTest {

	private ClassPathXmlApplicationContext providerContext;
	private String providerConfigLocation = "/dubboPractice-provider/dubbo-mergeResultCluster/spring-context-provider.xml";

	private ClassPathXmlApplicationContext providerContext2;

	private ClassPathXmlApplicationContext consumerContext;
	private String consumerConfigLocation = "/dubboPractice-provider/dubbo-mergeResultCluster/spring-dubbo-consumer.xml";

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
		System.out.println(list);

	}

}
