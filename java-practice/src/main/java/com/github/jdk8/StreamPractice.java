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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;

/**
 * @author doctor
 *
 * @date 2014年9月17日 下午9:13:46
 */
public class StreamPractice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> asList = Arrays.asList("a","b","c","d");
		String reduce = asList.stream().reduce("", String::concat);
		System.out.println(reduce);
		Assert.assertEquals(String.join("", asList), reduce);
		
		

	}

}
