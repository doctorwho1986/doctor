package com.doctor.spring.context.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("beanLifecycleTest")
public class BeanLifecycleTest implements BeanFactoryAware,BeanNameAware,InitializingBean,DisposableBean,ApplicationContextAware {
	private static final Logger log = LoggerFactory.getLogger(BeanLifecycleTest.class);

	@Autowired
	@Qualifier("lifecycleAnnotherBean")
	private LifecycleAnnotherBean lifecycleAnnotherBean;
	
	private BeanFactory beanFactory;
	private String beanName;
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
		log.info("{BeanFactoryAware:'setBeanFactory(BeanFactory beanFactory)'}");

	}

	@Override
	public void setBeanName(String name) {
		this.beanName = beanName;
		log.info("{BeanNameAware:'setBeanName(String name)'}");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("{InitializingBean:'afterPropertiesSet()'}");
		
	}

	@Override
	public void destroy() throws Exception {
		log.info("{DisposableBean:'destroy()'}");
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		log.info("ApplicationContextAware:' setApplicationContext(ApplicationContext applicationContext)'");
		
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("{@PostConstruct:'postConstruct()'}");
		log.info("LifecycleAnnotherBean in BeanLifecycleTest");
	}

	@PreDestroy
	public void prdDestroy(){
		log.info("@PreDestroy:'prdDestroy()'");
	}
}
