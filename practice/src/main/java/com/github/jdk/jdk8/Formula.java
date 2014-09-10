package com.github.jdk.jdk8;
/**
 * 
 * @author 接口中有默认方法实现 
 *
 */
public interface Formula {
	double add(double a, double b);
	default double sqrt(double a){
		return Math.sqrt(a);
	}
}
