package com.doctor.mybatispractice;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.io.ClassLoaderWrapper;
import org.apache.ibatis.io.Resources;
import org.junit.Before;
import org.junit.Test;

public class ClassLoaderWrapperPractice {
	
	private ClassLoaderWrapper newInstance;
	private String resource = "mybatisPracticeConfig/mybatisPractice-mybatis-db.xml";
	@Before
	public void init()throws Throwable{
		Constructor<?> constructor = ClassLoaderWrapper.class.getDeclaredConstructors()[0];
		constructor.setAccessible(true);
		newInstance = ClassLoaderWrapper.class.cast(constructor.newInstance());
	}
	@Test
	public void test_getResourceAsURL() throws Throwable{
		
		URL url = newInstance.getResourceAsURL(resource);
		System.out.println(url.getPath());
	}
	
	@Test
	public void test_getResourceAsStream() throws Throwable{
		InputStream inputStream = newInstance.getResourceAsStream(resource);
		String content = IOUtils.toString(inputStream);
		System.out.println(content);
	}
	
	@Test
	public void test_Resources_getDefaultClassLoader() throws Throwable{
		ClassLoader defaultClassLoader = Resources.getDefaultClassLoader();
		if (defaultClassLoader != null) {
			System.out.println(defaultClassLoader.getClass());
		}
	}
	
	@Test
	public void test_Resources_getResourceURL() throws Throwable{
		URL url = Resources.getResourceURL(resource);
		System.out.println(url);
	}
}
