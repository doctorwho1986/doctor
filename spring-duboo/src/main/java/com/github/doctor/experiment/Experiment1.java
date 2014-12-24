package com.github.doctor.experiment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * spring 接口调用不受spring 管理类的方法，因为jstrom bolt不受spring管理，与dubbo结合提供服务，如何
 * 让dubbo调用bolt实例方法，每个bolt的实例可能位于同一个机器，或不同机器。他们提供的接口注册在zookeeper上
 * 除最后的时间戳不一样，其它都是一样的，消费端调用就一个（按集群处理）
 * @author doctor
 *
 * @time   2014年12月24日 上午11:36:12
 */
public class Experiment1 implements ReportInfo{
	private AbstractApplicationContext springCtx;

	@Autowired
	@Qualifier("experimentService")
	public ExperimentService experimentService;

	public void prepare() {
		this.springCtx = new ClassPathXmlApplicationContext("classpath:/springdubbo-experiment/experiment1.xml");
//		this.springCtx.getAutowireCapableBeanFactory().autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);

		 experimentService = (ExperimentService) this.springCtx.getBean("experimentService");
	}
	
	@Override
	public String reportInfo() {
		return "hello reportInfo";
	}

	public void cleanup() {
		if (springCtx != null) {
			springCtx.close();
		}
	}
}
