package com.doctor.embeddedjetty;

import org.springframework.stereotype.Component;

@Component("simpleService")
public class SimpleServiceImpl implements SimpleService {

	@Override
	public String getMessage() {
		return "SimpleServiceImpl";
	}

}
