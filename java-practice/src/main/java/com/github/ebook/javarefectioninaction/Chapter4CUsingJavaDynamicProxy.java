package com.github.ebook.javarefectioninaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Chapter4CUsingJavaDynamicProxy {

	public static void main(String[] args) {
		Iperson proxyInstancePerson = (Iperson) new ProxyIperson(new Man()).createProxyInstance();
		proxyInstancePerson.greet();
		
		proxyInstancePerson = (Iperson) new ProxyIperson(new Woman()).createProxyInstance();
		proxyInstancePerson.greet();
	}
}

interface Iperson{
	void greet();
}

class Man implements Iperson{

	@Override
	public void greet() {
		System.out.println("hello ,I'm a man");
	}
}

class Woman implements Iperson{

	@Override
	public void greet() {
		System.out.println("hello, I'm a woman");
	}
	
}

class ProxyIperson implements InvocationHandler{
	private Object target;
	
	public ProxyIperson(Object target){
		this.target = target;
	}
	
	public  Object createProxyInstance() {
		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before method :" + method);
		Object invoke = method.invoke(this.target, args);
		System.out.println("after method :" + method);

		return invoke;
	}
	
}