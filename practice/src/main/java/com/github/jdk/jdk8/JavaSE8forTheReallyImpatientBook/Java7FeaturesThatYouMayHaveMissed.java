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

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * @author doctor
 *
 * @date 2014年9月13日 下午10:36:04
 */
public class Java7FeaturesThatYouMayHaveMissed {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String join = String.join(File.separator, Java7FeaturesThatYouMayHaveMissed.class.getPackage().getName().split("\\."));
		System.out.println(join);
		Path path = Paths.get("").toAbsolutePath();
		path = Paths.get(path.toString(), "src","main","java",join,Java7FeaturesThatYouMayHaveMissed.class.getSimpleName()+".java");
		System.out.println(path);
		try {
			List<String> readAllLines = Files.readAllLines(path, StandardCharsets.UTF_8);
			readAllLines.forEach(System.out::println);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		

	}

}
