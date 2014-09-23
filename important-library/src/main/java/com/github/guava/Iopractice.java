package com.github.guava;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

public class Iopractice {
	private Logger log = LoggerFactory.getLogger(Iopractice.class);
	
	@Test
	public  void testFilesRead() throws IOException {
		String readString = Files.toString(new File("src/main/resources/guavatest/file.txt"), StandardCharsets.UTF_8);
		String read = IOUtils.toString(Iopractice.class.getResourceAsStream("/guavatest/file.txt"));
		
		Assert.assertEquals(read, readString);
//		log.info("{testFilesRead:'{}'}",readString);
	}

	@Test
	public void testFilesCopy() throws IOException{
		String pathSrc = "src/main/resources/guavatest/file.txt";
		String pathDes = "src/main/resources/guavatest/file1.txt";
		File file = new File(pathDes);
		if (file.exists()) {
			file.delete();
			log.info("{file exists: '{} and delete '}",pathDes);
		}
		
		Files.copy(new File(pathSrc), new File(pathDes));
		
		Assert.assertTrue(new File(pathDes).exists());
		
		Assert.assertTrue(Files.equal(new File(pathSrc), new File(pathDes)));
		
		file.delete(); 
	}
	
	@Test
	public void testFilesWrite() throws IOException{
		String pathDes = "src/main/resources/guavatest/file2.txt";
		File file = new File(pathDes);
		if (file.exists()) {
			file.delete();
			log.info("{file exists: '{} and delete '}",pathDes);
		}
		
		file.createNewFile();
		String wirteString = "wirte dddsjdkfjd神秘搏杀  星际之门";
		Files.write(wirteString, file, StandardCharsets.UTF_8);
		
		String read = Files.toString(file, StandardCharsets.UTF_8);
		Assert.assertEquals(wirteString, read);
		
		
		file.delete();
		
	}
}
