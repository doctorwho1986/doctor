package com.doctor.spring.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.MultiValueMap;

public class AnnotatedElementUtilsPractice {

	public static void main(String[] args) throws ReflectiveOperationException {
		MultiValueMap<String, Object> allAnnotationAttributes = AnnotatedElementUtils.getAllAnnotationAttributes(PerAnn.class, MyAnnotation.class.getName());
		for (Entry<String, List<Object>> entry : allAnnotationAttributes.entrySet()) {
			System.out.println(entry.getKey());
			for (Object object : entry.getValue()) {
				System.out.println(object);
			}
		}
		
		MyAnnotation myAnnotation = PerAnn.class.getAnnotation(MyAnnotation.class);
		System.out.println(myAnnotation);
		for (Method method : myAnnotation.annotationType().getDeclaredMethods()) {
			method.setAccessible(true);
			Object invoke = method.invoke(myAnnotation);
			System.out.println("invoke method :" + method + " result: " + invoke);
		}
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@Documented
	public @interface MyAnnotation {
		String value() default "";
	}

	@MyAnnotation("my")
	public static class PerAnn {

	}
}
