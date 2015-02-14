package com.github.jdk.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @see http://tutorials.jenkov.com/java-reflection/dynamic-proxies.html
 * @see http://www.javacodegeeks.com/2012/08/creating-java-dynamic-proxy.html
 * @author doctor
 *
 * @since 2015年2月14日 下午9:03:10
 */
public class DynamicProxiesPractice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyInterface newInstance = (MyInterface) DynamicProxy.newInstance(new MyClass());
		System.out.println(newInstance.getName());

	}

	public static interface MyInterface {
		String getName();
	}

	public static class MyClass implements MyInterface {

		@Override
		public String getName() {
			return "doctor who";
		}

	}

	public static class DynamicProxy implements InvocationHandler {
		private Object src;

		public static Object newInstance(Object src) {
			return Proxy.newProxyInstance(src.getClass().getClassLoader(), src.getClass().getInterfaces(), new DynamicProxy(src));
		}

		private DynamicProxy(Object src) {
			this.src = src;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("befor " + method);
			Object result = method.invoke(src, args);
			System.out.println("after " + method);
			return result;
		}

	}
}
