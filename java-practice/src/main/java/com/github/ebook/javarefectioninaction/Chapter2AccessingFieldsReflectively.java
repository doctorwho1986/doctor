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
package com.github.ebook.javarefectioninaction;

import java.lang.reflect.Array;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author doctor
 *
 * @date 2014年9月24日 上午12:05:09
 */
public class Chapter2AccessingFieldsReflectively {

	@Test
	public void test_Working_with_arrays(){
		String[] s = new String[]{"name","doctor"};
		Assert.assertTrue(s.getClass().isArray());
		
		Assert.assertEquals(s.length, Array.getLength(s));
		Assert.assertEquals(s[0], Array.get(s, 0));
		Assert.assertEquals(s[1], Array.get(s, 1));
		
		String value = "who are you";
		Array.set(s, 0, value);
		Assert.assertEquals(value, Array.get(s, 0));
		
		int[] newInstance = (int[]) Array.newInstance(int.class, 1);
		Assert.assertEquals(1, newInstance.length);

	}

}
