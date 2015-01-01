package com.doctor.spring.annotation;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * @Value bean属性值的用法．一个bean的属性初始化用另一个bean的属性值．但容器初始化后，再设置属性，依赖
 * 这个属性的bean中的值不会跟着改变．
 * @author doctor
 *
 * @since 2015年1月1日 上午11:31:38
 */
public class ValueAnnotationPractice {
	private AnnotationConfigApplicationContext context;

	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext(ValueAnnotationConfig.class);
		context.start();
	}

	@After
	public void destroy() {
		context.close();
	}

	@Test
	public void test() {
		ValueBean valueBean = context.getBean(ValueBean.class);

		assertNotNull(valueBean.getBeanName());
		assertThat("doctor", equalTo(valueBean.getBeanName()));
		assertThat(600, equalTo(valueBean.getBeanAge()));

		Bean bean = context.getBean(Bean.class);
		assertThat("doctor", equalTo(bean.getName()));
		assertThat(600, equalTo(bean.getAge()));

		//改变属性
		bean.setName("hello-doctor");
		bean.setAge(200);
		assertThat("hello-doctor", equalTo(bean.getName()));
		assertThat(200, equalTo(bean.getAge()));

		//引入这个bean属性的bean不会跟着变．
		assertThat("doctor", equalTo(valueBean.getBeanName())); // 不能随着依赖而变化
		assertThat(600, equalTo(valueBean.getBeanAge()));

	}
}
