package com.doctor.slf4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 如何打印异常堆栈信息。
 * @author doctor
 *
 * @time   2014年12月11日 上午9:49:00
 */
public class LogThrowableRule {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 这种e.toString()方法只是得到异常信息，异常堆栈没有得到
	 */
	@Test
	public void test_wrongLogThrowable(){
		try {
			int test = 10/0;
		} catch (Exception e) {
			log.error("error" + e);
			//main  ERROR c.d.s.LogThrowableRule -
			//                         errorjava.lang.ArithmeticException: / by zero
		}
	}
	
	/**
	 * 得到异常堆栈信息正确的log方法
	 */
	@Test
	public void test_rightWayGetInfoForThrowable(){
		int a = 10;
		int b = 0;
		try {
			
			int test = a/b;
		} catch (Exception e) {
			log.error("error" ,e);
			//main  ERROR c.d.s.LogThrowableRule -
			//			error
			//java.lang.ArithmeticException: / by zero
			//          at com.doctor.slf4j.LogThrowableRule.test_rightWayGetInfoForThrowable(LogThrowableRule.java:35) ~[classes/:na]
			//          at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.0_11]
			//          .............
			
			String msg = String.format("{error:'%s/%s'}", a,b);//string带参数，利用String.format
			log.error(msg, e);
		}
	}
}
