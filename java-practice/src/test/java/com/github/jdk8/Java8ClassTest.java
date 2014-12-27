package com.github.jdk8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author doctor
 *
 * @since 2014年12月27日 上午11:27:10
 */
public class Java8ClassTest {
	private Java8Class java8Class ;
	
	
	@Before
	public void init(){
		java8Class = new Java8Class();
	}
	
	/**
	 * Test method for {@link com.github.jdk8.Java8Class#method2()}.
	 */
	@Test
	public void testMethod2() {
		java8Class.method1();
	}

	/**
	 * Test method for {@link com.github.jdk8.Java8Class#method1()}.
	 */
	@Test
	public void testMethod1() {
		java8Class.method2();
	}

	/**
	 * Test method for {@link com.github.jdk8.Java8Class#log(java.lang.String)}.
	 */
	@Test
	public void testLog() {
		java8Class.log("hello doctor");
		
	}

}
