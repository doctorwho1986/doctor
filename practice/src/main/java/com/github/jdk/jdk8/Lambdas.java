package com.github.jdk.jdk8;

import java.util.Comparator;


/**
 * 
 * @author see
 *         http://www.oracle.com/technetwork/articles/java/architect-lambdas-
 *         part1-2080972.html
 *
 */
public class Lambdas {

	public static void main(String[] args) {

		System.out.println(Thread.currentThread().getName());
		Listing3();

		Syntax();

	}

	/**
	 * Lambda expressions. Funda-men-tally, a lambda expression is just a
	 * shorter way of writing an implementation of a method for later execution.
	 * Thus, while we used to define a Runnable as shown in Listing 2, which
	 * uses the anonymous inner class syntax and clearly suffers from a
	 * “vertical problem” (meaning that the code takes too many lines to express
	 * the basic concept), the Java 8 lambda syntax allows us to write the code
	 * as shown in Listing 3.
	 */
	public static void Listing3() {
		Runnable runnable = () -> {
			System.out.println(Thread.currentThread().getName());
		};

		new Thread(runnable).start();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName());
		}).start();
	}

	/*
	 * Syntax. A lambda in Java essentially consists of three parts: a
	 * parenthesized set of parameters, an arrow, and then a body, which can
	 * either be a single expression or a block of Java code. In the case of the
	 * example shown in Listing 2, run takes no parameters and returns void, so
	 * there are no parameters and no return value. A Comparator<T>-based
	 * example, however, highlights this syntax a little more obviously, as
	 * shown in Listing 4. Remember that Comparator takes two strings and
	 * returns an integer whose value is negative (for “less than”), positive
	 * (for “greater than”), and zero (for “equal”).
	 */
	public static void Syntax() {
		Comparator<String> comparator = (a, b) -> a.compareTo(b);
		System.out.println("ab abc" + comparator.compare("ab", "abc"));

		
		/*
		 * If the body of the lambda requires more than one expression, the
		 * value returned from the expression can be handed back via the return
		 * keyword, just as with any block of Java code (see Listing 5).
		 */
		Comparator<Integer> comparator2 = (a, b) -> {
			return a.compareTo(b);
		};

		System.out.println("555 66 " + comparator2.compare(555, 66));
	
		
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("djkjf").append("xxx");
		Runnable r = () ->System.out.println(stringBuffer);
		r.run();
		stringBuffer.append("rrrrr");
		r.run();
		
	}

}
