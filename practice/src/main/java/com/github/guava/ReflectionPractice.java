package com.github.guava;


import org.junit.Assert;
import org.junit.Test;

import com.google.common.reflect.Reflection;

public class ReflectionPractice {

	@Test
	public void test() {
		Assert.assertEquals("com.github.guava", Reflection.getPackageName(ReflectionPractice.class));
		Assert.assertEquals("com.github.guava", Reflection.getPackageName(ReflectionPractice.class.getName()));
		
		Assert.assertEquals(getPackageName(ReflectionPractice.class), Reflection.getPackageName(ReflectionPractice.class));
	}

	private String getPackageName(Class<?> classz){
		String name = classz.getName();
		int lastIndexOf = name.lastIndexOf(".");
		return (-1 == lastIndexOf) ? name : name.substring(0, lastIndexOf);
		
	}
}
