package com.github.jdk8;

public interface Java8Interface2 {
	void method2();

	default void log(String str) {
		System.out.println("Java8Interface2 log " + str);
	}
}
