package com.github.jdk8.ebook.java8_recipes2nd_edition;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author doctor
 *
 * @time   2015年2月4日 上午8:46:47
 */
public class Chapter6Code {

	public static void main(String[] args) {
		HelloType helloType = (arg) -> (System.out.println("hello " + arg));
		helloType.hello("doctor");
		
		Function<String, String> function = (value) -> "hello " + value;
		System.out.println(function.apply("doctor"));
		
		BiConsumer<String, String> biConsumer = (a,b)-> System.out.println(String.join(",", a,b));
		biConsumer.accept("BiConsumer hello", "doctor");
		
		BiFunction<String, String, String> biFunction = (a,b) -> String.join(",", a,b);
		System.out.println(biFunction.apply("BiFunction", "hello"));
		
		//BinaryOperator 是一种特殊的BiFunction，参数是相同类型（入参，返回类型）
		BinaryOperator<String> binaryOperator = (a,b)-> String.join(",", a,b);
		System.out.println(binaryOperator.apply("binaryOperator ", "hello"));
		
		BiPredicate<String, String> biPredicate = (a,b)-> a.equals(b);
		System.out.println("BiPredicate:" + biPredicate.test("ab", "cd"));
		
		BooleanSupplier booleanSupplier = () -> true;
		System.out.println("BooleanSupplier:" + booleanSupplier.getAsBoolean());
		
		Consumer<String> consumer = (a)->System.out.println(a);
		consumer.accept("Consumer:" + "hello");
		
		
		
		List<String> collect = Stream.of("c","dca","cddd","abccss","ba","aab").parallel().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
		collect.forEach(System.out::println);

	}

	
	@FunctionalInterface
	public interface HelloType{
		void hello(String message);
	}
}
