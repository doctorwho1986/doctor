package com.doctor.spring.context;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 可以对spring管理的bean动手术（bean的属性等）
 * To interact with the container’s management of the bean lifecycle, you can
 * implement the Spring InitializingBean and DisposableBean interfaces. The
 * container calls afterPropertiesSet() for the former and destroy() for the
 * latter to allow the bean to perform certain actions upon initialization and
 * destruction of your beans.
 * 
 * @author doctor
 *
 * @since 2014年12月31日 上午12:30:17
 */
public class InitializingBeanPractice {
	private AnnotationConfigApplicationContext context;

	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext(InitializingBeanConfig.class);
	}

	@Test
	public void test() {

	}
}
