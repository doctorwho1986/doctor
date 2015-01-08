package com.doctor.spring.context.inject.experiment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Bean1 {

	@Autowired
	@Qualifier("bean2")
	private Bean2 bean2;

	public Bean2 getBean2() {
		return bean2;
	}

	public void setBean2(Bean2 bean2) {
		this.bean2 = bean2;
	}

}
