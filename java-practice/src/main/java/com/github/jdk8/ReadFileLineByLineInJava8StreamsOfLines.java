package com.github.jdk8;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @see http://howtodoinjava.com/2014/05/04/read-file-line-by-line-in-java-8-streams-of-lines-example/
 * @author doctor
 *
 */
public class ReadFileLineByLineInJava8StreamsOfLines {

	public static void main(String[] args) {

		Path path = Paths.get(new File("").getAbsolutePath(), 
							  "src/main/java",
				              ReadFileLineByLineInJava8StreamsOfLines.class.getName().replace(".", "/")
				              +".java");
		
		try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8).onClose(()-> System.out.println("file close"))){
			stream.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
