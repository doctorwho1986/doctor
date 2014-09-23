package com.github.guava;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.reflect.Reflection;

public class ReflectionPractice {
	@Test
	public void testGetPackageName(){
		String packageName = Reflection.getPackageName(ReflectionPractice.class);
		Assert.assertEquals("com.github.guava", packageName);
	}
	
	@Test
	public void  testJavaProxy(){
		Greet proxyInstance = (Greet) new JavaProxy().createProxyInstance(new GreeImpl());
		String greet = proxyInstance.greet();
		Assert.assertEquals("java Proxy", greet);
		System.out.println(greet);
	}
}

interface Greet{
	String greet();
}


class GreeImpl implements Greet{

	@Override
	public String greet() {
		return "java Proxy";
	}
}

class JavaProxy implements InvocationHandler{
	private Object object;

	public  Object createProxyInstance(Object object) {
		this.object = object;
		return Reflection.newProxy(object.getClass().getInterfaces()[0], this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before start" + method.getName());
		Object result = method.invoke(this.object, args);
		System.out.println("after end " + method.getName());
		return result;
	}
	
}