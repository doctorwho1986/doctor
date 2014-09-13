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

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author doctor
 *
 * @date 2014年9月13日 下午9:55:03
 */
public class ConcurrencyEnhancements {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AtomicLong atomicLong = new AtomicLong(15L);
		System.out.println(atomicLong.addAndGet(15L));
		System.out.println(atomicLong.updateAndGet(x -> x + 15L));

	}

}
