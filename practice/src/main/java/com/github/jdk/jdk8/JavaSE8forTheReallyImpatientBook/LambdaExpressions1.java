package com.github.jdk.jdk8.JavaSE8forTheReallyImpatientBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;

public class LambdaExpressions1 {

	public static void main(String[] args) {
		Comparator<String> comp = (a, b) -> Integer.compare(a.length(), b.length());
		Assert.assertEquals(1, comp.compare("aa", "a"));
		Assert.assertEquals(0, comp.compare("a", "a"));
		Assert.assertEquals(-1, comp.compare("a", "aa"));

		Comparator<Integer> comInt = (Integer a, Integer b) -> Integer.compare(a, b);
		Assert.assertEquals(-1, comInt.compare(12, 15));
		Assert.assertEquals(1, comInt.compare(112, 15));

		List<Integer> listIntegers = Arrays.asList(12, 44, 55, 33, 2, 6, 99, 88, 55, 3);
		Collections.sort(listIntegers, (a, b) -> (Integer.compare(a, b)));
		listIntegers.forEach(System.out::println);

		/*
		 * As of Java 8, you are allowed to add static methods to interfaces.
		 * There was never a technical reason why this should be outlawed. It
		 * simply seemed to be against the spirit of interfaces as abstract
		 * specifications. Up to now, it has been common to place static methods
		 * in companion classes. You find pairs of interfaces and utility
		 * classes such as Collection/Collections or Path/Paths in the standard
		 * library. Have a look at the Paths class. It only has a couple of
		 * factory methods. You can construct a path from a sequence of strings,
		 * such as Paths.get("jdk1.8.0", "jre", "bin"). In Java 8, one could
		 * have added this method to the Path interface. Then the Paths class is
		 * no longer necessary.
		 * 
		 * It is unlikely that the Java collections library will be refactored
		 * in this way, but when you implement your own interfaces, there is no
		 * longer a reason to provide a separate companion class for utility
		 * methods. In Java 8, static methods have been added to quite a few
		 * interfaces. For example, the Comparator interface has a very useful
		 * static comparing method that accepts a “key extraction” function and
		 * yields a comparator that compares the extracted keys. To compare
		 * Person objects by name, use Comparator.comparing(Person::name).
		 */
		Assert.assertFalse(InterfaceMore.isNull(4));
		new InterfaceMore() {

			@Override
			public void interfaceCommon() {
				// TODO Auto-generated method stub

			}
		}.interfaceDefault("lllll");

	}

}

interface InterfaceMore {
	void interfaceCommon();

	default void interfaceDefault(String printMessage) {
		System.out.println(printMessage);
	}

	public static boolean isNull(Object object) {
		if (null == object) {
			return true;
		}
		return false;

	}
}
