package com.github.dubbo.demo.merge.compont;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.dubbo.common.demo.service.MergeService;
/**
 * 模拟jstrom bolt ,bolt一台机器上多实例(有可能实例分到不同机器上），分组聚合实验失败
 * 注：模拟的是一个jvm启动多个dubbo应用（端口一样不会报错）。和集群有区别，集群是一台机器上部署多个相同的dubbo，但端口不一样（一样报错）
 * ，或者不同机器上部署。
 * @author doctor
 *
 * @time   2014年12月23日 上午10:22:07
 */
public class JstormBoltTest {
	private JstormBolt jstormBolt1 = new JstormBolt();
	private JstormBolt jstormBolt2 = new JstormBolt();
	
	private ClassPathXmlApplicationContext consumerContext;
	private String consumerConfigLocation = "/dubboPractice-provider/dubbo-merge2/spring-dubbo-consumer.xml";

	@Before
	public void init(){
		jstormBolt1.prepare();
		jstormBolt2.prepare();
		
		consumerContext = new ClassPathXmlApplicationContext(consumerConfigLocation);
		consumerContext.start();
	}
	
	@After
	public void destroy(){
		while(true){
			
		}
//		jstormBolt1.cleanup();
//		jstormBolt2.cleanup();
//		
//		consumerContext.close();
	}
	@Test
	public void test() {
		 MergeService mergeService = (MergeService) consumerContext.getBean("mergeService");
		 List<String> list = mergeService.get(2);
		 System.out.println(list);
	}

}
