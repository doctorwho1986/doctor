package com.doctor.spring.context.annotationbasedconfiguration;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 利用这个可以把一组相同父类的子类获得．一般用于组件组装．除了数组还支持list,map自动注入
 * It is also possible to provide all beans of a particular type from the
 * ApplicationContext by adding the annotation to a field or method that expects
 * an array of that type:
 * 
 * @author doctor
 *
 * @since 2015年1月1日 上午1:28:35
 */
public class AutowireAllBeansOfAParticularTypePractice {
	private AnnotationConfigApplicationContext applicationContext;

	@Before
	public void init() {
		applicationContext = new AnnotationConfigApplicationContext(AutowireAllBeansOfAParticularTypeConfig.class);
		applicationContext.start();
	}

	@After
	public void destroy() {
		applicationContext.close();
	}

	@Test
	public void test() {
		AnotherBean anotherBean = applicationContext.getBean(AnotherBean.class);
		BaseBean[] baseBeans = anotherBean.getBaseBeans();
		assertThat(baseBeans.length, equalTo(2));
		for (BaseBean baseBean : baseBeans) {
			System.out.println(baseBean);
		}
	}
}
