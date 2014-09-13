/*
 * Copyright (C) 2014 The doctor Authors
 * https://github.com/doctorwho1986
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jdk.jdk8.java8LambdasBook;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

import org.junit.Assert;

/**
 * @author doctor
 *
 * @date 2014年9月14日 上午12:18:48
 */
public class LambdaExpressions2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runnable noArguments = () -> System.out.println("noArguments");
		noArguments.run();

		ActionListener oneArgument = envet -> System.out.println("oneArgument");

		Runnable multiStatement = () -> {
			System.out.println("multiStatement 1");
			System.out.println("multiStatement 2");
		};
		multiStatement.run();

		BinaryOperator<Long> binaryOperator = (a, b) -> Long.sum(a, b);
		Long a = 12L, b = 13L;
		Assert.assertEquals(25L, binaryOperator.apply(a, b).longValue());

		BinaryOperator<Long> addExplicit = (Long at, Long bt) -> Long.sum(at, bt);
		Assert.assertEquals(25L, addExplicit.apply(a, b).longValue());

		/*
		 * Functional Interfaces
		 *  A functional interface is an interface with a
		 * single abstract method that is used as the type of a lambda
		 * expression. 
		 * So it’s a functional interface. It doesn’t matter what
		 * the single method on the interface is called—it’ll get matched up to
		 * your lambda expression as long as it has a compatible method
		 * signature. Functional interfaces also let us give a useful name to
		 * the type of the parameter—something that can help us understand what
		 * it’s used for and aid readability.
		 */
		
		//Type Inference

		Predicate<Integer> predicate1 = x -> x > 5;
		Assert.assertTrue(predicate1.test(13));
		Assert.assertFalse(predicate1.test(3));
		
		// new
		/*
		 * ThreadLocal lambda expressions. Java has a class called ThreadLocal
		 * that acts as a container for a value that’s local to your current
		 * thread. In Java 8 there is a new factory method for ThreadLocal that
		 * takes a lambda expression, letting you create a new ThreadLocal
		 * without the syntactic burden of subclassing. a. Find the method in
		 * Javadoc or using your IDE. b. The Java DateFormatter class isn’t
		 * thread-safe. Use the constructor to create a thread-safe
		 * DateFormatter instance that prints dates like this: “01-Jan-1970”.
		 */
		ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MMM-yyyy") );
		System.out.println(threadLocal.get().format(Date.from(Instant.now())));
	}

}
