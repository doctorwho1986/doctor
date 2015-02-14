package com.github.jdk.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @see http://tutorials.jenkov.com/java-reflection/fields.html
 * @author doctor
 *
 * @since 2015年2月14日 下午3:51:38
 */
public class FieldsPractice {

	/**
	 * @param args
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		System.out.println("---getFields----");
		Arrays.stream(FieldsClass.class.getFields()).forEach(System.out::println);
		
		System.out.println("--getDeclaredFields---");
		Arrays.stream(FieldsClass.class.getDeclaredFields()).forEach(System.out::println);
		
		System.out.println("--子类　getDeclaredFields---");
		Arrays.stream(FieldChidren.class.getDeclaredFields()).forEach(System.out::println);
		
		//Field Name Field Type
		Arrays.stream(FieldsClass.class.getDeclaredFields()).forEach(f->{
			System.out.println("Field Name:" + f.getName() +" Field Type:" + f.getType() );
		});
		
		Field field = FieldsClass.class.getDeclaredField("name");
		FieldsClass fieldsClass = new FieldsClass();
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		System.out.println(field.get(fieldsClass));
		field.set(fieldsClass, "doctor who");
		System.out.println(field.get(fieldsClass));
	}
	
	private static class FieldsClass{
		private String name = "doctor";
		private String sex;
		private byte age;
	}

	private static class FieldChidren extends FieldsClass{
		
	}
}
