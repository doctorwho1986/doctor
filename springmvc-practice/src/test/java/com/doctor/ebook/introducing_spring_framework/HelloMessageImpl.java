package com.doctor.ebook.introducing_spring_framework;

import org.springframework.stereotype.Service;

/**
 * @author doctor
 *
 * 2014年12月1日 下午9:27:48
 */
@Service("helloMessage")
public class HelloMessageImpl implements IHelloMessage {

	@Override
	public String hello() {
		return "hello doctor";
	}

}
