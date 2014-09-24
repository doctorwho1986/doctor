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
package com.github.ebook.theartofencryptionandeecryptionaboutjava;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author doctor
 *
 * @date 2014年9月24日 下午8:56:32
 */
public class Chapter5 {

	@Test
	public void test_base64(){
		String hello = "java解密与加密的艺术";
		System.out.println("原文：" + hello);
		String encodeToString = Base64.getEncoder().encodeToString(hello.getBytes(StandardCharsets.UTF_8));
		System.out.println("base64编码后：" + encodeToString);
		byte[] decode = Base64.getDecoder().decode(encodeToString);
		String decodeString = new String(decode, StandardCharsets.UTF_8);
		System.out.println("base64解码后：" + decodeString);
		Assert.assertEquals(hello, decodeString);
	}
	
	@Test
	public void test_urlBase64(){
		String hello = "java解密与加密的艺术";
		System.out.println("原文：" + hello);
		String encodeToString = Base64.getUrlEncoder().encodeToString(hello.getBytes(StandardCharsets.UTF_8));
		System.out.println("base64编码后：" + encodeToString);
		byte[] decode = Base64.getUrlDecoder().decode(encodeToString);
		String decodeString = new String(decode, StandardCharsets.UTF_8);
		System.out.println("base64解码后：" + decodeString);
		Assert.assertEquals(hello, decodeString);
		
	}
}
