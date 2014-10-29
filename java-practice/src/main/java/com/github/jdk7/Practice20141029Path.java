package com.github.jdk7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;

public class Practice20141029Path {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get(Practice20141029Path.class.getResource("/javaPracticeProp").toString());
		path = Paths.get(path.toString(), "a");
	
		
		System.out.println(path);

	}

}
