package com.github.jdk7;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异常信息的丢失 和保持
 * @author doctor
 *
 */
public class Excetion1 {
	private static final Logger log = LoggerFactory.getLogger(Excetion1.class);
	
	@Test(expected=ArithmeticException.class)
	public void test_1(){
		//异常信息丢失案例
		try {
			Integer.parseInt("hello");
		} catch (NumberFormatException e) {
			log.info("{excetion:}",e);
			throw  e; //这个异常被下面的掩盖了。
		}finally{
			int a = 12 / 0; //expected=ArithmeticException.class 这里的异常
		}
	}
	
	@Test(expected=NumberFormatException.class)
	public void test_2(){
		//异常信息丢失案例:解决方法
		try {
			Integer.parseInt("hello");
		} catch (NumberFormatException e) {
			log.info("{excetion:}",e);
			throw e; //这个异常被下面的掩盖了。
		}finally{
			try {
				int a = 12 / 0;
			} catch (Exception e) {
				// 忽略此处异常，上面的主要根源异常就可以跑出去了
			}
		}
	}
	
	
	@Test(expected=RuntimeException.class)
	public void test_3(){
		//异常信息丢失案例:解决方法
		RuntimeException exception = new RuntimeException("error:");
		try {
			
			Integer.parseInt("hello");
		} catch (NumberFormatException e) {
			
			exception.addSuppressed(e); //把这个异常堆栈信息保留一下
			
		}finally{
			try {
				int a = 12 / 0;
			} catch (Exception e) {
				exception.addSuppressed(e);
				log.info("{excetion:}",exception);
				throw exception;
			}
		}
	}
}
