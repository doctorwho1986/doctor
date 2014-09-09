package com.github.jdk.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesPractice {

	public static void main(String[] args) {
		Path path = Paths.get(System.getProperty("user.home"));
		boolean exists = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
		if (exists) {
			System.out.println(path + " is exist");
		}
		
		path = Paths.get(System.getProperty("user.home"), "paths");
		exists = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
		if (exists) {
			System.out.println(path + " is exist");
		}else {
			try {
				Files.createFile(path);
				System.out.println(path + " is createDirectorie");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println(path + " isReadable " + Files.isReadable(path));
		System.out.println(path + " isWritable " + Files.isWritable(path));
		System.out.println(path + " isExecutable " + Files.isExecutable(path));
		System.out.println(path + " isExecutable " + Files.isRegularFile(path,LinkOption.NOFOLLOW_LINKS));
	}

}
