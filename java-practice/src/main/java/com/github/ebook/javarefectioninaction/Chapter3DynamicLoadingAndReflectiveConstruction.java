package com.github.ebook.javarefectioninaction;


import org.junit.Assert;
import org.junit.Test;

public class Chapter3DynamicLoadingAndReflectiveConstruction {

	@Test
	public void test_classForName() throws ReflectiveOperationException{
		Class<?> forName = Class.forName(String[].class.getName());
		
		Assert.assertTrue(forName.isArray());
		Assert.assertEquals(String.class,forName.getComponentType());
		
		Class<?> forName2 = Class.forName(String.class.getName());
	}
}
