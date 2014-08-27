package com.github.jdk.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;



public class CglibProxyPractice {

	public static void main(String[] args) {
		PersonT instance = (PersonT) new CglibProxy().getInstance(new PersonT());
		instance.talk("hello");

	}

}

class PersonTalks{
	public void talk(String message) {
		System.out.println("person talk :" + message);
	}
}

class CglibProxy implements MethodInterceptor{
	private Object targeObject;
	
	public Object getInstance(Object targeObject) {
		this.targeObject = targeObject;
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.targeObject.getClass());
		enhancer.setCallback(this);
		
		return enhancer.create();
		
	}
	@Override
	public Object intercept(Object obj, Method method, Object[] args,MethodProxy proxy) throws Throwable {
		System.out.println("before " + method);
		
		Object invokeSuper = proxy.invokeSuper(obj, args);
		
		System.out.println("after " + method);
		
		return invokeSuper;
	}
}

class PersonT{
	public void talk(String message) {
		System.out.println("person talk: " + message);
	}
}