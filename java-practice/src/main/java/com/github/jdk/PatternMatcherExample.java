package com.github.jdk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @see http://examples.javacodegeeks.com/core-java/util/regex/pattern-matcher-example/
 * @author doctor
 *
 */
public class PatternMatcherExample {

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("d.*ian");
		Matcher matcher = pattern.matcher("darwinian pterodactyls soared over the devonian space");
		while (matcher.find()) {
			String group = matcher.group();
			System.out.println("group:" + group);
			System.out.println("start:" + matcher.start());
			System.out.println("end:" + matcher.end());
		}
		
		System.out.println("----------------");
		pattern = Pattern.compile("a*b");
		matcher = pattern.matcher("adfjb");
		System.out.println(matcher.find());
	}
	

}
