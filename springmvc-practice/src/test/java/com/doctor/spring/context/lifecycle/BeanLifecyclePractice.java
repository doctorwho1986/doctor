package com.doctor.spring.context.lifecycle;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 各种spring　生命周期接口调用顺序　查看结果
 * 
 * @author doctor
 *
 * @since 2015年1月4日 下午10:37:13
 */
public class BeanLifecyclePractice {

	private AnnotationConfigApplicationContext context;

	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext(BeanLifecyclePracticeConfig.class);
	}

	@Test
	public void test() {
		//結果：
		
//		01-04 22:35:58.560 main  INFO  o.s.c.a.AnnotationConfigApplicationContext -
//		Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@20322d26: startup date [Sun Jan 04 22:35:58 CST 2015]; root of context hierarchy
//01-04 22:35:58.741 main  INFO  c.d.s.c.l.LifecycleAnnotherBean -
//		@PostConstruct:'postConstruct()'
//01-04 22:35:58.742 main  INFO  c.d.s.c.l.BeanLifecycleTest -
//		{BeanNameAware:'setBeanName(String name)'}
//01-04 22:35:58.742 main  INFO  c.d.s.c.l.BeanLifecycleTest -
//		{BeanFactoryAware:'setBeanFactory(BeanFactory beanFactory)'}
//01-04 22:35:58.743 main  INFO  c.d.s.c.l.BeanLifecycleTest -
//		ApplicationContextAware:' setApplicationContext(ApplicationContext applicationContext)'
//01-04 22:35:58.743 main  INFO  c.d.s.c.l.BeanLifecycleTest -
//		{@PostConstruct:'postConstruct()'}
//01-04 22:35:58.743 main  INFO  c.d.s.c.l.BeanLifecycleTest -
//		LifecycleAnnotherBean in BeanLifecycleTest
//01-04 22:35:58.743 main  INFO  c.d.s.c.l.BeanLifecycleTest -
//		{InitializingBean:'afterPropertiesSet()'}

	}

}
