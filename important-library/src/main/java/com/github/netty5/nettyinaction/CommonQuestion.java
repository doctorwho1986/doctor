package com.github.netty5.nettyinaction;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author doctorwho
 * compareTo 都不能为null。
 * equals 前者不能为null，所以，常量比较放前面
 */
public class CommonQuestion {

	@Test
	public void Comparable() {
		String a = "ss";
		Assert.assertEquals(0, a.compareTo("ss"));
	}

	@Test(expected=NullPointerException.class)
	public void Comparable_null() {
		String a = null;
		a.compareTo("ss");
		
	}
	
	@Test(expected=NullPointerException.class)
	public void Comparable_null_1() {
		String a = "ss";
		a.compareTo(null);
	}
	
	@Test
	public void equal_null(){
		String s = "ss";
		Assert.assertFalse(s.equals(null));
		Assert.assertTrue(s.equals("ss"));
	}
	
	@Test(expected=NullPointerException.class)
	public void equal_null_1(){
		String s = null;
		Assert.assertFalse(s.equals("ss"));
		
	}
}
