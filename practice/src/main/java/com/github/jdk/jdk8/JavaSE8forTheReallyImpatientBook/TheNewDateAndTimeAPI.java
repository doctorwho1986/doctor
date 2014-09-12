package com.github.jdk.jdk8.JavaSE8forTheReallyImpatientBook;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;


public class TheNewDateAndTimeAPI {

	public static void main(String[] args) {
		Instant start = Instant.now();
		for(int i= 0; i <=Integer.MAX_VALUE /2; i++){};
		Instant end = Instant.now();
		long millis = Duration.between(start, end).toMillis();
		System.out.println(millis);
		
		LocalDate now = LocalDate.now();
		now = now.plusYears(5);
		System.out.println(now + " is leap year " + now.isLeapYear());
	}

}
