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
package com.github.jdk8;

import java.util.stream.Stream;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * @author doctor
 *
 * @date 2014年9月17日 下午9:22:45
 */
public class LambdaPractice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Sorting Strings by Length
		String[] strings = {"abcdedd","aaadd","b","bcdd","aaad","kdkjfkdjfkdjf"};
		System.out.println("Sorting Strings by Length");
		Stream.of(strings).sorted((a,b) -> a.length() - b.length()).forEach(System.out::println);
		
		//Sorting Strings by Last Char
		System.out.println("\n\nSorting Strings by Last Char");
		Stream.of(strings).sorted((a,b) ->  a.charAt(a.length()-1) - b.charAt(b.length()-1)).forEach(System.out::println);
	}

}
