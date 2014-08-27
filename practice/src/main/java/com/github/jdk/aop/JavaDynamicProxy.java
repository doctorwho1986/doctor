package com.github.jdk.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JavaDynamicProxy {

	public static void main(String[] args) {
		Talk proxyObject = (Talk) new MyDynamicProxy().bind(new PersonTalk());
		proxyObject.talk("hello");
	}

}


class MyDynamicProxy implements InvocationHandler{
	private Object targetObect;
	
	public Object bind(Object targetObect) {
		this.targetObect = targetObect;
		return Proxy.newProxyInstance(targetObect.getClass().getClassLoader(), targetObect.getClass().getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before " + method);
		Object object = method.invoke(this.targetObect, args);
		System.out.println("after " + method);
		return object;
	}
}


interface Talk{
	void talk(String message);
}

class PersonTalk implements Talk{

	@Override
	public void talk(String message) {
		System.out.println("person talk :" + message);
	}
	
}