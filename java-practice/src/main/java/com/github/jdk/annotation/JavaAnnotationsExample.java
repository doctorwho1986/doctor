package com.github.jdk.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * java流行的框架中spring,mybatis都利用了注解，mybatis在方法及方法的参数中都利用了注解．他们是如何工作的呢
 * 方法注解获取注解值和方法参数注解获取注解值
 * @author doctor
 *
 */
public class JavaAnnotationsExample {

	public static void main(String[] args) throws ReflectiveOperationException {
		Method[] methods = JavaAnnotationClass.class.getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(JavaAnnotationsEx.class)) {
				System.out.println("method:"+ method);
				
				//获得一个注解实例。这是
				JavaAnnotationsEx annotation = method.getAnnotation(JavaAnnotationsEx.class);
				System.out.println("JavaAnnotationsEx:" + annotation);
				
				//获得此注解实例的注解类型（Returns the annotation type of this annotation），和一般类获取class不同的地发
				//反射获得它的方法
				Method[] declaredMethods = annotation.annotationType().getDeclaredMethods();
				for (Method method2 : declaredMethods) {
					System.out.println(method2);
					System.out.println("method name:" + method2.getName() + "--value:" + method2.invoke(annotation));
				}
			
			}
			
			 
		}
		
		
		System.out.println("方法参数注解获取值");
		
		for (Method method : methods) {
			//方法获取参数注解示例。二维数组
			Annotation[][] parameterAnnotations = method.getParameterAnnotations();
			for (Annotation[] annotations : parameterAnnotations) {
				for (Annotation annotation : annotations) {
					if (annotation instanceof JavaAnnotationsEx) {
						JavaAnnotationsEx a = (JavaAnnotationsEx) annotation;
						System.out.println("注解是：" + a);
						System.out.println("直接调用注解的方法，获取注解值，");
						System.out.println("age:" + a.age());
						System.out.println("name:" + a.name());
						
						System.out.println("反射获取参数注解的值：");
						Method[] methodsA = a.annotationType().getDeclaredMethods();
						for (Method m : methodsA) {
							System.out.println("method name:" + m.getName() + "--value:"+ m.invoke(a));
						}
						
					}
				}
			}
		}

	}
	
	@Target({ElementType.METHOD,ElementType.PARAMETER})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface JavaAnnotationsEx{
		String name()default "";
		int age() default 0;
	}
	
	public static class JavaAnnotationClass{
		
		@JavaAnnotationsEx(name="doctor",age=666666)
		public String method(@JavaAnnotationsEx(name="who") String name){
			return name;
		}
	}

}
