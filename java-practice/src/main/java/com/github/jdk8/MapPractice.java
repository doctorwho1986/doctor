package com.github.jdk8;

import java.util.IntSummaryStatistics;
import java.util.stream.Stream;

public class MapPractice {

	public static void main(String[] args) {
		IntSummaryStatistics summaryStatistics = Stream.of(1,12,33,66,88).parallel().mapToInt(t -> t).summaryStatistics();
		System.out.println(summaryStatistics.getAverage());
		System.out.println(summaryStatistics.getCount());
		System.out.println(summaryStatistics.getMax());
		System.out.println(summaryStatistics.getMin());
		System.out.println(summaryStatistics.getSum());

	}

}
