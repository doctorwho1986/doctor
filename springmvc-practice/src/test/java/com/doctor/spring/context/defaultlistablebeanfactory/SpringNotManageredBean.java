package com.doctor.spring.context.defaultlistablebeanfactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringNotManageredBean {
	
	private AnnotationConfigApplicationContext context;
	public void init(){
		context = new AnnotationConfigApplicationContext(DefaultListableBeanFactoryExperimentConfig.class);
		context.getBeanFactory().registerSingleton("springNotManageredBean", this);
		context.getBean(SpringManageredBean.class).setSpringNotManageredBean(this);
	}
	public final AnnotationConfigApplicationContext getContext() {
		return context;
	}
	
	
}
