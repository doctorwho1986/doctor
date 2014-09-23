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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author doctor
 *
 * @date 2014年9月23日 下午11:38:47
 */
public class Chapter1 {
	private static final Logger log = LoggerFactory.getLogger(Chapter1.class);
	
	@Test
	public void test_isInterface(){
		Assert.assertTrue(List.class.isInterface());
		Assert.assertFalse(ArrayList.class.isInterface());
	}
	
	@Test
	public void test_getMethods(){
		Method[] methods = List.class.getMethods();
		Assert.assertEquals(32, methods.length);
		log.info("{List.class.getMethods() result: {}}",Arrays.asList(methods));
		
		Method[] declaredMethods = List.class.getDeclaredMethods();
		Assert.assertEquals(28, declaredMethods.length);
		log.info("{List.class.getDeclaredMethods() result : {}}",Arrays.asList(declaredMethods));
	
		Assert.assertFalse(Arrays.class.isArray());
		Assert.assertTrue(int[].class.isArray());
	}
}
