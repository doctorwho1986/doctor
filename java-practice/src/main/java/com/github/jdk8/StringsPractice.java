package com.github.jdk8;

import java.util.Arrays;
import java.util.Objects;

public class StringsPractice {

	public static void main(String[] args) {
		String join = String.join(",", "name","sex","address");
		System.out.println(join);
		
		System.out.println(Objects.isNull(null));
		System.out.println(Objects.equals("a", new String("a")));
		System.out.println(Objects.deepEquals("a", new String("a")));
		System.out.println(Objects.deepEquals(Arrays.asList("a","b"), Arrays.asList("a","c")));
		System.out.println(Objects.hashCode("a"));
		System.out.println(Objects.hash("a",34));
		System.out.println(Objects.toString(5));
		System.out.println(Objects.nonNull(null));

	}

}
