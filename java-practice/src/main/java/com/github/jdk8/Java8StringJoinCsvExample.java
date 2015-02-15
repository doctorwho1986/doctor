package com.github.jdk8;

import java.time.ZoneId;

public class Java8StringJoinCsvExample {

	public static void main(String[] args) {
		String join = String.join("/", "usr","local","bin");
		System.out.println(join);

		String join2 = String.join(",", ZoneId.getAvailableZoneIds());
		System.out.println(join2);
	}

}
