package com.doctor.spring.annotation.importresource;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author doctor
 *
 * @since 2015年1月1日 下午4:02:08
 */

public class ImportResourcePractice {
	private AnnotationConfigApplicationContext context;

	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext(ImportrResourceConfig.class);
		context.start();
	}

	@After
	public void destroy() {
		context.close();
	}

	@Test
	public void test() {
		ImportrResourceConfig resourceConfig = context.getBean(ImportrResourceConfig.class);
		assertNotNull(resourceConfig);
		assertThat(resourceConfig.getUrl(), equalTo("jdbc:hsqldb:hsql://localhost/xdb"));
		assertThat(resourceConfig.getUsername(), equalTo("sa"));
		assertThat(resourceConfig.getPassword(), equalTo("sa"));
	}
}
