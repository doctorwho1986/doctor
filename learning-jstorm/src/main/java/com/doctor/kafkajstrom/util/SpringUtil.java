package com.doctor.kafkajstrom.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class SpringUtil {
	private SpringUtil() {
		throw new IllegalAccessError("constructor should not access");
	}
	
	public static ApplicationContext of(String classPathConfigLocation){
		return new ClassPathXmlApplicationContext(classPathConfigLocation);
	}
}
