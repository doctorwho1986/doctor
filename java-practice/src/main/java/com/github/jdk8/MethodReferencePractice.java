package com.github.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MethodReferencePractice {

	public static void main(String[] args) {
		ArrayList<Integer> arrayList = Stream.of(1,2,3,4,5,6,7).collect(Collectors.toCollection(ArrayList::new));
		arrayList.forEach(System.out::println);
		
		String collect = Stream.of("name","address","sex").parallel().collect(Collectors.joining(",", "[", "]"));
		System.out.println(collect);
		
		Consumer<String> consumer = a -> System.out.println(a);
		//函数要么是Consumer，有输入无输出情况。要么是Function。
		consumer = System.out::println;
		consumer.accept("ss");
		
		BiFunction<String, String, List<String>> biFunction = Arrays::asList;
		List<String> list = biFunction.apply("name", "doctor");
		list.forEach(System.out::println);
	}

}
