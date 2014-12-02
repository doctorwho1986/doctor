package com.github.jdk;

import java.net.URL;
import java.net.URLClassLoader;

public class PrintCurrentProjectClasspath {

	public static void main(String[] args) {
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		URL[] urLs = ((URLClassLoader)systemClassLoader).getURLs();
		for (URL url : urLs) {
			System.out.println(url);
		}

	}

}
