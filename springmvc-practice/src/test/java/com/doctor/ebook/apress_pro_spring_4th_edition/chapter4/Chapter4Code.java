package com.doctor.ebook.apress_pro_spring_4th_edition.chapter4;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author doctor
 *
 * @since 2015年3月7日 下午2:35:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Chapter4RootConfig.class })
public class Chapter4Code {

	@Resource(name = "simpleBean")
	private SimpleBean simpleBean;
	
	@Resource
	private BeanNamePrinter beanNamePrinter;

	@Test
	public void test_init() {
		System.out.println(simpleBean);
	}
	
	@Test
	public void test_get_beanName(){
		System.out.println(beanNamePrinter.getBeanName());
		assertThat(beanNamePrinter.getBeanName(), equalTo("doctorBeanNamePrinter"));
	}
}
