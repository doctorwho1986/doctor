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
		//保存要代理的原始对象
		this.targetObject = targetObject;
		
		//生成代理对象
		return Proxy.newProxyInstance(this.targetObject.getClass().getClassLoader(),
				this.targetObject.getClass().getInterfaces(), this);
		
	}

	//代理对象调用方法时候，  会  要 调用到的代理的方法
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before " + method);
		
		//原对象方法调用
		Object object = method.invoke(this.targetObject, args);
		System.out.println("after " + method);
		return object;
	}
	
}