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
package com.github.jdk.jdk8.java8LambdasBook;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;


/**
 * @author doctor
 *
 * @date 2014年9月14日 上午1:05:19
 */
public class Streams3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = Stream.of("a","b","c").collect(Collectors.toList());
		Assert.assertTrue(Arrays.asList("a","b","c").equals(list));
		
		List<String> list2 = Stream.of("abc","name","doctor","sex","man").map(T -> T.toUpperCase()).collect(Collectors.toList());
		Assert.assertTrue(Arrays.asList("abc".toUpperCase(),"name".toUpperCase(),"doctor".toUpperCase(),"sex".toUpperCase(),"man".toUpperCase()).equals(list2));

		List<String> list3 = Stream.of("ab","cc","1dd").filter(T -> Character.isDigit(T.charAt(0))).collect(Collectors.toList());
		Assert.assertTrue(Arrays.asList("1dd").equals(list3));
		
		//next -> P25

	}

}
