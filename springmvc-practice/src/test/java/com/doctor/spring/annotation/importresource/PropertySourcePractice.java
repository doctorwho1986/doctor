package com.doctor.spring.annotation.importresource;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author doctor
 *
 * @since 2015年1月1日 下午4:32:00
 */
public class PropertySourcePractice {
	private AnnotationConfigApplicationContext context;

	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext(PropertySourceConfig.class);
		context.start();
	}

	@After
	public void destroy() {
		context.close();
	}

	@Test
	public void test() {
		PropertySourceConfig resourceConfig = context.getBean(PropertySourceConfig.class);
		assertNotNull(resourceConfig);
		assertThat(resourceConfig.getUrl(), equalTo("jdbc:hsqldb:hsql://localhost/xdb"));
		assertThat(resourceConfig.getUsername(), equalTo("sa"));
		assertThat(resourceConfig.getPassword(), equalTo("sa"));
	}
}
