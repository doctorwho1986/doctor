package com.github.jdk;

import org.junit.Assert;

public class SetPropertyPractice {

	public static void main(String[] args) {
		String name = "name";
		String value = "who am i";
		Assert.assertNull(System.getProperty(name));
		System.out.println(System.getProperty(name));
		
		System.setProperty("name", value);
		String property = System.getProperty(name);
		Assert.assertEquals(value, property);
		System.out.println(property);
		
		test(name);
		
		System.setProperty(name, "");
		test(name);
	}
	
	private static void test(String name){
		String property = System.getProperty(name);
		System.out.println("test:" + property);
	}

}
