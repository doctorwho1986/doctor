package com.github.jdk.jdk8.JavaSE8forTheReallyImpatientBook;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.springMvc.example.domain.Person;
import com.github.springMvc.example.domain.Person.Gender;

public class TheStreamAPI {

	public static void main(String[] args) {
		List<String> words = Arrays.asList("djfkjd", "doctor", "doctor who", "name", "sex", "age", "address");

		/*
		 * Simply changing stream into paralleStream allows the stream library
		 * to do the filtering and counting in parallel.
		 */
		long count = words.parallelStream().filter(p -> p.length() > 3).count();
		System.out.println(count);

		/*
		 * 1. Stream Creation
		 */
		Stream<String> of = Stream.of("dd", "cc", "bb");
		of.forEach(System.out::println);

		Stream<String> empty = Stream.empty();
		empty.forEach(System.out::print);

		Stream<String> generate = Stream.generate(() -> "Echo");
		generate.limit(6).forEach(System.out::print);

		System.out.println();
		Stream.generate(() -> Math.random()).limit(2).forEach(System.out::println);

		Stream.iterate(BigDecimal.ZERO, n -> n.add(BigDecimal.ONE)).limit(10).forEach(System.out::println);

		/*
		 * NOTE: A number of methods that yield streams have been added to the
		 * API with the Java 8 release.
		 */
		String contents = "name,doctor,sex,man,age,10";
		Pattern.compile(",").splitAsStream(contents).forEach(System.out::println);

		Path rootdir = Paths.get("").toAbsolutePath();
		String name = TheStreamAPI.class.getName();
		name = "src" + File.separator + "main" + File.separator + "java" + File.separator + name.replace(".", File.separator) + ".java";

		Path filePath = Paths.get(rootdir.toString(), name);

		/*
		 * The stream, and the underlying file with it, will be closed when the
		 * try block exits normally or through an exception.
		 */
		try (Stream<String> stream = Files.lines(filePath, Charset.forName("utf-8"));) {
			stream.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * 2. The filter, map, and flatMap Methods A stream transformation reads
		 * data from a stream and puts the transformed data into another stream.
		 * You have already seen the filter transformation that yields a new
		 * stream with all elements that match a certain condition. Here, we
		 * transform a stream of strings into another stream containing only
		 * long words: The argument of filter is a Predicate<T>—that is, a
		 * function from T to boolean. Often, you want to transform the values
		 * in a stream in some way. Use the map method and pass the function
		 * that carries out the transformation. For example, you can transform
		 * all words to lowercase like this:
		 */

		words.parallelStream().map(p -> p.toUpperCase() + "  ").forEach(System.out::print);

		/*
		 * When you use map, a function is applied to each element, and the
		 * return values are collected in a new stream.
		 */
		System.out.println();
		List<Stream<String>> list = words.parallelStream().map(p -> Stream.of(p.split(""))).collect(Collectors.toList());

		for (Stream<String> stream : list) {
			System.out.print("stream: ");
			stream.forEach(p -> System.out.print(p + "   "));
		}

		System.out.println();
		List<String> collect = words.parallelStream().flatMap(p -> Stream.of(p.split(""))).collect(Collectors.toList());
		collect.parallelStream().forEach(p -> System.out.print(p + "  "));

		/*
		 * 3. Extracting Substreams and Combining Streams The call
		 * 
		 * stream.limit(n) returns a new stream that ends after n elements (or
		 * when the original stream ends if it is shorter). This method is
		 * particularly useful for cutting infinite streams down to size. For
		 * example,
		 */
		System.out.println();
		Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).skip(5).forEach(p -> System.out.print(p + "  "));

		/*
		 * TIP: The peek method yields another stream with the same elements as
		 * the original, but a function is invoked every time an element is
		 * retrieved. That is handy for debugging: Object[] powers =
		 * Stream.iterate(1.0, p -> p * 2) .peek(e ->
		 * System.out.println("Fetching " + e)) .limit(20).toArray(); When an
		 * element is actually accessed, a message is printed. This way you can
		 * verify that the infinite stream returned by iterate is processed
		 * lazily.
		 */
		System.out.println();
		Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).map(p -> p * p).peek(p -> System.out.print(" get " + p)).limit(3).toArray();

		/*
		 * Grouping and Partitioning
		 */
		System.out.println();
		List<Person> lPersons = new ArrayList<>();
		lPersons.add(new Person("doctor1", Person.Gender.WOMAN, "chinaBeijing"));
		lPersons.add(new Person("doctor2", Person.Gender.MAN, "chinaShenzheng"));
		lPersons.add(new Person("doctor3", Person.Gender.MAN, "chinaBeijing"));
		lPersons.add(new Person("doctor", Person.Gender.MAN, "chinaChongqing"));
		lPersons.add(new Person("doctor", Person.Gender.WOMAN, "chinaShandong"));
		System.out.println("<<<lPerson");
		lPersons.stream().forEach(System.out::println);
		System.out.println(">>>lPerson");

		Map<Gender, List<Person>> mapPersonByGender = lPersons.stream().collect(Collectors.groupingBy(Person::getGender));
		for (Gender gender : mapPersonByGender.keySet()) {
			System.out.println(gender.name() + " have :");
			List<Person> p = mapPersonByGender.get(gender);
			p.forEach(System.out::println);
		}

		// 另一种输出显示
		System.out.println("--------------------------------------------------------");
		lPersons.stream().collect(Collectors.groupingBy(Person::getGender)).forEach((g, l) -> {
			System.out.println(g.name());
			l.stream().forEach(System.out::println);
		});

		/*
		 * The groupingBy method yields a map whose values are lists. If you
		 * want to process those lists in some way, you supply a “downstream
		 * collector.” For example, if you want sets instead of lists, you can
		 * use the Collectors.toSet collector that you saw in the preceding
		 * section:
		 */
		System.out.println("---------------groupingBy-------------");
		Map<Gender, Set<Person>> map2 = lPersons.stream().collect(Collectors.groupingBy(Person::getGender, Collectors.toSet()));
		for (Gender g : map2.keySet()) {
			System.out.println(g);
			map2.get(g).stream().forEach(System.out::println);
		}

		/*
		 * 2.14  Functional Interfaces In this chapter, you have seen many
		 * operations whose argument is a function. For example, the
		 * Streams.filter method takes a function argument: 
		 *      Stream<String> longWords = words.filter(s -> s.length() >= 12); 
		 * In the javadoc of the Stream class, the filter method is declared as follows: 
		 *     Stream<T> filter(Predicate<? super T> predicate) 
		 *  To understand the
		 * documentation, you have to know what a Predicate is. It is an
		 * interface with one nondefault method returning a boolean value:
		 *     public interface Predicate {
		 *          boolean test(T argument); 
		 *      } 
		 *  
		 * In practice,
		 * one usually passes a lambda expression or method reference, so the
		 * name of the method doesn’t really matter. The important part is the
		 * boolean return type. When reading the documentation of Stream.filter,
		 * just remember that a Predicate is a function returning a boolean.
		 * NOTE: When you look closely at the declaration of Stream.filter, you
		 * will note the wildcard type Predicate<? super T>. This is common for
		 * function para- meters. For example, suppose Employee is a subclass of
		 * Person, and you have a Stream<Employee>. You can filter the stream
		 * (where T is Employee) with a Predicate<Employee>, a
		 * Predicate<Person>, or a Predicate<Object>. This flexibility is
		 * particularly important for supplying method references. For example,
		 * you may want to use Person::isAlive to filter a Stream<Employee>.
		 * That only works because of the wildcard in the parameter of the
		 * filter method.
		 */
	}

}
