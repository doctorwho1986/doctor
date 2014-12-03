package com.github.jdk;

import java.lang.reflect.Array;

import com.google.common.base.Preconditions;

public class ReflectionArraysPractice {

	public static void main(String[] args) {
		String[] newInstance = (String[]) Array.newInstance(String.class, 3);
		System.out.println(newInstance.getClass());
		
		Array.set(newInstance, 0, "name");
		System.out.println(newInstance[0]);
		System.out.println(Array.get(newInstance, 0));
		Preconditions.checkArgument("name".equals(Array.get(newInstance, 0)));
		 
		System.out.println(newInstance.getClass().isArray());
		Preconditions.checkArgument(newInstance.getClass().isArray());
		System.out.println(newInstance.getClass().getComponentType());
		Preconditions.checkArgument(String.class.equals(newInstance.getClass().getComponentType()));
		
		System.out.println(String.class.getComponentType());
		Preconditions.checkArgument(!String.class.isArray());
		Preconditions.checkArgument(null == String.class.getComponentType() );

	}

}
