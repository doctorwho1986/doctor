package com.github.doctor.experiment;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.dubbo.common.demo.service.MergeService;

/**
 * 想验证以下spring 中一个接口，多个实现。dubbo在扩展spring的时候，对这种情况做了改变。
 * @author doctor
 *
 * @time   2014年12月26日 上午9:35:36
 */
public class SpringOneServiceMoreImpl {

	private ClassPathXmlApplicationContext springContext;
	private String configLocation = "/dubboPractice-provider/spring-test-experiment/spring-context-one-service-more-impl.xml";

	@Before
	public void init() {
		springContext = new ClassPathXmlApplicationContext(configLocation);
		springContext.start();

	}

	@After
	public void destroy() {
		springContext.close();
	}
	
	
	@Test
	public void test_one_service_more_impl(){
		String[] beanDefinitionNames = springContext.getBeanDefinitionNames();
		for (String beanName : beanDefinitionNames) {
			 Object bean = springContext.getBean(beanName);
			 System.out.println(bean);
			 Class<?>[] interfaces = bean.getClass().getInterfaces();
			 for (Class<?> interfacesItem : interfaces) {
				 //不同实现接口都一样的，和dubbo不一样
				assertThat(interfacesItem.getName(), equalTo(MergeService.class.getName()));
				System.out.println(interfacesItem);
			}
		}
	}
}
