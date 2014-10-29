package com.github.jdk7;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Practice20141029 {
	@Rule
	public  ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void test_1(){
		String string = "hello";
		switch (string) {
		case "hello":
			System.out.println(string);
			break;

		default:
			throw new IllegalArgumentException("非法参数");
		}
	}

	@Test
	public void test_2(){
		String string = "";
		expectedException.expect(IllegalArgumentException.class);
		switch (string) {
		case "hello":
			System.out.println(string);
			break;

		default:
			throw new IllegalArgumentException("非法参数");
		}
	}
	
	
	@Test
	public void test_3(){
		String string = null;
		expectedException.expect(NullPointerException.class);
		switch (string) {
		case "hello":
			System.out.println(string);
			break;

		default:
			throw new IllegalArgumentException("非法参数");
		}
	}
	
	@Test
	public void test_path(){
		Path path = Paths.get(Practice20141029.class.getResource("/").toString());
		System.out.println(path);
	}
}
