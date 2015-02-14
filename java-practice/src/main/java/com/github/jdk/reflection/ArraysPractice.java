package com.github.jdk.reflection;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @see http://tutorials.jenkov.com/java-reflection/arrays.html
 * @author doctor
 *
 * @since 2015年2月14日 下午8:49:48
 */
public class ArraysPractice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Creating Arrays 数组默认都初始化为默认值
		String[] instance = (String[]) Array.newInstance(String.class, 8);
		Arrays.stream(instance).forEach(System.out::println);
		
		int[] newInstance = (int[]) Array.newInstance(int.class, 2);
		Arrays.stream(newInstance).forEach(System.out::println);
		
		//Accessing Arrays
		Array.set(newInstance, 0, 88);
		Array.set(newInstance, 1, 888);
		System.out.println(Array.get(newInstance, 0));
		System.out.println(Array.get(newInstance, 1));
		
		//btaining the Component Type of an Array
		Class<? extends int[]> componentClass = newInstance.getClass();
		System.out.println(componentClass);
		System.out.println(componentClass.getComponentType());
		
		System.out.println(instance.getClass().isArray());
		System.out.println(instance.getClass().getComponentType());

	}

}
