package com.github.jdk8;

/**
 * @see http://howtodoinjava.com/2014/05/04/java-8-exact-airthmetic-operations-supported-in-math-class/
 * @author doctor
 *
 */
public class Java8ExactAirthmeticOperationsSupportedInMathClass {

	public static void main(String[] args) {
		// (add|substract|multiply|increment|decrement|negate)Exact methods
		// The Math class provides these new methods which throws <code>"java.lang.ArithmeticException"</code> exception every-time
		// the result of operation overflows to max limit. All above methods takes arguments as either int or long primitives.

		System.out.println(100000 * 100000);
		try {
			System.out.println(Math.multiplyExact(100000, 100000));
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println(Integer.MAX_VALUE + Integer.MAX_VALUE);
		try {
			System.out.println(Math.addExact(Integer.MAX_VALUE, Integer.MAX_VALUE));
		} catch (Exception e) {
			System.out.println(e);
		}

		// floorMod and floorDiv methods
		System.out.println(10 % 2);
		System.out.println(-10 % 2);
		System.out.println(11 % 2);
		System.out.println(-11 % 2);
		System.out.println(Math.floorMod(-11, 2));

		// toIntExact method
		System.out.println("\n long to int : ");
		System.out.println((int) Long.MAX_VALUE);
		try {
			System.out.println(Math.toIntExact(Long.MAX_VALUE));
		} catch (Exception e) {
			System.out.println(e);
		}

		// <h3>4) nextDown method</h3>
		// <p>This is also a noticeable new addition in java 8 kit. 
		//This is very helpful when you have a situation where you want to return a number less than n. 
		//And the number you computed for returning happens to be exactly n. 
		//Then you can use this method to find a number closest to n, still less than n
		System.out.println(Math.nextDown(123.56D));
		System.out.println(Math.nextDown(123.56F));
	}
}
