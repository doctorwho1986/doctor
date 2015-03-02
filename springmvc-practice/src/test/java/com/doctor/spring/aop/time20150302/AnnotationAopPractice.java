package com.doctor.spring.aop.time20150302;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring in Action, 4th Edition-2015 第4章内容 Aspect-oriented Spring
 * 
 * @author doctor
 *
 * @time 2015年3月2日 下午2:42:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AopConfig.class })
public class AnnotationAopPractice {

	@Autowired
	private Performance performance;

	@Test
	public void test() {
		performance.perform();
	}
}
