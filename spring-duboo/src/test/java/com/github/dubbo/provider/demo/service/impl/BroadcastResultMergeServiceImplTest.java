package com.github.dubbo.provider.demo.service.impl;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.dubbo.common.demo.service.BroadcastResultMergeService;

/**
 * 实验结果是对的．一个应用进程内启动dubbo服务多个，也能暴露服务．这样在jstorm　bolt这样的情况下也可以用dubbo
 * 去收集每个bolt实例的内存信息（因为一个bolt可能会很多实例，而多个实例可能在一个jvm进程内运行，或者不同jvm,在
 * 　我要做的就是不管是不是一个jvm进程启动几个dubbo，都认为是集群．而dubbo对于一个进程内启动多个相同的dubbo服务
 * 　有瑕疵，扩展dubbo已适用jstorm，这样，两次扩展dubbo就可以把每个集群的内存信息收集．
 *
 * 　测试启动注意：只启动这个，代表一个进程内2个相同的dubbo服务，你还得再启动一个jvm，修改集群名称，运行． 　结果就会列出这几个集群的名字．
 * 
 * @author doctor
 *
 * @since 2014年12月28日 上午7:54:52
 */
public class BroadcastResultMergeServiceImplTest {

	private ClassPathXmlApplicationContext providerContext;
	private String providerConfigLocation = "/dubboPractice-provider/dubboBroadcast-resultMerge/spring-context-provider.xml";

	private ClassPathXmlApplicationContext providerContext2;

	private ClassPathXmlApplicationContext consumerContext;
	private String consumerConfigLocation = "/dubboPractice-provider/dubboBroadcast-resultMerge/spring-dubbo-consumer.xml";

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
