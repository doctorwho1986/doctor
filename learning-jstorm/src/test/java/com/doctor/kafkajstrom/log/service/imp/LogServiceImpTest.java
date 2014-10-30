package com.doctor.kafkajstrom.log.service.imp;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.doctor.kafkajstrom.log.service.LogService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/learningJstormConfig/dubbo-consumer.xml")
public class LogServiceImpTest {

	@Autowired
	private  LogService logService;
	
	@Test
	public void testGetCount() {
		System.out.println(logService.getCount("dd"));
	}

}
