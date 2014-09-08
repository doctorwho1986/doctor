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
package com.github.netexample.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author doctor
 *
 * @date 2014年9月8日 下午4:47:48
 */
public class URLPractice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.baidu.com");
			InputStream openStream = url.openStream();
			int chr = -1;
			
			do {
				chr = openStream.read();
				System.out.print((char)chr);
				
			} while (-1 != chr);
			
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		
		try {
			URL url = new URL("http://www.sun0816.com/");
			System.out.println(url.getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
