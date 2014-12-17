package com.github.jdkcommon;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

/**
 * @author doctor
 *
 * @since 2014年12月17日 下午10:12:13
 */
public class ClassLoaderPractice {

	public static void main(String[] args) throws ReflectiveOperationException {

		ClassLoader classLoader = ClassLoaderPractice.class.getClassLoader();
		System.out.println(classLoader);
		Class<?> loadClass = classLoader.loadClass("java.lang.String");
		Object instance = loadClass.newInstance();
		assertThat(instance.getClass().getName(), equalTo("java.lang.String"));
		System.out.println(instance.getClass());
		
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		assertEquals(classLoader, systemClassLoader);
		System.out.println(systemClassLoader);
		
		ClassLoader loader = classLoader;
		System.out.println("--");
		while (loader != null ) {
			System.out.println(loader);
			loader = loader.getParent();
		}
	}

}
