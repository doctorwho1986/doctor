package com.github.jdk.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
		} else {
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
		System.out.println(path + " isExecutable " + Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS));

		System.out.println("---------------read file");
		Path rootdir = Paths.get("").toAbsolutePath();
		String name = FilesPractice.class.getName();
		name = "src" + File.separator + "main" + File.separator + "java" + File.separator + name.replace(".", File.separator) + ".java";

		Path filePath = Paths.get(rootdir.toString(), name);
		System.out.println(filePath);
		try {
			List<String> readAllLines = Files.readAllLines(filePath);
			for (String readLine : readAllLines) {
				System.out.println(readLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("-------newBufferedReader ----");
		try (
				BufferedReader bufferedReader = Files.newBufferedReader(filePath, Charset.forName("utf-8"))) {
			String readLine = null;
			while (null != (readLine = bufferedReader.readLine())) {
				System.out.println(readLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("-------newBufferedReader ----");
		Path pathWrite = Paths.get(System.getProperty("user.home"), "write");
		if (Files.notExists(pathWrite, LinkOption.NOFOLLOW_LINKS)) {
			try {
				Files.createFile(pathWrite);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String wString = "ssssssssssssssdkkkkkkkkkkkk";
		try (
				BufferedWriter newBufferedWriter = Files.newBufferedWriter(pathWrite, Charset.forName("utf-8"));) {

			newBufferedWriter.write(wString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
