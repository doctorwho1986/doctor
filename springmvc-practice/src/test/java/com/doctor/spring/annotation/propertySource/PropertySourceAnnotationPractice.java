package com.doctor.spring.annotation.propertySource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * In Spring, you can use @PropertySource annotation to externalize your configuration to a
 * properties file.
 * 
 * @author doctor
 *
 * @time 2015年2月28日 下午4:15:16
 */
public class PropertySourceAnnotationPractice {

	private AnnotationConfigApplicationContext context;

	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext(PropertySourceAnnotationConfig.class);
		context.start();
	}

	@After
	public void destroy() {
		context.close();
	}

	@Test
	public void test_Value_Annotation(){
		PropertySourceAnnotationBean bean = context.getBean(PropertySourceAnnotationBean.class);
		assertThat(bean.getJdbcUrl(), equalTo("jdbc:hsqldb:hsql://localhost/xdb"));
		assertThat(bean.getJdbcUsername(), equalTo("sa"));
		assertThat(bean.getJdbcPassword(), equalTo("sa"));
	}
}
