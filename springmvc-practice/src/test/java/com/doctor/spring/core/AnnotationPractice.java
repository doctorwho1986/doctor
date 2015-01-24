package com.doctor.spring.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * 自定义Annotation，得到注解类中Annotation设定的注解值 即：遍历自定义Annotation中的方法，反射执行方法，结果就是
 * 对应的注解值。
 * 
 * 对应复杂注解的处理参看：{@code AnnotationUtils}
 * 
 * @author doctor
 *
 * @time 2015年1月23日 下午6:15:31
 */

public class AnnotationPractice {

	public static void main(String[] args) throws ReflectiveOperationException {
		MyAnnotation myAnnotation = AnnotationP.class.getAnnotation(MyAnnotation.class);
		System.out.println(myAnnotation);
		// 输出：
		// @com.doctor.spring.core.AnnotationPractice$MyAnnotation(value=AnnotationP,
		// num=12, address=[1, 2])

		for (Method method : myAnnotation.annotationType().getDeclaredMethods()) {
			if (!method.isAccessible()) {
				method.setAccessible(true);
			}
			Object invoke = method.invoke(myAnnotation);
			System.out.println("invoke methd " + method.getName() + " result:" + invoke);
			if (invoke.getClass().isArray()) {
				Object[] temp = (Object[]) invoke;
				for (Object o : temp) {
					System.out.println(o);
				}
			}
		}
		// 输出：
		// invoke methd num result:12
		// invoke methd value result:AnnotationP
		// invoke methd address result:[Ljava.lang.String;@74a14482
		// 1
		// 2

	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@Documented
	private @interface MyAnnotation {
		String value() default "";

		int num() default 100;

		String[] address() default {};
	}

	@MyAnnotation(value = "AnnotationP", num = 12, address = { "1", "2" })
	private static class AnnotationP {

	}
}
