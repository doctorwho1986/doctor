package com.doctor.spring.annotation.propertySource2;

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
public class PropertySourcesAnnotationPractice {

	private AnnotationConfigApplicationContext context;

	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext(PropertySourcesAnnotationConfig.class);
		context.start();
	}

	@After
	public void destroy() {
		context.close();
	}

	@Test
	public void test_Value_Annotation(){
		PropertySourcesAnnotationBean bean = context.getBean(PropertySourcesAnnotationBean.class);
		assertThat(bean.getJdbcUrl(), equalTo("jdbc:postgresql://127.0.0.1:5432/doctor"));
		assertThat(bean.getJdbcUsername(), equalTo("doctor"));
		assertThat(bean.getJdbcPassword(), equalTo("doctor"));
	}
}
