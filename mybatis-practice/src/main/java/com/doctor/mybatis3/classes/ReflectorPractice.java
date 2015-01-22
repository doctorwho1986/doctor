package com.doctor.mybatis3.classes;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.invoker.Invoker;

import com.doctor.mybatis3practice.domain.Blog;

/**
 * {@code Reflector} This class represents a cached set of class definition information that
 * allows for easy mapping between property names and getter/setter methods.
 * 
 * {@code Reflector} 类属性和getter/setter 方法的封装（反射方法的封装）
 * @author doctor
 *
 * @time 2015年1月22日 下午1:52:21
 */
public class ReflectorPractice {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		Reflector reflector = Reflector.forClass(Blog.class);
		Reflector.setClassCacheEnabled(false);

		String findPropertyName = reflector.findPropertyName("title");
		System.out.println(findPropertyName);

		Arrays.asList(reflector.getGetablePropertyNames()).forEach(System.out::println);

		Class<?> getterType = reflector.getGetterType("title");
		System.out.println(getterType);

		Invoker getInvoker = reflector.getGetInvoker("title");
		System.out.println(getInvoker);
		Blog blog = new Blog();
		blog.setTitle("title");
		System.out.println(getInvoker.invoke(blog, null));

	}

}
