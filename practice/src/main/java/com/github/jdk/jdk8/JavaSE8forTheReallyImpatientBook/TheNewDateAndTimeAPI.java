package com.github.jdk.jdk8.JavaSE8forTheReallyImpatientBook;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TheNewDateAndTimeAPI {

	public static void main(String[] args) {
		Instant start = Instant.now();
		for (int i = 0; i <= Integer.MAX_VALUE / 2; i++) {};
		Instant end = Instant.now();
		long millis = Duration.between(start, end).toMillis();
		System.out.println(millis);

		LocalDate now = LocalDate.now();
		now = now.plusYears(5);
		System.out.println(now + " is leap year " + now.isLeapYear());
		
		

		/*
		 * Interoperating with Legacy Code As a brand-new creation, the Java
		 * Date and Time API will have to interoperate with existing classes, in
		 * particularicular, the ubiquitous java.util.Date, java.util.
		 * GregorianCalendar, and java.sql.Date/Time/Timestamp.
		 */
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);
		Timestamp timestamp = Timestamp.valueOf(localDateTime);
		System.out.println(timestamp);
		
		LocalDateTime dateTime = timestamp.toLocalDateTime();
		System.out.println(dateTime);
	}

}
