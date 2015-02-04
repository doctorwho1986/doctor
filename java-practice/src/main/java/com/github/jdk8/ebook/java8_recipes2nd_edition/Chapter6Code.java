package com.github.jdk8.ebook.java8_recipes2nd_edition;

import java.util.Arrays;
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
 * @time 2015年2月4日 上午8:46:47
 */
public class Chapter6Code {

	public static void main(String[] args) {
		HelloType helloType = (arg) -> (System.out.println("hello " + arg));
		helloType.hello("doctor");

		Function<String, String> function = (value) -> "hello " + value;
		System.out.println(function.apply("doctor"));

		BiConsumer<String, String> biConsumer = (a, b) -> System.out.println(String.join(",", a, b));
		biConsumer.accept("BiConsumer hello", "doctor");

		BiFunction<String, String, String> biFunction = (a, b) -> String.join(",", a, b);
		System.out.println(biFunction.apply("BiFunction", "hello"));

		// BinaryOperator 是一种特殊的BiFunction，参数是相同类型（入参，返回类型）
		BinaryOperator<String> binaryOperator = (a, b) -> String.join(",", a, b);
		System.out.println(binaryOperator.apply("binaryOperator ", "hello"));

		BiPredicate<String, String> biPredicate = (a, b) -> a.equals(b);
		System.out.println("BiPredicate:" + biPredicate.test("ab", "cd"));

		BooleanSupplier booleanSupplier = () -> true;
		System.out.println("BooleanSupplier:" + booleanSupplier.getAsBoolean());

		Consumer<String> consumer = (a) -> System.out.println(a);
		consumer.accept("Consumer:" + "hello");

		List<String> collect = Stream.of("c", "dca", "cddd", "abccss", "ba", "aab").parallel().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
		collect.forEach(System.out::println);

		// ***java8 函数引用 -> 一种利用现有函数简单推理为函数表达式即简化的函数表达式（省去了参数，-> 符号，只有更简化函数表达式体），[从而生成对应接口实现类默认的抽象方法体]。***
		// 函数表达式可以推理成java的匿名类，那么现有的函数就可以推理成函数表达式。只要现有的函数符合要生成的方法的签名即可（入参，返回值，函数名无关紧要）
		// 参数和返回值有编译器从上下文获得。从而省去了参数。只提供方法名就可以。
		// 所以（arg) -> {} 函数表达式，可以简化为利用现有的函数引用：<class or instance name>::<methodName>。

		// A method reference is a
		// simplified form of a lambda expression, which specifies the class name or instance name, followed by the method to
		// be called in the following format:
		// <class or instance name>::<methodName>

		// The double colon (::) operator specifies a method reference. Since a method reference is a simplified lambda
		// method, it must implement a functional interface, and the abstract method within the interface must have the same
		// argument list and return type as the method being referenced. Any arguments are subsequently derived from the
		// context of the method reference. For instance, consider the same scenario as the solution, whereby you wanted to sort
		// an array of Player objects by calling upon the Player.compareByGoal() method to perform goal comparisons.
		// The following code could be written to enable this functionality via a lambda expression:

		Integer[] integers = { 12, 45, 1, 66, 33, 22 };
		System.out.println("lambda expression排序前：");
		Arrays.stream(integers).forEach(System.out::println);

		Arrays.sort(integers, (a, b) -> Integer.compare(a, b)); // 用lambda expression 生成匿名类 Comparator , 实现compare(T o1, T o2) 方法
		System.out.println("lambda expression 排序后：");
		Arrays.stream(integers).forEach(System.out::println);

		Integer[] integers2 = { 12, 45, 1, 66, 33, 22 };
		System.out.println("函数引用排序前：");
		Arrays.stream(integers2).forEach(System.out::println);
		Arrays.sort(integers2, Integer::compare); // 用函数引用生成匿名类，
		System.out.println("函数引用排序后：");
		Arrays.stream(integers2).forEach(System.out::println);

	}

	@FunctionalInterface
	public interface HelloType {
		void hello(String message);
	}
}
