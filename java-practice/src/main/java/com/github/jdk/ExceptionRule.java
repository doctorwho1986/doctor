package com.github.jdk;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author 日志，自定义抛出异常都必须带者从异常出现代码中的 e
 *
 */
public class ExceptionRule {
	private static final Logger log = LoggerFactory.getLogger(ExceptionRule.class);
	
	@Test
	public void  test1() {
		boolean b = exception1();
		System.out.println(b);
	}

	
	@Test
	public void  test2() {
		boolean b = exception2();
		System.out.println(b);
	}
	private boolean exception1() {
		try {
			int a = 1 / 0;
			return true;
		} catch (Exception e) {
			String error = "error";
			log.error("{error:{}}",error);
			throw new RuntimeException(error);
		} 
	}
	
	
	private boolean exception2() {
		try {
			int a = 1 / 0;
			return true;
		} catch (Exception e) {
			String error = "error";
			log.error("{error:{} ,{}}",error,e);
			throw new RuntimeException(error,e);
		} 
	}
}
