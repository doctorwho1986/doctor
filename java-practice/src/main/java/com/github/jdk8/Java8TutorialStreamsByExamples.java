package com.github.jdk8;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @see http://howtodoinjava.com/2014/04/13/java-8-tutorial-streams-by-examples/
 * @author doctors
 *
 */
public class Java8TutorialStreamsByExamples {

	public static void main(String[] args) {
		//Different ways to build streams
		 Stream.of(1,2,3,5,6).forEach(System.out::print);
		 
		 System.out.println();
		 Stream.of(new Integer[]{1,2,3,5,6}).forEach(System.out::print);
		 
		 System.out.println();
		 Arrays.asList(1,2,3,5,6,7,8).forEach(System.out::print);
		 
		 System.out.println();
		 Arrays.stream(new int[]{6,8,9}).forEach(System.out::print);
		 
		 System.out.println(); 
		 Stream.generate(()-> LocalDateTime.now()).limit(2).forEach(System.out::println);
		 
		 //Converting streams to collection
		 
	}

}
