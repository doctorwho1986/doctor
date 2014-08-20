package com.github.jdk.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * @author doctor
 * java 动态代理
 *
 */
public class JavaAop {

	public static void main(String[] args) {
		JavaProxy proxy = new JavaProxy();
		UserService proxyObject = (UserService) proxy.createProxyObject(new UserServiceImpl());
		proxyObject.addUser();
		proxyObject.deleteUser();
	}

}

interface UserService{
	public boolean addUser();
	public boolean deleteUser();
}

class UserServiceImpl implements UserService{

	@Override
	public boolean addUser() {
		System.out.println("addUser");
		return true;
	}

	@Override
	public boolean deleteUser() {
		System.out.println("deleteUser");
		return true;
	}
	
}

class JavaProxy implements InvocationHandler{
	private Object targetObject;
	
	public Object createProxyObject(Object targetObject) {
		this.targetObject = targetObject;
		
		return Proxy.newProxyInstance(this.targetObject.getClass().getClassLoader(),
				this.targetObject.getClass().getInterfaces(), this);
		
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before " + method);
		Object object = method.invoke(this.targetObject, args);
		System.out.println("after " + method);
		return object;
	}
	
}