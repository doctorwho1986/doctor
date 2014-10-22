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
package com.github.jdk7;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * @author doctor
 *
 * @date 2014年9月20日 下午5:06:46
 */
public class Chapter1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		switchString();
		binary();
		test1();
		test2(1,2,3,4);
	}
	
	
	public static void switchString() {
		String aString = "no";
		switch (aString) {
		case "no":
			System.out.println(aString + " is no");
			break;
		case "yes":
			System.out.println(aString + " is yes ");
			break;
		default:
			System.out.println("defalut");
			break;
		}
	}

	
	public static void binary() {
		System.out.println(0b1111_0000);
		System.out.println(0b0010);
	}
	
	public static void test1() {
		InputStream inputStream = Chapter1.class.getResourceAsStream("/dkj/a.txt");
		try {
			String allLines = IOUtils.toString(inputStream);
		} catch (Throwable e) {
			
			System.err.println(" error ");
		}finally{
			IOUtils.closeQuietly(inputStream);
		}
		System.out.println("now");
		
		
		System.out.println("close");
		
	}
	
	public static <T> void test2(T... arg) {
		System.out.println(arg.getClass());
		System.out.println(arg.getClass().isArray());
		System.out.println(arg.toString());
	}
}
