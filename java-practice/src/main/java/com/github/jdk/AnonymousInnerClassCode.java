package com.github.jdk;

import java.util.HashMap;
import java.util.Map;

/**
 * java 之　匿名内部类
 * mybatis３中动态sql语句的生成用到了．
 * @see http://mybatis.github.io/mybatis-3/statement-builders.html
 * @author doctor
 *
 * @time 2015年3月9日 上午8:56:15
 */
public class AnonymousInnerClassCode {
	public static void main(String[] args) {
		String simpleClass = new SimpleClass(){{
			setName("doctor");
			setAge(2000);
		}}.toString();
		
		System.out.println(simpleClass);
		
		//map匿名内部类
		Map<String, String> map = new HashMap<String, String>(){{
			put("name", "doctor who");
			put("age", "200");
		}};
		
		System.out.println(map);
	}

	private static class SimpleClass{
		private StringBuilder stringBuilder = new StringBuilder();
		
		public SimpleClass setName(String name){
			stringBuilder.append(" name:" + name);
			return this;
		}
		
		public SimpleClass setAge(int age){
			stringBuilder.append(" age:" + age);
			return this;
		}

		@Override
		public String toString() {
			return stringBuilder.toString();
		}
		
		
	}
}
