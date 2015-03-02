package com.doctor.spring.aop.time20150302;

import org.springframework.stereotype.Component;

@Component
public class PerformanceImp implements Performance {

	@Override
	public void perform() {
		System.out.println(PerformanceImp.class + " : call perform()");

	}

}
