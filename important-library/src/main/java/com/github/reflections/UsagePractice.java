package com.github.reflections;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

import org.reflections.Reflections;

/**
 * @see https://github.com/ronmamo/reflections
 * @author doctor
 *
 * @time 2015年1月26日 下午3:15:09
 */
public class UsagePractice {

	public static void main(String[] args) {
		// A typical use of Reflections
		Reflections reflections = new Reflections("com.github.reflections");
		Set<Class<? extends Type1>> subTypesOf = reflections.getSubTypesOf(Type1.class);
		for (Class<? extends Type1> class1 : subTypesOf) {
			System.out.println(class1);
		}
		System.out.println("---------------------");

		Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(MyAnn.class);
		for (Class<?> class1 : typesAnnotatedWith) {
			System.out.println(class1);
		}

	}

	private static class Type1 {

	}

	@MyAnn
	private static class Type2 extends Type1 {

	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	private static @interface MyAnn {

	}
}
