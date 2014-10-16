package com.github.jdk;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

public class SetPropertyPractice {

	public static void main(String[] args) {
		String name = "name";
		String value = "who am i";
		Assert.assertNull(System.getProperty(name));
		System.out.println(System.getProperty(name));

		System.setProperty("name", value);
		String property = System.getProperty(name);
		Assert.assertEquals(value, property);
		System.out.println(property);

		test(name);

		System.setProperty(name, "");
		test(name);
		
		System.out.println("test lodad");
		test_load_property();
	}

	private static void test(String name) {
		String property = System.getProperty(name);
		System.out.println("test:" + property);
	}

	private static void test_load_property() {
		try (InputStream inputStream = SetPropertyPractice.class.getResourceAsStream("/javaPracticeProp/propertyPractice.properties")) {
			Properties properties = new Properties();
			properties.load(inputStream);
			System.out.println(properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
