package com.github.doctor.experiment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * spring 接口调用不受spring 管理类的方法
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
