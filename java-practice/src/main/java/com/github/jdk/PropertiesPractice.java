package com.github.jdk;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.junit.Test;

public class PropertiesPractice {

	@Test
	public void test1() {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource("practice.properties");
		try (InputStream inputStream = url.openStream()) {
			properties.load(inputStream);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		properties.forEach((k, v) -> System.out.println(k + ":" + v));
	}

	@Test
	public void test2() {
		Properties properties = new Properties();
		try(InputStream inputStream = PropertiesPractice.class.getClassLoader().getResourceAsStream("practice.properties")){
			properties.load(inputStream);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("////////////test2");
		
		properties.forEach((k,v) -> System.out.println(k + "   :   " + v));
		System.out.println("----------------");
		System.out.println(properties.get("netty-transport.version"));
	}
}
