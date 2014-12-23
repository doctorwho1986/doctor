package com.github.dubbo.demo.merge.compont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.dubbo.common.demo.service.MergeService;

/**
 * 模拟jstrom bolt ,bolt一台机器上多实例，对dubbo提供的服务有什么影响，分组聚合会其作用吗
 * 
 * @author doctor
 *
 * @time 2014年12月23日 上午9:49:40
 */
public class JstormBolt {
	private AbstractApplicationContext springCtx;
	
	@Autowired
	@Qualifier("mergeServiceOne")
	private MergeService mergeService;

	public JstormBolt() {

	};

	public void prepare() {
		this.springCtx = new ClassPathXmlApplicationContext("classpath:/dubboPractice-provider/dubbo-merge2/spring-context-provider.xml");
		this.springCtx.getAutowireCapableBeanFactory().autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
		
	}
	
	public void cleanup() {
		if(springCtx != null){
			springCtx.close();
		}
	}
}
