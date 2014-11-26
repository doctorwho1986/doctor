package com.github.jdk8;

@FunctionalInterface
public interface Formula {
	double calculate(int a);
	default double sqrt(int a){
		return Math.sqrt(a);
	}
}
