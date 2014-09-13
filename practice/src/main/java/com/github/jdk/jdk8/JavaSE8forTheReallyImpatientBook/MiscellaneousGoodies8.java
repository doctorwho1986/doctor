/*
 * Copyright (C) 2014 The doctor Authors
 * https://github.com/doctorwho1986
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jdk.jdk8.JavaSE8forTheReallyImpatientBook;

import java.math.BigInteger;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

/**
 * @author doctor
 *
 * @date 2014年9月13日 下午10:08:38
 */
public class MiscellaneousGoodies8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * A common task is to combine several strings, separating them with a
		 * delimiter such as ", " or "/". This has now been added to Java 8. The
		 * strings can come from an array or an Iterable<? extends
		 * CharSequence>:
		 */
		String join = String.join(",", "name", "doctor", "sex", "man", "address", "unknown");
		System.out.println(join);
		System.out.println(String.join(",", ZoneId.getAvailableZoneIds()));
		List<String> list = Arrays.asList("name", "doctor", "sex", "man", "address", "unknown");
		System.out.println(String.join(",", list));

		/*
		 * The five types Short, Integer, Long, Float, and Double now have
		 * static methods sum, max, and min, which can be useful as reduction
		 * functions in stream operations. The Boolean class has static methods
		 * logicalAnd, logicalOr, and logicalXor for the same purpose.
		 */

		System.out.println(Integer.sum(12, 13));
		System.out.println(Boolean.logicalOr(Boolean.TRUE, Boolean.FALSE));
		System.out.println(Integer.bitCount(12));
		System.out.println(Integer.toBinaryString(12));

		/*
		 * The Float and Double classes have static methods isFinite. The call
		 * Double.isFinite(x) returns true if x is not infinity, negative
		 * infinity, or a NaN (not a number). In the past, you had to call the
		 * instance methods isInfinite and isNaN to get the same result.
		 */
		System.out.println(Double.isFinite(123));
		System.out.println(Double.isFinite(Double.MAX_VALUE + 1));
		System.out.println(Double.isNaN(55));

		
		/*
		 * Finally, the BigInteger class has instance methods
		 * (long|int|short|byte)ValueExact that return the value as a long, int,
		 * short, or byte, throwing an ArithmeticException if the value is not
		 * within the target range.
		 */
		try {
			BigInteger.valueOf(Long.MAX_VALUE).shortValueExact();
		} catch (ArithmeticException e) {

			System.err.println(e.getMessage());
		}
	}

}
