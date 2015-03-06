package com.github.jdk8;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.UnsupportedTemporalTypeException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @see http://stackoverflow.com/questions/25229124/format-instant-to-string
 * @author doctor
 *
 * @time 2015年3月6日 上午11:17:54
 */
public class InstantFormat {

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Test
	public void test_wrong_format() {
		Instant now = Instant.now();
		ex.expect(UnsupportedTemporalTypeException.class);
		String format = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss").format(now);
		// java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: YearOfEra
		// at java.time.Instant.getLong(Instant.java:608)
	}

	/**
	 * You can not use DateTimeFormatter to format an instant. Instead you must format it by hand.
	 * One way to do this is to convert the Instant to a LocalDateTime
	 */
	@Test
	public void test_right_format() {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
		String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss"));
		System.out.println(format);
	}

}
