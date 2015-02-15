package com.github.jdk7;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @see http://howtodoinjava.com/2013/07/08/how-to-define-path-in-java-nio/
 * @author doctor
 *
 * @since 2015年2月15日 下午9:07:54
 */
public class HowToDefinePathInJavaNio {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Throwable {
		//Define absolute path
		Path path = Paths.get("/home", "doctor");
		System.out.println("Define absolute path: " + path.toAbsolutePath());
		System.out.println(path.toFile().exists());
		path = Paths.get(path.toString(), "mm");
		System.out.println(path + "  --  " + path.toFile().exists());
		
		//Define path relative to current working directory
		System.out.println("Define path relative to current working directory:");
		path = Paths.get("src", "s.txt");
		System.out.println(path.toAbsolutePath());

	}

}
