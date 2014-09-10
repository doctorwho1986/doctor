package com.github.jdk.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathPractice {

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"));
		System.out.println(path);
		try {
			Path realPath = path.toRealPath(LinkOption.NOFOLLOW_LINKS);
			System.out.println(realPath);
		} catch (IOException e) {
			System.out.println("error : no such a file : " + path);
		}
		
		File file = path.toFile();
		if (file.isDirectory()) {
			System.out.println(path + " is a directory");
			File[] list = file.listFiles();
			for (File f : list) {
				System.out.println(f);
			}
		}

		System.out.println("---------------------");
		System.out.println(Paths.get("b","b","../c").normalize().toAbsolutePath());
	}

}
