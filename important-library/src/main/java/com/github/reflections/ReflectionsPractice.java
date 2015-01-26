package com.github.reflections;

import java.util.Set;

import org.reflections.Reflections;

public class ReflectionsPractice {

	public static void main(String[] args) {
		Reflections reflections = new Reflections("com.github.reflections");
		Set<String> allTypes = reflections.getAllTypes();
		for (String string : allTypes) {
			System.out.println(string);
		}

	}

}
