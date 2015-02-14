package com.github.jdk.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @see http://tutorials.jenkov.com/java-reflection/annotations.html
 * @author doctor
 *
 * @since 2015年2月14日 下午4:36:52
 */
public class AnnotationsPractice {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ReflectiveOperationException {
		MyAnnotation myAnnotation = TheClass.class.getAnnotation(MyAnnotation.class);
		System.out.println(myAnnotation);
		System.out.println("name:" + myAnnotation.name());
		System.out.println("value:" + myAnnotation.value());

		System.out.println("method annotation");
		Method method = TheClass.class.getDeclaredMethod("doSomething");
		MyAnnotation declaredAnnotation = method.getDeclaredAnnotation(MyAnnotation.class);
		System.out.println(declaredAnnotation);
		System.out.println(declaredAnnotation.name());
		System.out.println(declaredAnnotation.value());

		// Parameter Annotations
		System.out.println("----------Parameter Annotations------------");
		Method declaredMethod = TheClass.class.getDeclaredMethod("doSomethingElse",new Class[]{String.class});
		Arrays.stream(declaredMethod.getParameters()).forEach(p->{
			System.out.println("Parameter name:" + p.getName() +", type:" + p.getType());
			if (p.isAnnotationPresent(MyAnnotation.class)) {
				MyAnnotation annotation = p.getAnnotation(MyAnnotation.class);
				System.out.println("Annotation type:" + annotation.annotationType());
				
				Arrays.stream(annotation.annotationType().getDeclaredMethods()).forEach(m->{
					
					try {
						System.out.println("method return type:" + m.getReturnType() + " method name:" + m.getName() + " value:" + m.invoke(annotation));
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
		});
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER })
	private static @interface MyAnnotation {
		String name() default "";

		String value() default "";
	}

	@MyAnnotation(name = "name", value = "doctor who")
	private static class TheClass {

		@MyAnnotation(name = "do", value = "something")
		public void doSomething() {
		}

		public void doSomethingElse(@MyAnnotation(name = "para", value = "ppp") String name) {

		}
	}
}
