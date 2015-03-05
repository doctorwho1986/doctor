package com.doctor.blogcode.jdk.common;

import java.util.Random;

import org.junit.Test;

/**
 * @see http://www.baeldung.com/java-generate-random-long-float-integer-double
 * @author doctor
 *
 * @time 2015年3月5日 上午9:40:27
 */
public class RandomLongFloatIntegerAndDouble {

	@Test
	public void test_givenUsingPlainJava_whenGeneratingRandomLongUnbounded_thenCorrect(){
		long nextLong = new Random().nextLong();
		System.out.println(nextLong);
	}
	
	@Test
	public void test_givenUsingPlainJava_whenGeneratingRandomLongBounded_thenCorrect(){
		long min = 11;
		long max = 36;
		long random = min + (long)(Math.random()*(max - min));
		System.out.println(random);
	}
}
