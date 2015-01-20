package com.doctor.elasticsearch_1_4_2.classes;

import java.lang.reflect.Modifier;

import org.elasticsearch.common.Classes;


public class ClassesPractice {

	public static void main(String[] args) {
		ClassLoader defaultClassLoader = Classes.getDefaultClassLoader();
		System.out.println(defaultClassLoader);
		
		System.out.println(Thread.currentThread().getContextClassLoader());
		
		System.out.println(ClassesPractice.class.getClassLoader());
		
		System.out.println(Classes.getPackageName(ClassesPractice.class));
		System.out.println(ClassesPractice.class.getPackage().getName());
		System.out.println(ClassesPractice.class.getName());
		
		System.out.println(Classes.isInnerClass(ClassesPractice.class));
		
		System.out.println(Classes.isInnerClass(InnerClass.class));
		
		System.out.println("getEnclosingClass:" + StaticInnerClass.class.getEnclosingClass());
		System.out.println("getEnclosingClass:" + ClassesPractice.class.getEnclosingClass());
		System.out.println(Modifier.isFinal(StaticInnerClass.class.getModifiers()));
		System.out.println(Modifier.isStatic(StaticInnerClass.class.getModifiers()));
	}

	private  class InnerClass{
		
	}
	
	private static class StaticInnerClass{
		
	}
}
