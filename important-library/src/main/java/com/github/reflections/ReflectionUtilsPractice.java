package com.github.reflections;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Set;

import org.reflections.ReflectionUtils;

/**
 * 关于{@code Class#forName(String)} 方法
 * 这个方法总是返回要加载的类的Class类的实例
 * 1、forName(String className)单参数时, initialize=true
 * a.总是使用当前类装载器(也就是装载执行forName()请求的类 的类装载器)
 * b.总是初始化这个被装载的类(当然也包括：装载、连接、初始化)
 * 
 * 2、Class.forName(String className, boolean initialize, ClassLoader loader)
 * a.loader指定装载参数类所用的类装载器，如果null则用bootstrp装载器。
 * b.initialize=true时，肯定连接，而且初始化了；
 * c.false时，绝对不会初始化，但是可能被连接了，但是这里有个例外，如果在调用这个forName()前，已经被初始化了(当然，这里也暗含着：className是被同一个loader所装载的，即被参数中的loader所装载的，而且这个类被初始化了)，那么返回的类型也肯定是被初始化的
 * 
 * 关于用户自定义的类装载器的loadClass()方法
 * 1、loadClass(String name)单参数时, resolve=false
 * a.如果这个类已经被这个类装载器所装载，那么，返回这个已经被装载的类型的Class的实例，否则，就用这个自定义的类装载器来装载这个class，这时不知道是否被连接。绝对不会被初始化
 * b.这时唯一可以保证的是，这个类被装载了。但是不知道这个类是不是被连接和初始化了
 * 2、loadClass(String name, boolean resolve)
 * a.resolve=true时，则保证已经装载，而且已经连接了。resolve=falses时，则仅仅是去装载这个类，不关心是否连接了，所以此时可能被连接了，也可能没有被连接
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 另外：这里所谓的“初始化”是指类的初始化，即执行了className字节码的<clinit>方法
 * 
 * 
 * 
 * 再者：类是这么加载的
 * 
 * 1、装载
 * 
 * 2、连接
 * 
 * a)验证-->检查类格式等
 * 
 * b)准备-->给类变量分配内存，并根据类变量类型设置默认值（即内存中置0）
 * 
 * c)解析-->常量池解析
 * 
 * @author doctor
 *
 * @time 2015年1月26日 下午3:44:13
 */
public class ReflectionUtilsPractice {

	public static void main(String[] args) {
		Class<?> forName = ReflectionUtils.forName("com.github.reflections.ReflectionUtilsPractice");
		System.out.println(forName);
		
		Set<Constructor> constructors = ReflectionUtils.getConstructors(ReflectionUtilsPractice.class);
		for (Constructor constructor : constructors) {
			System.out.println(constructor);
		}

	}

}
