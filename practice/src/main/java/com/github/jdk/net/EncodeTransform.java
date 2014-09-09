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
package com.github.jdk.net;

import java.nio.charset.Charset;

/**
 * @author doctor
 *
 * @date 2014年9月9日 下午10:00:48
 */
public class EncodeTransform {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] bytes = "a123456".getBytes(Charset.forName("utf-8"));
		for (byte b : bytes) {
			System.out.println(b + " " +  (char)b); //Unicode (ascii)码表 
		}

	}

}
