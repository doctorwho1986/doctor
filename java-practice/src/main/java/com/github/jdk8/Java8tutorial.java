package com.github.jdk8;

import java.util.function.Predicate;

/**
 * 
 * @author doctor 
 *  @see http://winterbe.com/posts/2014/03/16/java-8-tutorial/
 *
 * @time   2014年11月26日 下午3:34:16
 */
public class Java8tutorial {

	public static void main(String[] args) {
		Formula formula = new Formula() {
			
			@Override
			public double calculate(int a) {
				return sqrt(a) * 100D;
			}
		};
		
		double calculate = formula.calculate(1);
		System.out.println(calculate);
		System.out.println(formula.calculate(25));
		formula = (a)-> a * 1.1D;
		System.out.println(formula.calculate(1));
		System.out.println(formula.calculate(25));
		
		/*
		 * Predicates are boolean-valued functions of one argument. 
		 * The interface contains various default methods for composing predicates 
		 * to complex logical terms (and, or, negate)
		 */
		Predicate<String> predicateNotEmpty = (string) -> (string!= null && string.trim().length() > 1);
		System.out.println(predicateNotEmpty.test(""));
		System.out.println(predicateNotEmpty.test("aa"));
		System.out.println(predicateNotEmpty.test(null));

	}

}
