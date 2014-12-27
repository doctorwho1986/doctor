package com.github.jdk8;

public interface Java8Interface1 {
	void method1();

	default void log(String str) {
		System.out.println("Java8Interface1 log " + str);
	}
}
