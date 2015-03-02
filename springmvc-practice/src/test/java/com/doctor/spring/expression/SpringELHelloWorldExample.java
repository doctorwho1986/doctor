package com.doctor.spring.expression;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @see http://www.mkyong.com/spring3/spring-el-hello-world-example/
 * @author doctor
 *
 * @time 2015年3月2日 上午10:58:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringELHelloWorldExampleConfig.class })
public class SpringELHelloWorldExample {

	@Autowired
	private Customer customer;

	@Autowired
	private Item item;
	
	@Test
	public void test(){
		System.out.println(item);
		System.out.println(customer);
	}

}
