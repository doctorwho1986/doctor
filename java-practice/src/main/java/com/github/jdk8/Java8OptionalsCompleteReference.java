package com.github.jdk8;

import java.util.Optional;

/**
 * @see http://howtodoinjava.com/2014/06/09/java-8-optionals-complete-reference/
 * Optional并不是取代null引用的。可以检查参数，为null自动抛出异常，不用自己处理null引用带来的异常
 * @author doctor
 *
 */
public class Java8OptionalsCompleteReference {

	/**
	 * It is important to note that the intention of the Optional class is not to replace every single null reference.
	 *  Instead, its purpose is to <strong>help design more-comprehensible APIs</strong> 
	 *  so that by just reading the signature of a method, you can tell whether you can expect an optional value. 
	 *  This forces you to fetch the value from Optional and work on it, 
	 *  and at the same time handle the case where optional will be empty. 
	 *  Well, this is exactly the solution of null references/return values which ultimately result into <code>NullPointerException</code>.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Optional<Integer> optional = Optional.of(5);
		if (optional.isPresent()) {
			System.out.println(optional.get());
		}

		Optional<Object> empty = Optional.empty();
		if (!empty.isPresent()) {
			System.out.println("empty:");
		}

		Optional<Object> ofNullable = Optional.ofNullable(null);
		if (!ofNullable.isPresent()) {
			System.out.println("null");
		}
		
		try {
			Optional.of(null);
		} catch (Throwable e) {
			System.out.println("e:" + e.toString());
		}

		
		Optional<String> nullable = Optional.ofNullable("name:doctor who");
		nullable.ifPresent(System.out::println);
		nullable = Optional.ofNullable(null);
		nullable.ifPresent(System.out::println);
	}

}
