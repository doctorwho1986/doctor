package com.doctor.spring.core;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;

/**
 * 反射得到注解值 
 * 定义注解，就是定义了一个java　类型，像其它自定义类一样，
 * 每使用注解一次，就实例化了一个注解类的对象．然后反射可操作该对象，获得注解值．
 * 
 * @author doctor
 *
 * @since 2015年1月24日 下午8:31:04
 */
public class AnnotationUtilsPractice {

	public static void main(String[] args) throws ReflectiveOperationException {
		Annotation[] annotations = AnnotationUtils.getAnnotations(AnnotationClass.class);
		for (Annotation annotation : annotations) {
			System.out.println(annotation);
			// @com.doctor.spring.core.AnnotationUtilsPractice$MyAn(value=doctor)
		}

		MyAn annotation = AnnotationUtils.getAnnotation(AnnotationClass.class, MyAn.class);
		System.out.println(annotation);
		// @com.doctor.spring.core.AnnotationUtilsPractice$MyAn(value=doctor)
		Object value = AnnotationUtils.getValue(annotation, "value");
		System.out.println(value);// doctor

		// 反射得到注解值
		// 定义注解，就是定义了一个java　类型，像其它自定义类一样，
		// 每使用注解一次，就实例化了一个注解类的对象．然后反射可操作该对象，获得注解值．
		Method method = annotation.annotationType().getDeclaredMethod("value");
		method.setAccessible(true);
		Object invoke = method.invoke(annotation);
		System.out.println(invoke);// doctor
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@Documented
	private static @interface MyAn {
		String value() default "";
	}

	@MyAn("doctor")
	private static class AnnotationClass {

	}

}
